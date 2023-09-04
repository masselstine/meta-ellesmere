# Setup alternatives as 'vim' is used in scriptlets
do_install:append:class-target() {
    ln -sf vim.tiny ${D}${base_bindir}/vim
}
