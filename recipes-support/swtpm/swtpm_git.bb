HOMEPAGE = "http://github.com/stefanberger/swtpm"
SUMMARY = "TPM Emulator"
DESCRIPTION = "TPM emulator built on libtpms providing TPM \
               functionality for QEMU VMs."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe8092c832b71ef20dfe4c6d3decb3a8"

S = "${WORKDIR}/git"

SRCREV = "2ae7b019370760e17f4f2675195a91ca53950eda"
PV = "0.8.0"
SRC_URI = "git://github.com/stefanberger/swtpm.git;branch=master;protocol=https"

inherit autotools pkgconfig

DEPENDS = " \
    openssl \
    socat \
    gnutls \
    libtasn1 \
    libtpms \
    net-tools-native \
    json-glib \
    expect-native \
    coreutils-native \
    perl-native \
"

do_install:append() {
	rm -f ${D}${libdir}/${BPN}/*.{a,la,so}
}
#FILES:${PN}-dev += "/usr/lib/swtpm/libswtpm_libtpms.so"
