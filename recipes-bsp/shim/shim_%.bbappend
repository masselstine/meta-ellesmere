# Let base-files own /boot. This prevents hitting the filter in
# rpm-ostree for directories not acceptable to ostree use, in this
# case /boot.
DIRFILES = " \
    /boot/efi \
    /boot/efi/EFI \
    /boot/efi/EFI/BOOT \
"

RDEPENDS:${PN} += "base-files"

# Better practice and matches cosa search path
EFI_TARGET = "/boot/efi/EFI/${DISTRO_CODENAME}"

#do_install:append() {
#    install -d -m0700 ${D}/boot/efi/EFI/BOOT/ellesmere
#    mv ${D}/boot/efi/EFI/BOOT/mmx64.efi ${D}/boot/efi/EFI/BOOT/ellesmere/.
#    mv ${D}/boot/efi/EFI/BOOT/shimx64.efi ${D}/boot/efi/EFI/BOOT/ellesmere/.    
#}
