# Work around alternatives hack. Without this the
# main psmisc rpm would contain these and these
# related RPMs would not exist as they would be empty.
# This would cause dependency issues.
FILES:${PN}-ping = "${base_bindir}/ping"
FILES:${PN}-ping6 = "${base_bindir}/ping6"
FILES:${PN}-hostname = "${base_bindir}/hostname"
FILES:${PN}-ifconfig = "${base_sbindir}/ifconfig"
FILES:${PN}-traceroute = "${bindir}/traceroute"
FILES:${PN}-logger = "${bindir}/logger"
FILES:${PN}-syslogd = "${base_sbindir}/syslogd"
FILES:${PN}-ftp = "${bindir}/ftp"
FILES:${PN}-tftp = "${bindir}/tftp"
FILES:${PN}-telnet = "${bindir}/telnet"
FILES:${PN}-ftpd = "${bindir}/ftpd"
FILES:${PN}-ftpd-dbg = "${bindir}/.debug/ftpd"
FILES:${PN}-inetd = "${bindir}/inetd"

do_install:append() {
    # remove in favor of version in iputils
    rm ${D}${base_bindir}/ping
}
