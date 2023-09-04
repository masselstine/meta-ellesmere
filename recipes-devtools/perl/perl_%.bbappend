perl_package_preprocess () {
        # Fix up installed configuration
        sed -i -e "s,${D},,g" \
               -e "s,${DEBUG_PREFIX_MAP},,g" \
               -e "s,--sysroot=${STAGING_DIR_HOST},,g" \
               -e "s,-isystem${STAGING_INCDIR} ,,g" \
               -e "s,${STAGING_LIBDIR},${libdir},g" \
               -e "s,${STAGING_BINDIR},${bindir},g" \
               -e "s,${STAGING_INCDIR},${includedir},g" \
               -e "s,${STAGING_BINDIR_NATIVE}/perl-native/,${bindir}/,g" \
               -e "s,${STAGING_BINDIR_NATIVE}/,,g" \
               -e "s,${STAGING_BINDIR_TOOLCHAIN}/${TARGET_PREFIX},${bindir},g" \
               -e 's:${RECIPE_SYSROOT}::g' \
            ${PKGD}${bindir}/h2xs \
            ${PKGD}${bindir}/h2ph \
            ${PKGD}${bindir}/pod2man \
            ${PKGD}${bindir}/pod2text \
            ${PKGD}${bindir}/pod2usage \
            ${PKGD}${bindir}/podchecker \
            ${PKGD}${libdir}/perl5/${PV}/${TARGET_ARCH}-linux/CORE/config.h \
            ${PKGD}${libdir}/perl5/${PV}/${TARGET_ARCH}-linux/CORE/perl.h \
            ${PKGD}${libdir}/perl5/${PV}/${TARGET_ARCH}-linux/CORE/pp.h \
            ${PKGD}${libdir}/perl5/${PV}/Config.pm \
            ${PKGD}${libdir}/perl5/${PV}/${TARGET_ARCH}-linux/Config.pm \
            ${PKGD}${libdir}/perl5/${PV}/${TARGET_ARCH}-linux/Config.pod \
            ${PKGD}${libdir}/perl5/${PV}/${TARGET_ARCH}-linux/Config_git.pl \
            ${PKGD}${libdir}/perl5/${PV}/${TARGET_ARCH}-linux/Config_heavy.pl \
            ${PKGD}${libdir}/perl5/${PV}/ExtUtils/Liblist/Kid.pm \
            ${PKGD}${libdir}/perl5/${PV}/FileCache.pm \
            ${PKGD}${libdir}/perl5/${PV}/pod/*.pod \
            ${PKGD}${libdir}/perl5/config.sh
}
