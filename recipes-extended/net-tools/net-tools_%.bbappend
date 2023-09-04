do_install:append() {
    # remove in favor of the version in inetutils
    rm ${D}${base_bindir}/hostname
    rm ${D}${base_bindir}/dnsdomainname
}
