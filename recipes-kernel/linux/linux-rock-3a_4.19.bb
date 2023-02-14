DESCRIPTION = "Linux kernel for Rock-3A"

inherit kernel
inherit kernel-yocto
inherit python3native
require recipes-kernel/linux/linux-yocto.inc

# We need mkimage for the overlays
DEPENDS += "openssl-native u-boot-mkimage-radxa-native"
#do_compile[depends] += "u-boot-mkimage-radxa-native:do_populate_sysroot"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	git://github.com/radxa/kernel.git;branch=stable-4.19-rock3; \
	file://0003-enable-rock-3a-console-debug.patch\
"

SRCREV = "${AUTOREV}"
LINUX_VERSION = "4.19.193"

# Override local version in order to use the one generated by linux build system
# And not "yocto-standard"
LINUX_VERSION_EXTENSION = ""
PR = "r1"
PV = "${LINUX_VERSION}"

# Include only supported boards for now
COMPATIBLE_MACHINE = "(rk3568)"
deltask kernel_configme
deltask kernel_configcheck

# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
do_patch:append() {
	for s in `grep -rIl python ${S}/scripts`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done
}

do_compile:append() {
	oe_runmake dtbs
}
do_deploy:append() {
	install -d ${DEPLOYDIR}/overlays
	install -m 644 ${WORKDIR}/linux-rock_3a_rk3568-standard-build/arch/arm64/boot/dts/rockchip/overlay/* ${DEPLOYDIR}/overlays
}
PACKAGES += "${PN}-overlays"
FILES_${PN}-overlays = "/boot/overlays/*"
