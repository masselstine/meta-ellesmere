SUMMARY = "NSS module to look up from files in /usr/lib as well"
DESCRIPTION = "When installed, this package allows looking up users in \
               /usr/lib/passwd, and from respective files for all other \
               NSS maps."
HOMEPAGE = "https://github.com/flatcar/nss-altfiles"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb1949d8d807e528c1673da700aff41f"

SRC_URI = " \
    https://github.com/flatcar/nss-altfiles/archive/refs/tags/v${PV}.tar.gz#/${BPN}-${PV}.tar.gz \
    file://0001-build-sys-Inherit-LDFLAGS.patch \
    file://0003-deprecate-RES_USE_INET6.patch \
"
SRC_URI[sha256sum] = "2ab9ff43ccb5b6f3f5d18e7eddb160828551eab7ac71beeca10727a1e8dcc2fc"

inherit autotools autotools-brokensep

EXTRA_OECONF = " --with-types=all"
#EXTRA_OEMAKE = " -C ${S}"

ALLOW_EMPTY_${PN} = "1"

DEPENDS = " \
    virtual/libc \
    coreutils-native \
"
