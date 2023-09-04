# /srv handled differently than in default volatile-binds recipe
# to avoid /srv which is not compatible with ostree
VOLATILE_BINDS = "\
    /var/volatile/lib /var/lib\n\
    /var/volatile/cache /var/cache\n\
    /var/volatile/spool /var/spool\n\
    /var/volatile/srv /var/srv\n\
"

# See change in base-files too
FILES:${PN}:remove = "${servicedir}"
FILES:${PN} += "/var/srv"

do_install:append() {
    rmdir ${D}${servicedir}
    install -d ${D}/var/srv
}
