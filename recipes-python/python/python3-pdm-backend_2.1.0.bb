SUMMARY = "The build backend used by PDM that supports latest packaging standards."
DESCRIPTION = "This is the backend for PDM projects that is \
               fully-compatible with PEP 517 spec, but you can also \
               use it alone. It reads the metadata of PEP 621 format \
               and coverts it to Core metadata."
HOMEPAGE = "https://github.com/pdm-project/pdm-backend"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a564297b3c5b629a528b92fd8ff61ea"

DEPENDS += " \
"

PYPI_PACKAGE = "pdm_backend"

SRC_URI[md5sum] = "df6744bfe617af0ab8b3c49d5821609b"
SRC_URI[sha256sum] = "b853cb049244b697f5ebe9028d34418449130d6e12adf71b7661a4b95c342d59"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"