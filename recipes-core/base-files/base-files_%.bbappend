# Make conditional on feature 'usrmerge'
do_install:append() {
	ln -sf /usr/bin ${D}/bin
	ln -sf /usr/lib ${D}/lib
	ln -sf /usr/sbin ${D}/sbin

	# See volatile-binds as this and the changes
	# there are related. ??
	#ln -sf /var/srv ${D}/srv

	# supermin will fail if these are missing
	#install -m0555 -d ${D}/proc
	#install -m0555 -d ${D}/sys
	#install -m0777 -d ${D}/tmp

	# Needed to underpin efivars etc., used by bootupctl in cosa build
	# this package is excluded from the ostree sanity filter in rpm-ostree
	install -m 555 -d ${D}/boot

        # Used/needed by rpm-ostree
	install -d ${D}/usr/share/empty
	install -d ${D}/usr/lib/ostree-boot

	# Needed by coreos-check-cgroups.service via coreos-check-cgroups
	# Prob should not stay in base-files longterm
	install -m0755 -d ${D}/run/motd.d

	# Needed by coreos-ignition-write-issues.service
	# Prob should not stay in base-files longterm
	install -m0755 -d ${D}${sysconfdir}/issue.d
}

#PACKAGES =+ "${PN}-extra "
#FILES:${PN}-extra = "/proc /sys"
