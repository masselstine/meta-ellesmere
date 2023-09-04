SUMMARY = "Automated decryption framework"
DESCRIPTION = "Clevis is a framework for automated decryption. It \
               allows you to encrypt data using sophisticated \
               unlocking policies which enable decryption to occur \
               automatically. \
               \
               The clevis package provides basic encryption/decryption \
               policy support.  Users can use this directly; but most \
               commonly, it will be used as a building block for other \
               packages. For example, see the clevis-luks and \
               clevis-dracut packages for automatic root volume \
               unlocking of LUKSv1/LUKSv2 volumes during early boot."
HOMEPAGE = "https://github.com/latchset/clevis"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

PV = "19"
SRCREV = "0bb86d3714befc29b6de5e1d4f7911635eeab56e"
SRC_URI = "git://github.com/latchset/clevis.git;branch=master;protocol=https"

inherit meson pkgconfig

S = "${WORKDIR}/git"

DEPENDS = " \
    jansson \
    asciidoc \
    openssl \
    jq-native \
    cryptsetup \
    cryptsetup-native \
    dracut \
    curl \
    systemd \
    jose \
    cmake-native \
    luksmeta \
"

RDEPENDS:${PN} += "\
    bash \
"

FILES:${PN} += " \
    ${datadir}/bash-completion \
"
