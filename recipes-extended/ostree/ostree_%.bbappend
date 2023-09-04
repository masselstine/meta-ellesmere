PACKAGECONFIG = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'selinux smack', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd libmount', '', d)} \
    glib \
    gpgme \
    curl \
    libarchive \
    rofiles-fuse \
    dracut \
"