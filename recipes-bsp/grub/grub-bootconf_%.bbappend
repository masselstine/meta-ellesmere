# Let base-files own /boot. This prevents hitting the filter in
# rpm-ostree for directories not acceptable to ostree use, in this
# case /boot.
DIRFILES = " \
    /boot/EFI \
    /boot/EFI/BOOT \
"

RDEPENDS:${PN} += "base-files"
