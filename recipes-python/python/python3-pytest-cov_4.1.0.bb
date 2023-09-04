SUMMARY = "Coverage plugin for pytest."
DESCRIPTION = "This pytest plugin produces coverage reports."
HOMEPAGE = "https://github.com/pytest-dev/pytest-cov"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbc4e25353c748c817db2daffe605e43"

DEPENDS += " \
"

PYPI_PACKAGE = "pytest-cov"

SRC_URI[md5sum] = "593d6458518d96ebc63ea69eaf17840f"
SRC_URI[sha256sum] = "3904b13dfbfec47f003b8e77fd5b589cd11904a21ddf1ab38a64f204d6a10ef6"

inherit pypi setuptools3

RDEPENDS:${PN} = " \
    python3-pytest \
    python3-coverage \
"
