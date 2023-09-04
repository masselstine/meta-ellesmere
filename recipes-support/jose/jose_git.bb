SUMMARY = "Tools for JSON Object Signing and Encryption (JOSE)"
DESCRIPTION = "Jose is a command line utility for performing various \
               tasks on JSON Object Signing and Encryption (JOSE) \
               objects. JosÃ© provides a full crypto stack including \
               key generation, signing and encryption."
HOMEPAGE = "https://github.com/latchset/jose"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=34400b68072d710fecd0a2940a0d1658"

PV = "11"
SRCREV = "145c41a4ec70c15f6f8aa12a915e16cb60f0991f"
SRC_URI = "git://github.com/latchset/jose.git;branch=master;protocol=https"

inherit meson pkgconfig

S = "${WORKDIR}/git"

DEPENDS = " \
    openssl \
    asciidoc \
    jansson \
    zlib \
"

do_install:append() {
	rm -rf ${D}${datadir}/licenses
}