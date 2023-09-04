SUMMARY = "A library for reading and writing Windows Registry "hive" binary files"
DESCRIPTION = "This is a self-contained library for reading and \
               writing Windows Registry "hive" binary files."
HOMEPAGE = "https://github.com/libguestfs/hivex"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f23a23b996e90732d119709c8aca08db"

SRC_URI = " \
    https://download.libguestfs.org/hivex/hivex-1.3.23.tar.gz \
    file://0001-don-t-bother-attempting-to-build-test-images.patch \
"

SRC_URI[md5] = "d41d8cd98f00b204e9800998ecf8427e"
SRC_URI[sha256sum] = "40cf5484f15c94672259fb3b99a90bef6f390e63f37a52a1c06808a2016a6bbd"

inherit autotools pkgconfig gettext

DEPENDS = "perl-native"

RDEPENDS:hivex += "bash"

EXTRA_OECONF = " \
    --disable-perl \
    --disable-python \
    --disable-ocaml \
    --disable-ruby \
    --disable-largefile \
"
