DEPENDS:append:class-native = " \
    findlib-native \
"
DEPENDS:append:class-target = " \
    findlib-native \
    virtual/${TARGET_PREFIX}ocamlfind-meta \
"

# location where ocamlfind install puts the packages.
sitelibdir = "${ocamllibdir}/site-lib"
export sitelibdir

OCAMLFIND_CONF = "${STAGING_DIR_NATIVE}/etc/findlib.conf"
export OCAMLFIND_CONF

FILES:${PN} = " \
    ${sitelibdir}/*/*${SOLIBSDEV} \
    ${bindir}/* \
    ${sbindir}/* \
"
FILES:${PN}-dev = " \
    ${sitelibdir}/*/*.cm* \
    ${sitelibdir}/*/*.mli \
    ${sitelibdir}/*/META \
"
FILES:${PN}-staticdev = " \
    ${sitelibdir}/*/*.a \
"
FILES:${PN}-dbg = " \
    ${sitelibdir}/*/.debug \
    ${bindir}/.debug \
    ${sbindir}/.debug \
    /usr/src/debug \
"

do_amend_findlib_conf() {
    sed -i \
        -e 's|^destdir=.*|destdir="${D}${sitelibdir}"|' \
        -e 's|^path=.*|path="${OCAMLLIB}/site-lib:${STAGING_LIBDIR}/ocaml/site-lib"|' \
            "${OCAMLFIND_CONF}"
    if ! grep -q '^ldconf=' "${OCAMLFIND_CONF}"; then
        echo 'ldconf="ignore"' >> "${OCAMLFIND_CONF}"
    else
        sed -i -e 's|^ldconf=.*|ldconf="ignore"|' "${OCAMLFIND_CONF}"
    fi
}
do_prepare_recipe_sysroot[postfuncs] += "do_amend_findlib_conf"

do_install:prepend() {
    install -d ${D}${sitelibdir}
}
