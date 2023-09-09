require ocaml-common.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=4f72f33f302a53dc329f4d3819fe14f9"

SRC_URI = " \
    https://caml.inria.fr/pub/distrib/ocaml-5.0/ocaml-${PV}.tar.gz \
    file://0001-stdlib-Makefile-always-assume-long-shebang.patch \
"

MAA = " \
    file://shebang-use-env.patch \
"

SRC_URI[md5sum] = "c36d8352f572627fa9b92524edc2a638"
SRC_URI[sha256sum] = "969e1f7939736d39f2af533cd12cc64b05f060dbed087d7b760ee2503bfe56de"

S = "${WORKDIR}/ocaml-${PV}"

do_configure() {
    ./configure \
                --disable-graph-lib \
                --disable-ocamldoc \
                --disable-ocamltest \
                -prefix ${prefix} \
		-libdir ${libdir}/ocaml \
		-mandir ${datadir}/man \
                ${EXTRA_CONF}
}

do_compile() {
    oe_runmake world.opt
}

do_install() {
    oe_runmake DESTDIR="${D}" install
}
