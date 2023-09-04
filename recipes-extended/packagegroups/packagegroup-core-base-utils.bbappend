# Cleanup required due to alternatives hack fallout.
#
# inetutils-ping doesn't exist any longer, ping is in iputils
#
RDEPENDS:${PN}:remove = " \
    inetutils-ping \
"
