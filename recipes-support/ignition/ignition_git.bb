HOMEPAGE = "https://github.com/coreos/ignition"
SUMMARY = "First boot installer and configuration tool"
DESCRIPTION = "Ignition is a utility that is used to manipulate disks \
               during the initramfs. This includes partitioning \
               disks, formatting partitions, writing files (regular \
               files, systemd units, etc.), and configuring users. On \
               first boot, Ignition reads its configuration from a \
               source of truth (remote URL, network metadata service, \
               hypervisor bridge, etc.) and applies the configuration. \
               containerd leverages runC's advanced features such as \
               seccomp and user namespace support as well as \
               checkpoint and restore for cloning and live migration \
               of containers."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=d2794c0df5b907fdace235a619d80314"

GO_IMPORT = "github.com/coreos/ignition"
SRCREV = "13f05b3c9f6221fb68234387ff2e4c2d63a39b63"
SRC_URI = " \
    git://${GO_IMPORT};branch=main;protocol=https \
    "
RAW_VERSION = "2.15.0"

inherit go

# New Go versions has Go modules support enabled by default and cause the Glide
# tool build to fail.
export GO111MODULE = "off"

inherit systemd
SYSTEMD_PACKAGES = "${@bb.utils.contains('DISTRO_FEATURES','systemd','${PN}','',d)}"
SYSTEMD_SERVICE:${PN} = "${@bb.utils.contains('DISTRO_FEATURES','systemd','ignition-delete-config.service','',d)}"

FILES:${PN} += "${systemd_system_unitdir}/*"

FILES:${PN} += "${base_libdir}/dracut/modules.d/30ignition"

DEPENDS += "util-linux"
RDEPENDS:${PN} += " \
    util-linux-blkid \
    dosfstools \
    dracut \
    bash \
"
RRECOMMENDS:${PN} += "btrfs-tools"

RDEPENDS:${PN}-dev:append = "bash"

# Switch selinuxRelabel=true once we are ready for selinux
IGN_LDFLAGS = " \
    -X github.com/coreos/ignition/internal/version.Raw=${RAW_VERSION} \
    -X github.com/coreos/ignition/internal/distro.selinuxRelabel=false \
    -X github.com/coreos/ignition/internal/distro.writeAuthorizedKeysFragment=false \
"
GO_EXTRA_LDFLAGS = "${IGN_LDFLAGS}"
BUILD_LDFLAGS:append = " ${IGN_LDFLAGS}"

# https://src.fedoraproject.org/rpms/ignition/blob/rawhide/f/ignition.spec
do_compile() {
    cd ${B}/src/${GO_IMPORT}
    ${GO} build ${GOBUILDFLAGS} -o ${B}/bin/ignition internal/main.go
    ${GO} build ${GOBUILDFLAGS} -o ${B}/bin/ignition-validate validate/main.go
}

# https://src.fedoraproject.org/rpms/ignition/blob/rawhide/f/ignition.spec
do_install:append() {
    SRCBASE="${S}/src/${GO_IMPORT}"
    install -d -p ${D}/${base_libdir}/dracut/modules.d
    cp -r ${SRCBASE}/dracut/* ${D}/${base_libdir}/dracut/modules.d/
    install -m 0644 -D -t ${D}/${systemd_system_unitdir} ${SRCBASE}/systemd/ignition-delete-config.service
    install -m 0755 -d ${D}/${libexecdir}
    ln -sf ../lib/dracut/modules.d/30ignition/ignition ${D}/${libexecdir}/ignition-apply
    ln -sf ../lib/dracut/modules.d/30ignition/ignition ${D}/${libexecdir}/ignition-rmcfg

    # ignition
    install -d -p ${D}${bindir}
    install -p -m 0755 ${B}/bin/ignition-validate ${D}${bindir}

    # The ignition binary is only for dracut, and is dangerous to run from
    # the command line.  Install directly into the dracut module dir.
    install -p -m 0755 ${B}/bin/ignition ${D}/${base_libdir}/dracut/modules.d/30ignition
}

PACKAGES =+ "${PN}-validate"
FILES:${PN}-validate += " \
    ${bindir}/ignition-validate \
"
INSANE_SKIP:${PN}-validate += "ldflags"