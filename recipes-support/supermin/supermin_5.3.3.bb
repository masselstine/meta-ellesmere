SUMMARY = "Tool for creating supermin appliances"
DESCRIPTION = "Supermin is a tool for building supermin appliances. \
               These are tiny appliances (similar to virtual \
               machines), usually around 100KB in size, which get \
               fully instantiated on-the-fly in a fraction of a second \
               when you need to boot one of them."
HOMEPAGE = "https://people.redhat.com/~rjones/supermin/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools pkgconfig gettext ocaml findlib autotools-brokensep

#CFLAGS:prepend = " --sysroot=${RECIPE_SYSROOT} "

#OCAMLLINKFLAGS = " -ccopt '--sysroot=${RECIPE_SYSROOT} ${CFLAGS}' -cclib '${LDFLAGS}' "
OCAMLLINKFLAGS = " -cc '${CC}'"
export OCAMLLINKFLAGS

DEPENDS = " \
    rpm \
    e2fsprogs \
    ocaml \
    ocaml-cross-${TARGET_ARCH} \
    findlib-cross-${TARGET_ARCH} \
"

RDEPENDS:${PN} = " \
    rpm \
    dnf \
    gzip \
    xz \
"

CACHED_CONFIGUREVARS += " \
ac_cv_path_RPM=/usr/bin/rpm \
ac_cv_path_DNF=/usr/bin/dnf \
ac_cv_path_RPM2CPIO=/usr/bin/rpm2cpio \
ac_cv_path_ZCAT=/usr/bin/zcat \
ac_cv_path_XZCAT=/usr/bin/xzcat \
"

SRC_URI = " \
    https://download.libguestfs.org/supermin/5.3-development/supermin-${PV}.tar.gz \
    file://0001-rpm-New-RPM-database-location-in-usr-lib-sysimage-rp.patch \
    file://0002-Add-support-for-OCaml-5.0.patch \
    file://0003-Restore-compatibility-with-OCaml-4.07.patch \
    file://0004-rpm-Detect-dnf5-and-omit-missing-options.patch \
    file://0005-rpm-Use-dnf-config-instead-of-c.patch \
    file://0006-src-Improved-debugging-of-the-supermin-if-newer-calc.patch \
    file://0007-src-Fix-if-newer-copy-kernel.patch \
    file://0008-rpm-Reenable-disable_excludes-for-dnf5.patch \
    file://0009-ocamlc-Use-output-complete-exe-instead-of-custom.patch \
    file://0010-ocamlc-Only-supply-output-complete-exe-to-final-link.patch \
    file://0001-ellesmere-customize-to-work-with-our-distro.patch \
"
SRC_URI[md5sum] = "b242dd25155eb2c3522b2d6e1ced3b86"
SRC_URI[sha256sum] = "88948d515a9b34285f9f5ae0158c808061b6ec319ad04a21ad7df7c8cbe2102d"

#EXTRA_OEMAKE = "GIR_EXTRA_LIBS_PATH=${RECIPE_SYSROOT}/${prefix}/lib/x86_64-wrs-linux/ocaml"

do_install:append() {
    rm -rf ${D}${prefix}/lib
}

#FILES:${PN} += " \
#    ${datadir}/bash-completion \
#    ${sysconfdir}/virt-builder \
#"
