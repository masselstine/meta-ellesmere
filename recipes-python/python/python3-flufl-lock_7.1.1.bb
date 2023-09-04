SUMMARY = "NFS-safe file locking with timeouts for POSIX and Windows."
DESCRIPTION = "The flufl.lock library provides an NFS-safe file-based \
               locking algorithm influenced by the GNU/Linux open(2) \
               manpage, under the description of the O_EXCL option."
HOMEPAGE = "https://flufllock.readthedocs.io/en/stable/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b3e59b5d01d81495b9c4f73741f192bd"

DEPENDS += " \
    python3-pdm-pep517-native \
"

PYPI_PACKAGE = "flufl.lock"

SRC_URI[md5sum] = "bdb8ea0df50bc2eec2c6dc4609a7d6b5"
SRC_URI[sha256sum] = "af14172b35bbc58687bd06b70d1693fd8d48cbf0ffde7e51a618c148ae24042d"

inherit pypi python_setuptools_build_meta

RDEPENDS:${PN} = " \
    python3-psutil \
    python3-atpublic \
"
