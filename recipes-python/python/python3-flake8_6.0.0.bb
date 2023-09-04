SUMMARY = "A tool to check the style and quality of some python code."
DESCRIPTION = "flake8 is a python tool that glues together \
               pycodestyle, pyflakes, mccabe, and third-party plugins \
               to check the style and quality of some python code."
HOMEPAGE = "https://github.com/PyCQA/flake8"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75b26781f1adf1aa310bda6098937878"

DEPENDS += " \
"

PYPI_PACKAGE = "flake8"

SRC_URI[md5sum] = "dfb5c116f47553399a781c41a8c9d02b"
SRC_URI[sha256sum] = "c61007e76655af75e6785a931f452915b371dc48f56efd765247c8fe68f2b181"

inherit pypi setuptools3
