COMPATIBLE_MACHINE:x86-64 = "x86-64"

KBRANCH:x86-64  = "v6.1/standard/x86"

do_install:append() {
    # Can't be a link as the search code used in rpm-ostree doesn't look at links
    cp ${D}/${KERNEL_IMAGEDEST}/bzImage-${KERNEL_VERSION} ${D}/${KERNEL_IMAGEDEST}/vmlinuz
}

# The cgl might have been dropped in newer kernel-cache
KERNEL_FEATURES += "cgl/cfg/dmm.scc"

# Needed for cosa and supermin
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://9pfs.cfg \
    file://zram.cfg \
    file://qemu_fw_cfg.cfg \
"
