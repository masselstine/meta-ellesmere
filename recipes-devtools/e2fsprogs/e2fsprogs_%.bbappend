# As part of the alternatives hack

# Reverse this action taken in the main recipe
do_install:append:class-target() {
        mv ${D}${base_sbindir}/mke2fs.e2fsprogs ${D}${base_sbindir}/mke2fs
        mv ${D}${base_sbindir}/mkfs.ext2.e2fsprogs ${D}${base_sbindir}/mkfs.ext2
        mv ${D}${base_sbindir}/tune2fs.e2fsprogs ${D}${base_sbindir}/tune2fs

        mv ${D}${base_bindir}/chattr.e2fsprogs ${D}${base_bindir}/chattr
        mv ${D}${base_bindir}/lsattr.e2fsprogs ${D}${base_bindir}/lsattr
}

# But keep their same packaging
FILES:e2fsprogs-mke2fs += "${base_sbindir}/mke2fs"
FILES:e2fsprogs-tune2fs += "${base_sbindir}/tune2fs"
