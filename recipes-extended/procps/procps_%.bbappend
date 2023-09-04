# Work around alternatives hack. Without this the
# main psmisc rpm would contain these and these
# related RPMs would not exist as they would be empty.
# This would cause dependency issues.
FILES:${PN}-ps = "${base_bindir}/ps"

do_install:append() {
    # Remove in favor of the version in util-linux
    rm ${D}${base_bindir}/kill
}
