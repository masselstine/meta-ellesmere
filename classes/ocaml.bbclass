# OCAMLLIB
# The directory containing the OCaml standard library. (If OCAMLLIB is not set,
# CAMLLIB will be used instead.) Used to locate the ld.conf configuration file
# for dynamic loading
OCAMLLIB:class-native = "${STAGING_LIBDIR_NATIVE}/ocaml"
OCAMLLIB:class-cross = "${STAGING_LIBDIR_NATIVE}/ocaml"
OCAMLLIB = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}/ocaml"
export OCAMLLIB

# OCAML_TOPLEVEL_PATH
# Path to the top-level (path to libraries & such).
OCAML_TOPLEVEL_PATH:class-native = "${STAGING_LIBDIR_NATIVE}"
OCAML_TOPLEVEL_PATH:class-cross = "${STAGING_LIBDIR_NATIVE}"
OCAML_TOPLEVEL_PATH = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}"
export OCAML_TOPLEVEL_PATH

# OCAML toolchain
# Common variables used in Makfiles for OCAML projects.
OCAMLMKLIB="ocamlmklib -ldopt '--sysroot=${STAGING_DIR_NATIVE}'"
OCAMLC:class-target="ocamlc -cc '${CC} -fPIC'"
OCAMLOPT:class-target="ocamlopt -cc '${CC} -fPIC'"
OCAMLMKLIB:class-target="ocamlmklib -ocamlc ocamlc -ocamlopt ocamlopt -ldopt '--sysroot=${STAGING_DIR_TARGET} ${LDFLAGS}'"

OCAMLPATH = ""
OCAMLPATH:class-target = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}/ocaml:${STAGING_LIBDIR}/${TARGET_SYS}/ocaml"
export OCAMLPATH

# Override Makefile variables with oe_runmake.
EXTRA_OEMAKE:append = ' \
    OCAMLMKLIB="${OCAMLMKLIB}" \
'
EXTRA_OEMAKE:append:class-target = ' \
    OCAMLC="${OCAMLC}" \
    OCAMLOPT="${OCAMLOPT}" \
    OCAMLPATH="${OCAMLPATH}" \
'

DEPENDS:append:class-native = " \
    ocaml-native \
"
DEPENDS:append:class-cross = " \
    ocaml-native \
"
DEPENDS:append:class-target = " \
    virtual/${TARGET_PREFIX}ocamlc \
    virtual/${TARGET_PREFIX}ocamlopt \
"

ocamllibdir = "${libdir}/ocaml"
export ocamllibdir

# cross environment ocaml headers to interface with C sources.
ocamlincdir = "${STAGING_LIBDIR_NATIVE}/${TARGET_SYS}/ocaml"
export ocamlincdir

OPN ?= "${@d.getVar('BPN').replace('ocaml-','')}"
