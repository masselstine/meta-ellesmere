# Need to have mkdir and others during 'rpm-ostree compose'
base_bindir_progs = ""
sbindir_progs = ""

do_install:append:class-target () {
    mv ${D}${base_bindir}/chroot ${D}${base_sbindir}/.
    mv ${D}${base_bindir}/mktemp.${PN} ${D}${base_bindir}/mktemp

    # remove in favor of the version in procps
    rm ${D}${base_bindir}/uptime

    # remove in favor of the verison in inetutils
    rm ${D}${base_bindir}/hostname

    # remove in favor of the version in util-linux
    rm ${D}${base_bindir}/kill
}
