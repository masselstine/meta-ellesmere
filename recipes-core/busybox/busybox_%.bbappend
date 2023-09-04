do_install:append() {
    # Remove in favor of version available in bash
    rm ${D}${base_bindir}/sh
}
