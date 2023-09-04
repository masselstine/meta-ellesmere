FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Needed to support blscfg. Taken from Fedora grub packaging
# https://src.fedoraproject.org/rpms/grub2/tree/rawhide
SRC_URI += " \
    file://0021-blscfg-add-blscfg-module-to-parse-Boot-Loader-Specif.patch \
"

# Fedora uses /boot/grub2, use this
EXTRA_OECONF += " --with-grubdir=grub2"
