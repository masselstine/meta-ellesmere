SUMMARY = "A useful set of utilities for managing cloud images."
DESCRIPTION = "The tasks associated with image bundling are often \
               tedious and repetitive. The cloud-utils package \
               provides several scripts that wrap the complicated \
               tasks with a much simpler interface."
HOMEPAGE = "https://launchpad.net/cloud-utils/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://launchpad.net/cloud-utils/trunk/0.31/+download/${BPN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "197c14ed7f2b566e5cd2ef03a46057d536a3e7bcb0b37a7a27fc378e691bd41d"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -m 0555 -D -t ${D}${base_bindir} ${S}/bin/*

    # Exclude Ubuntu specific tools
    rm -f ${D}${base_bindir}/*ubuntu*

    # Exclude euca2ools wrappers
    rm -f ${D}${base_bindir}//cloud-publish-*
}

RDEPENDS:${PN} = " \
    bash \
    gawk \
    e2fsprogs \
    file \
    python3-core \
    qemu \
    util-linux \
"

RDEPENDS:${PN}-growpart = " \
    bash \
    gawk \
    util-linux \
"

PACKAGES =+ "${PN}-growpart"

FILES:${PN}-growpart = "${base_bindir}/growpart"
