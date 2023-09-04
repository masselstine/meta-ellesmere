SUMMARY = "Translates human readable Butane Configs into machine readable Ignition Configs"
DESCRIPTION = "Butane translates human readable Butane Configs into \
               machine readable Ignition Configs. See the getting \
               started guide for how to use Butane and the \
               configuration specifications for everything Butane \
               configs support."
HOMEPAGE = "https://github.com/coreos/butane"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/import/LICENSE;md5=d2794c0df5b907fdace235a619d80314"

GO_IMPORT = "import"

SRCREV = "b28e42e9610575cb4a319bfcc6447660d6abf847"
PV = "0.18.0"
SRC_URI = "git://github.com/coreos/butane.git;branch=main;protocol=https"

inherit go goarch

RDEPENDS:${PN}-dev = " \
    bash \
"

GO_EXTRA_LDFLAGS = "-w -X github.com/coreos/butane/internal/version.Raw=${PV}"

do_compile() {
	cd ${S}/src/import

	export GO111MODULE=on
	export GOFLAGS=-mod=vendor
	export CGO_ENABLED=0
	version=${PV}

	NAME=butane

	${GO} build -o ${B}/bin/${NAME} ${GO_LDFLAGS} internal/main.go
}

do_install() {
	install -d ${D}${bindir}
	install ${B}/bin/butane ${D}${bindir}/butane
	ln -sfr ${D}${bindir}/butane ${D}${bindir}/fcct
}