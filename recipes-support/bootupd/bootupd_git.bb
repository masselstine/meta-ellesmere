xHOMEPAGE = "https://github.com/coreos/bootupd"
SUMMARY = "Bootloader updater."
DESCRIPTION = "A cross-distribution, OS update system agnostic tool to \
               manage updates for things like: /boot/efi, x86 BIOS \
               MBR, other architecture bootloaders."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fa818a259cbed7ce8bc2a22d35a464fc"

SRCREV = "db96220e76c6fb656f863355088c9b006f11ab94"
PV = "0.2.9"
SRC_URI = "git://github.com/coreos/bootupd;branch=main;protocol=https"

S = "${WORKDIR}/git"

include bootupd-crates.inc

inherit pkgconfig cargo cargo-update-recipe-crates  systemd

DEPENDS = " \
    glib-2.0 \
    ostree \
    openssl \
"

RDEPENDS:${PN} = " \
"

do_install:append() {
    ln -f ${D}${base_bindir}/bootupd ${D}${base_bindir}/bootupctl

    install -D -p -m0644 ${S}/systemd/bootupd.service ${D}${systemd_system_unitdir}/bootupd.service
    install -D -p -m0644 ${S}/systemd/bootupd.socket ${D}${systemd_system_unitdir}/bootupd.socket
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "bootupd.service bootupd.socket"
