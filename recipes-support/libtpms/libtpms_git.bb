HOMEPAGE = "http://github.com/stefanberger/libtpms"
SUMMARY = "Library providing Trusted Platform Module (TPM) functionality"
DESCRIPTION = "A library providing TPM functionality for VMs. Targeted \
               for integration into Qemu."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e73f0786a936da3814896df06ad225a9"

S = "${WORKDIR}/git"

SRCREV = "f8c2dc7e12a730dcca4220d7ac5ad86d13dfd630"
PV = "0.9.6"
SRC_URI = "git://github.com/stefanberger/libtpms.git;branch=stable-0.9;protocol=https"

inherit autotools pkgconfig

DEPENDS = " \
    coreutils \
    gawk \
    gnupg \
    openssl \
    sed \
    perl-native \
"
