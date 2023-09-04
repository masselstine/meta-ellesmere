do_install:append:class-target() {
    ln -sf gawk ${D}${base_bindir}/awk
}
