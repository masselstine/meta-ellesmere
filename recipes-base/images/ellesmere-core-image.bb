#
# Copyright (C) 2023 Wind River Systems, Inc.
#
DESCRIPTION = "A minimal image that will be used by live/installer/container and other images"

require ellesmere-core-image.inc

#TARGET_CORE_BOOT ?= " \
#    packagegroup-core-boot \
#    dhcpcd \
#    systemd-extra-utils \
#"
TARGET_CORE_BOOT ?= ""

# Control the installed packages strictly
#WRTEMPLATE_IMAGE = "0"

IMAGE_INSTALL = "\
    ${TARGET_CORE_BOOT} \
    busybox \
    busybox-syslog \
    openssh \
    ca-certificates \
"

# - No packagegroup-core-base-utils which corresponds to busybox
#   function since it is busybox based.
# - The ostree are not needed for container image.
#IMAGE_INSTALL:remove = "\
#    packagegroup-core-base-utils \
#    ${@bb.utils.contains('IMAGE_ENABLE_CONTAINER', '1', 'ostree ostree-upgrade-mgr linux-firmware', '', d)} \
#    ${@bb.utils.contains('IMAGE_ENABLE_CONTAINER', '1', 'u-boot u-boot-uenv', '', d)} \
#    ${@bb.utils.contains('IMAGE_ENABLE_CONTAINER', '1', 'efibootmgr', '', d)} \
#"

# For ostree
#IMAGE_INSTALL:append = " ${@bb.utils.contains('OSTREE_BOOTLOADER', 'u-boot', 'u-boot-uenv', '', d)}"

# For nxp-s32g2xx
#IMAGE_INSTALL:append:nxp-s32g2xx = " u-boot-s32 atf-s32g"

NO_RECOMMENDATIONS = "1"

# Remove debug-tweaks and x11-base
#IMAGE_FEATURES:remove = "debug-tweaks x11-base"

# Enable dhcpcd service if NetworkManager is not installed.
#ROOTFS_POSTPROCESS_COMMAND += "enable_dhcpcd_service; "
#enable_dhcpcd_service() {
#    if [ ! -e ${IMAGE_ROOTFS}${sbindir}/NetworkManager \
#        -a -f ${IMAGE_ROOTFS}${systemd_unitdir}/system/dhcpcd.service ]; then
#        ln -sf ${systemd_unitdir}/system/dhcpcd.service \
#            ${IMAGE_ROOTFS}${sysconfdir}/systemd/system/multi-user.target.wants/dhcpcd.service
#    fi
#}
