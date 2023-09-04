require ocaml-5.0.0.inc

DEPENDS += " \
    virtual/${TARGET_PREFIX}binutils \
    virtual/${TARGET_PREFIX}gcc \
    virtual/libc \
    libgcc \
"

PROVIDES = " \
    virtual/${TARGET_PREFIX}ocamlc \
    virtual/${TARGET_PREFIX}ocamlopt \
    virtual/${TARGET_PREFIX}ocamlyacc \
"

PN = "ocaml-cross-${TARGET_ARCH}"

COMPATIBLE_HOST = "x86_64.*-linux"
COMPATIBLE_MACHINE:x86-64 = "(.*)"

EXTRA_CONF = " \
    -host ${BUILD_SYS} \
    -target ${TARGET_SYS} \
    -bindir ${bindir} \
"
do_configure:prepend:x86-64() {
    export CC="${TARGET_PREFIX}gcc -fPIC --sysroot=${STAGING_DIR_TARGET}"
    export AS="${TARGET_PREFIX}as"
    export ASPP="${TARGET_PREFIX}gcc -c -fPIC"
}
#    ./configure \
#                --disable-graph-lib \
#                --disable-ocamldoc \
#		--disable-ocamltest \
#                -prefix ${prefix} \
#                -bindir ${bindir} \
#                -libdir ${libdir}/ocaml \
#                -mandir ${datadir}/man \
#		-host ${BUILD_SYS} \
#                -target ${TARGET_SYS} \
#                ${EXTRA_CONF}
#}

#do_compile() {
#    oe_runmake OCAMLLIB="${STAGING_LIBDIR_NATIVE}/ocaml" world.opt
#}

do_install:append() {
#    rm "${D}${bindir}/ocamlrun"
    sed -i -e 's@#!.*/\(.[^/]\)@#!/usr/bin/env \1@' ${D}${libdir}/ocaml/camlheader
    sed -i -e 's@#!.*/\(.[^/]\)@#!/usr/bin/env \1@' ${D}${libdir}/ocaml/camlheaderi
    sed -i -e 's@#!.*/\(.[^/]\)@#!/usr/bin/env \1@' ${D}${libdir}/ocaml/camlheaderd

#    (cd ${D}${prefix}/bin/${TARGET_SYS}
#     for f in *; do
#        mv ${f} ${TARGET_PREFIX}${f}
#     done
#     find * -type l -exec sh -c 'rm ${0} && ln -sf ${0}.opt ${0}' {} \;)
}

# Ignore how TARGET_ARCH is computed.
TARGET_ARCH[vardepvalue] = "${TARGET_ARCH}"

# The native compiler will be installed in the sysroot to serve as the cross
# compiler with the rest of the cross build environment. It is already stripped
# and debugging is not a concern here.
INSANE_SKIP:${PN} += "already-stripped"

inherit cross
