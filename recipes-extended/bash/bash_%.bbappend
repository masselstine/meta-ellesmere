# We need 'sh' for install scriptlets
do_install:append:class-target() {
    ln -sf bash ${D}${base_bindir}/sh
}

FILES:${PN} += " \
    ${base_bindir}/sh \
"
