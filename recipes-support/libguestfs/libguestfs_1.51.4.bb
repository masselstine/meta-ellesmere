SUMMARY = "Access and modify virtual machine disk images"
DESCRIPTION = "libguestfs is a set of tools for accessing and \
               modifying virtual machine (VM) disk images. You can use \
               this for viewing and editing files inside guests, \
               scripting changes to VMs, monitoring disk used/free \
               statistics, creating guests, P2V, V2V, performing \
               backups, cloning VMs, building VMs, formatting disks, \
               resizing disks, and much more."
HOMEPAGE = "https://libguestfs.org"
LICENSE = "GPL-2.0-only & LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools pkgconfig gettext gobject-introspection autotools-brokensep ocaml findlib

DEPENDS = " \
    qemu \
    glibc \
    cpio \
    gperf \
    flex-native \
    bison-native \
    libxml2 \
    ncurses \
    augeas \
    xz \
    zstd \
    jansson \
    gperf-native \
    xorriso-native \
    file \
    fuse \
    hivex \
    qemu-system-native \
    libconfig \
    clevis \
"

EXTRA_OECONF = " \
    --without-java \
    --disable-erlang \
    --disable-golang \
    --disable-daemon \
    --disable-appliance \
    --disable-perl \
    --disable-vala \
"

RDEPENDS:${PN} = " \
    bash \
"


SRC_URI = " \
    https://download.libguestfs.org/1.51-development/libguestfs-${PV}.tar.gz \
    file://0001-Disable-build-time-tests.patch \
"
SRC_URI[md5sum] = "8350326818b02af770174a3569b5d88c"
SRC_URI[sha256sum] = "72b950395ea6ec95de4ecd23c5b63d89b10fcebfa10f5e03779b61fd292e83b4"

EXTRA_OEMAKE = "GIR_EXTRA_LIBS_PATH=${B}/lib/.libs"

do_install:append() {
    mv ${D}/${RECIPE_SYSROOT_NATIVE}/${prefix}/lib/* ${D}/${prefix}/lib/.
    find ${D} -type d -empty -delete
    #rm ${D}/${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/META
}

#PACKAGES =+ "ocaml-${PN} ocaml-${PN}-staticdev"

FILES:${PN}:append = " \
    ${datadir}/bash-completion \
    ${sysconfdir} \
    ${prefix}/lib/python3.11 \
    ${prefix}/lib/libguestfs-gobject-1.0.so.0.0.0 \
    ${prefix}/lib/libguestfs.so.0.513.0 \
    ${prefix}/lib/*/ocaml/stublibs \
    ${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/*.cma \
    ${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/*.cmi \
    ${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/META \
"

FILES:${PN}-dev:append = " \
    ${includedir} \
    ${prefix}/lib/girepository-1.0 \
    ${prefix}/lib/pkgconfig \
    ${prefix}/lib/libguestfs-gobject-1.0.so \
    ${prefix}/lib/libguestfs-gobject-1.0.so.0 \
    ${prefix}/lib/libguestfs.so \
    ${prefix}/lib/libguestfs.so.0 \
"

FILES:${PN}-staticdev:append = " \
    ${prefix}/lib/libguestfs.a \
    ${prefix}/lib/libguestfs-gobject-1.0.a \
    ${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/*.a \
    ${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/*.cmx \
    ${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/*.cmxa \
    ${prefix}/lib/x86_64-wrs-linux/ocaml/guestfs/*.mli \
"

RDEPENDS:${PN} += " \
    ocaml \
"

RDEPENDS:${PN}-staticdev += " \
    ocaml \
"

#FILES:ocaml-${PN} = " \
#    ${prefix}/lib/*/ocaml/guestfs/META \
#    ${prefix}/lib/*/ocaml/guestfs/*.cmi \
#    ${prefix}/lib/*/ocaml/guestfs/*.cma \
#    ${prefix}/lib/*/ocaml/stublibs \
#"

#FILES:ocaml-${PN}-staticdev = " \
#    ${prefix}/lib/*/ocaml/guestfs/*.a \
#    ${prefix}/lib/*/ocaml/guestfs/*.mli \
#    ${prefix}/lib/*/ocaml/guestfs/*.cmx \
#    ${prefix}/lib/*/ocaml/guestfs/*.cmxa \
#"

# Always used in cross build so runtime deps are useless currently
#INSANE_SKIP:${PN} += "file-rdeps"
#INSANE_SKIP:${PN}-staticdev += "file-rdeps"
