SUMMARY = "Tools to access and modify virtual machine disk images"
DESCRIPTION = "guestfs-tools is a set of tools that can be used to \
               make batch configuration changes to guests, get disk \
               used/free statistics (virt-df), perform backups and \
               guest clones, change registry/UUID/hostname info, build \
               guests from scratch (virt-builder) and more."
HOMEPAGE = "https://libguestfs.org"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit autotools pkgconfig gettext ocaml findlib autotools-brokensep

CFLAGS:prepend = " --sysroot=${RECIPE_SYSROOT} "

OCAMLFLAGS = " -cc '${CC}'"
export OCAMLFLAGS

DEPENDS = " \
    libguestfs \
    libxml2 \
    jansson \
    libvirt \
    ncurses \
    xz \
    bison-native \
    flex-native \
    zip \
    unzip \
    xorriso-native \
    libosinfo \
    ocaml \
    ocaml-cross-${TARGET_ARCH} \
"

RDEPENDS:${PN} = " \
    curl \
    gpgme \
    xz \
    qemu \
"


SRC_URI = " \
    https://download.libguestfs.org/guestfs-tools/1.51-development/guestfs-tools-${PV}.tar.gz \
    file://0001-Disable-build-time-tests.patch \
    file://0002-sysprep-disable-pods-created-for-docs.patch \
"
SRC_URI[md5sum] = "6c0445112a393c676ed99b390ba712c9"
SRC_URI[sha256sum] = "5b38f57d77d998a96fa32de3f12460088a89c134a6bb8721c9b8f85053613794"

#EXTRA_OEMAKE = "GIR_EXTRA_LIBS_PATH=${RECIPE_SYSROOT}/${prefix}/lib/x86_64-wrs-linux/ocaml"

do_install:append() {
    rm -rf ${D}${prefix}/lib
}

FILES:${PN} += " \
    ${datadir}/bash-completion \
    ${sysconfdir}/virt-builder \
"
