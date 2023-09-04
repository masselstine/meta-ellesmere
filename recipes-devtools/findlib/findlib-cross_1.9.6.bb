require findlib-${PV}.inc

PROVIDES = " \
    virtual/${TARGET_PREFIX}ocamlfind-meta \
"

PN = "findlib-cross-${TARGET_ARCH}"

do_install:append() {
    rm -rf ${D}${bindir}
    rm -rf ${D}${mandir}
    rm -rf ${D}${sysconfdir}
    rm ${D}${libdir}/../ocaml/topfind
}

# Ignore how TARGET_ARCH is computed.
TARGET_ARCH[vardepvalue] = "${TARGET_ARCH}"

inherit cross
