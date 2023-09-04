# Work around alternatives, we want this to be THE command
do_install:append() {
    mv ${D}${base_sbindir}/ip.${PN} ${D}${base_sbindir}/ip
}

FILES:${PN}-ip = "${base_sbindir}/ip ${sysconfdir}/iproute2"
