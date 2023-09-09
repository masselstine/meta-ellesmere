require ocaml-5.0.0.inc

EXTRA_CONF:class-target = "--disable-ocamldoc --disable-ocamltest --host ${TARGET_SYS} --target ${TARGET_SYS}"
DEPENDS:class-target:append = " ocaml-cross-${TARGET_ARCH}"

#do_configure:class-target:prepend() {
#	export SAK_CC='$(BUILD_CC)'
#	export SAK_CFLAGS='$(OC_CFLAGS) ${BUILD_CFLAGS} $(OC_CPPFLAGS) $(CPPFLAGS)'
#	export SAK_LINK='$(BUILD_CC) $(OC_EXE_LDFLAGS) $(OC_CFLAGS) $(BUILD_CFLAGS) $(OC_LDFLAGS) $(BUILD_LDFLAGS) $(OUTPUTEXE)$(1) $(2)'
#}

do_compile:class-target() {
    # build 'sak' to run on the build host, needed to create runtime/build_config.h
    export SAK_CC='$(BUILD_CC)'
    export SAK_CFLAGS='$(OC_CFLAGS) $(BUILD_CFLAGS) $(OC_CPPFLAGS) $(CPPFLAGS)'
    export SAK_LINK='$(BUILD_CC) $(OC_EXE_LDFLAGS) $(OC_CFLAGS) $(BUILD_CFLAGS) $(OC_LDFLAGS) $(BUILD_LDFLAGS) $(OUTPUTEXE)$(1) $(2)'

    # build 'ocomlyacc' to run on the build host, needed to build ocamllex, should use 'ocamlyacc' from
    # ocaml-cross instead? most likely.
    rm -f ${B}/yacc/ocamlyacc ${B}/yacc/*.o
    oe_runmake CC='${BUILD_CC}' CFLAGS='${BUILD_CFLAGS}' LDFLAGS='${BUILD_LDFLAGS}' ocamlyacc

    # build 'ocamlrun*' which are run on the build host as part of the build, most likely not
    # an approach that will work for cross building for ARM?
    oe_runmake MKEXE='$(BUILD_CC) $(OC_CFLAGS) $(BUILD_CFLAGS) $(OC_LDFLAGS) $(BUILD_LDFLAGS)' BYTECCLIBS='-lpthread -lm -ldl' runtime-all

    # actually build ocaml, use ocamlyacc form ocaml-native, despite the attempt above
    # to avoid this, ocamlyacc seems to be overwritten in the while building world
    oe_runmake OCAMLYACC=ocamlyacc world
    oe_runmake OCAMLYACC=ocamlyacc opt

    # rebuild 'ocamlrun*' and 'ocamlyacc' for the target
    rm -f ${B}/yacc/ocamlyacc ${B}/yacc/*.o
    rm -f ${B}/runtime/*.o ${B}/runtime/ocamlrun ${B}/runtime/ocamlruni ${B}/runtime/ocamlrund
    oe_runmake ocamlyacc
    oe_runmake runtime/ocamlrun runtime/ocamlruni runtime/ocamlrund
}

FILES:${PN}-staticdev:class-target += " \
    ${prefix}/lib/ocaml/dynlink/dynlink.a \
    ${prefix}/lib/ocaml/unix/unix.a \
    ${prefix}/lib/ocaml/unix/unix.a \
    ${prefix}/lib/ocaml/runtime_events/runtime_events.a \
    ${prefix}/lib/ocaml/runtime_events/runtime_events.a \
    ${prefix}/lib/ocaml/threads/threads.a \
    ${prefix}/lib/ocaml/threads/threads.a \
    ${prefix}/lib/ocaml/str/str.a \
    ${prefix}/lib/ocaml/str/str.a \
    ${prefix}/lib/ocaml/*.a \
"

# Needed to build findlib-cross
BBCLASSEXTEND = "native"
