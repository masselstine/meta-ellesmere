do_install:append:class-target() {
    rm ${D}${base_bindir}/groups

    # Remove in favor of version in util-linux
    rm ${D}${base_bindir}/chfn

    # Remove in favor of version in util-linux
    rm ${D}${base_bindir}/chsh

    # Remove in favor of version in util-linux
    rm ${D}${base_sbindir}/nologin

    # Remove in favor of version in util-linux
    rm ${D}${base_bindir}/su
}
