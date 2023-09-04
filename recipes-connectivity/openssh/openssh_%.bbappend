# Work around alternatives hack. Without this the
# main psmisc rpm would contain these and these
# related RPMs would not exist as they would be empty.
# This would cause dependency issues.
FILES:${PN}-scp = "${bindir}/scp"
FILES:${PN}-ssh = "${bindir}/ssh"
