SUMMARY = "Utility for storing small metadata in the LUKSv1 header"
DESCRIPTION = "LUKSMeta is a command line utility for storing small \
               portions of metadata in the LUKSv1 header for use \
               before unlocking the volume."
HOMEPAGE = "https://github.com/latchset/luksmeta"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4e9dfcb21c14eb0c40ae8ba436d3bb7a"

PV = "9"
SRCREV = "3e3cba3944703b12b0010154654b032c78aaa94c"
SRC_URI = "git://github.com/latchset/luksmeta.git;branch=master;protocol=https"

inherit autotools pkgconfig

S = "${WORKDIR}/git"

DEPENDS = " \
    cryptsetup \
    cryptsetup-native \
    asciidoc \
"

#do_install:append() {
#	rm -rf ${D}${datadir}/licenses
#}