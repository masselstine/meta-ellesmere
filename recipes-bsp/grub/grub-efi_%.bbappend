# Let base-files own /boot. This prevents hitting the filter in
# rpm-ostree for directories not acceptable to ostree use, in this
# case /boot.
DIRFILES = " \
    /boot/efi \
    /boot/efi/EFI \
    /boot/efi/EFI/BOOT \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Needed to support blscfg. Taken from Fedora grub packaging
# https://src.fedoraproject.org/rpms/grub2/tree/rawhide
SRC_URI += " \
    file://0021-blscfg-add-blscfg-module-to-parse-Boot-Loader-Specif.patch \
"

# Fedora uses /boot/grub2, use this
EXTRA_OECONF += " --with-grubdir=grub2"
