HOMEPAGE = "https://github.com/coreos/coreos-installer"
SUMMARY = "A program to assist with installing coreos type OSes"
DESCRIPTION = "A program to assist with installing coreos type OSes. \
               It supports installing the operating system to a target \
               disk, downloading and verifying operating system images, \
               providing an up-to-date list of available images, wraps \
               ignition and initrd images to assist with PXE booting."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

SRCREV = "3f514894e9ec250dcef67c0257b3a1e43246218a"
PV = "0.17.0"
SRC_URI = "git://github.com/coreos/coreos-installer;branch=main;protocol=https"

S = "${WORKDIR}/git"

include coreos-installer-crates.inc

inherit pkgconfig cargo cargo-update-recipe-crates 

DEPENDS = " \
    sed-native \
    zstd \
    openssl \
    virtual/libc \
    make-native \
"

RDEPENDS:${PN} = " \
    gpgme \
    kpartx \
    util-linux-lsblk \
    udev \
    bash \
"

CARGO_BUILD_FLAGS += "--features rdcore"

do_install:prepend() {
    cd ${S}
    #cargo install -f rdcore
    install -D -m 0755 -t ${D}${base_libdir}/dracut/modules.d/50rdcore dracut/50rdcore/module-setup.sh
    mv ${B}/target/x86_64-wrs-linux-gnu/release/rdcore ${D}${base_libdir}/dracut/modules.d/50rdcore/.
    make install-systemd DESTDIR=${D}
    make install-scripts DESTDIR=${D}
    make install-data DESTDIR=${D}
}

PACKAGES+= "${PN}-bootinfra"
FILES:${PN}-bootinfra = " \
    ${base_libdir}/dracut/modules.d/50rdcore \
    ${systemd_system_unitdir} \
    ${systemd_unitdir} \
    ${libexecdir}/coreos-installer-disable-device-auto-activation \
    ${libexecdir}/coreos-installer-service \
"
RDEPENDS:${PN}-bootinfra = " \
    bash \
"

inherit systemd
SYSTEMD_PACKAGES = "${PN}-bootinfra"
SYSTEMD_SERVICE:${PN}-bootinfra = " \
    coreos-installer-disable-device-auto-activation.service \
    coreos-installer-noreboot.service \
    coreos-installer-pre.target \
    coreos-installer-reboot.service \
    coreos-installer-post.target \
    coreos-installer.service \
    coreos-installer.target \
"

#do_configure() {
#    cd ${S}
#    # Avoid a whack of extra data
#    sed -i 's/^debug = true$/debug = false/' Cargo.toml
#}
#
#do_compile() {
#    cd ${S}
#    cargo build --release --target-dir=${B}
#}
