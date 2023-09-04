SUMMARY = "This project has been renamed and re-published as pdm-backend"
DESCRIPTION = "This is the backend for PDM projects, while you can \
               also use it alone. It reads the metadata of PEP 621 \
               format and coverts it to Core metadata."
HOMEPAGE = "https://pdm.fming.dev/latest/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a564297b3c5b629a528b92fd8ff61ea"

DEPENDS += " \
"

PYPI_PACKAGE = "pdm-pep517"

SRC_URI[md5sum] = "58c2e34f3d3e04089c052515e0a4a256"
SRC_URI[sha256sum] = "7f49121e70b42dca296fac962210dd2da07a39575fc5673137ad661633b2cf3f"

inherit pypi python_setuptools_build_meta

BBCLASSEXTEND = "native"