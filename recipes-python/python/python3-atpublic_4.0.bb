SUMMARY = "Documents the publicness of the names in your module."
DESCRIPTION = "This library provides two very simple decorators that \
               document the publicness of the names in your \
               module. They keep your module’s __all__ in sync so you \
               don’t have to."
HOMEPAGE = "https://public.readthedocs.io/en/stable/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3dc063bf020e46b0400d126ca9e8e5cb"

DEPENDS += " \
    python3-pdm-backend-native \
"

PYPI_PACKAGE = "atpublic"

SRC_URI[md5sum] = "3aa37a0ec1ec9a0c93018af06a846b7e"
SRC_URI[sha256sum] = "0f40433219e124edf115c6c363808ca6f0e1cfa7d160d86b2fb94793086d1294"

inherit pypi python_setuptools_build_meta
