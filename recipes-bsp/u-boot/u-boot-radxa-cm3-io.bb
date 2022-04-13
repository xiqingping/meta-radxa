DEFAULT_PREFERENCE = "1"

DESCRIPTION = "Radxa CM3 IO U-Boot"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

include u-boot-rockpi.inc

SRC_URI = " \
	git://github.com/radxa/u-boot.git;branch=stable-4.19-rock3; \
	file://0001-Use-local-command.h-file-instead-of-system-file.patch \
	file://0002-Suppress-maybe-uninitialized-warning.patch \
	file://${MACHINE}/0001-to-avoid-warnings-when-compiling-with-GCC-8.1.patch \
	file://${MACHINE}/boot.cmd \
	file://${MACHINE}/uEnv.txt \
	file://${MACHINE}/idbloader.img \
	file://${MACHINE}/u-boot.itb \
"

SRCREV = "693c4cd017e57a6af8e471494be5e8780c041b08"

do_compile () {

}

do_compile_append () {

}

do_deploy_append() {
	if [ -f "${WORKDIR}/${MACHINE}/u-boot.itb" ]; then
		install -D -m 644 ${WORKDIR}/${MACHINE}/u-boot.itb ${DEPLOYDIR}/
	fi
	if [ -f "${WORKDIR}/${MACHINE}/idbloader.img" ]; then
		install -D -m 644 ${WORKDIR}/${MACHINE}/idbloader.img ${DEPLOYDIR}/
	fi
}

addtask compile
