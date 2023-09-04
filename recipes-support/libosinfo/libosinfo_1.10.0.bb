SUMMARY = "A library for managing OS information for virtualization"
DESCRIPTION = "libosinfo is a library that allows virtualization \
               provisioning tools to determine the optimal device \
               settings for a hypervisor/operating system \
               combination."
HOMEPAGE = "https://libosinfo.org"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

SRC_URI = " \
    https://releases.pagure.org/${PN}/${PN}-${PV}.tar.xz \
"

SRC_URI[md5sum] = "9c2059648bf6f5610ba838a1fb1a84ad"
SRC_URI[sha256sum] = "a252e00fc580deb21da0da8c0aa03b8c31e8440b8448c8b98143fab477d32305"

inherit meson pkgconfig gettext

DEPENDS = " \
    glib-2.0 \
    cmake-native \
    gobject-introspection-native \
    hwdata \
    libsoup \
    libxslt \
"
