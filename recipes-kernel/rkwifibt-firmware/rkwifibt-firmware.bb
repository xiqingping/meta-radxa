# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd

SUMMARY = "Rockchip WIFI/BT firmware files"
SECTION = "kernel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=9645f39e9db895a4aa6e02cb57294595"

SRCREV = "3277c78514fce8964c25172a5e8c7102bd0c17f6"
SRC_URI = "git://github.com/radxa/rkwifibt.git;protocol=https"

S = "${WORKDIR}/git"

inherit allarch deploy

do_install() {
	install -d ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/firmware/broadcom/AW-NB197/bt/BCM4343A1_001.002.009.1008.1024.hcd \
		${D}/lib/firmware/brcm/bcm43438a1.hcd
	install -m 0644 ${S}/firmware/broadcom/AP6236/*/* \
		-t ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/firmware/broadcom/AP6255/*/* \
		-t ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/firmware/broadcom/AP6256/bt/* \
		-t ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/firmware/broadcom/AP6256/wifi/* \
		${D}/lib/firmware/brcm/
	install -m 0644 ${S}/firmware/broadcom/AP6356/*/* \
		-t ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/firmware/broadcom/AP6398S/*/* \
		-t ${D}/lib/firmware/brcm/

	# let's use the firmware for AP6212 from Infineon / Cypress
	install -m 0644 ${S}/firmware/cypress/wifi/cyfmac43430-sdio.bin ${D}/lib/firmware/brcm/brcmfmac43430-sdio.bin
	install -m 0644 ${S}/firmware/cypress/wifi/cyfmac43430-sdio.txt ${D}/lib/firmware/brcm/brcmfmac43430-sdio.txt
	install -m 0644 ${S}/firmware/cypress/wifi/cyfmac43430-sdio.clm_blob ${D}/lib/firmware/brcm/brcmfmac43430-sdio.clm_blob

	install -d ${D}${base_libdir}/firmware/rtlbt/
	install -m 0644 ${S}/realtek/RTL8723DS/* -t ${D}${base_libdir}/firmware/rtlbt/
	install -m 0644 ${S}/realtek/RTL8723DU/* -t ${D}${base_libdir}/firmware/
	install -m 0644 ${S}/realtek/RTL8821CU/* -t ${D}${base_libdir}/firmware/
}

PACKAGES =+ " \
	${PN}-ap6212a1-wifi \
	${PN}-ap6212a1-bt \
	${PN}-ap6236-wifi \
	${PN}-ap6236-bt \
	${PN}-ap6255-wifi \
	${PN}-ap6255-bt \
	${PN}-ap6256-wifi \
	${PN}-ap6256-bt \
	${PN}-ap6356-wifi \
	${PN}-ap6356-bt \
	${PN}-ap6398s-wifi \
	${PN}-ap6398s-bt \
	${PN}-rtl8723ds-bt \
	${PN}-rtl8723du-bt \
	${PN}-rtl8821cu-bt \
"

FILES:${PN}-ap6212a1-wifi = " \
	lib/firmware/brcm/brcmfmac43430-sdio* \
"

FILES:${PN}-ap6212a1-bt = " \
	lib/firmware/brcm/bcm43438a1.hcd \
"

FILES:${PN}-ap6236-wifi = " \
	lib/firmware/brcm/fw_bcm43436b0.bin \
	lib/firmware/brcm/nvram_ap6236.txt \
"

FILES:${PN}-ap6236-bt = " \
	lib/firmware/brcm/BCM4343B0.hcd \
"

FILES:${PN}-ap6255-wifi = " \
	lib/firmware/brcm/fw_bcm43455c0_ag.bin \
	lib/firmware/brcm/fw_bcm43455c0_ag_p2p.bin \
	lib/firmware/brcm/nvram_ap6255.txt \
"

FILES:${PN}-ap6255-bt = " \
	lib/firmware/brcm/BCM4345C0.hcd \
"

FILES:${PN}-ap6256-wifi = " \
	lib/firmware/brcm/fw_bcm43456c5_ag.bin \
	lib/firmware/brcm/nvram_ap6256.txt \
"

FILES:${PN}-ap6256-bt = " \
	lib/firmware/brcm/BCM4345C5.hcd \
"

FILES:${PN}-ap6356-wifi = " \
	lib/firmware/brcm/fw_bcm4356a2_ag.bin \
	lib/firmware/brcm/nvram_ap6356.txt \
"

FILES:${PN}-ap6356-bt = " \
	lib/firmware/brcm/BCM4356A2.hcd \
"

FILES:${PN}-ap6398s-wifi = " \
	lib/firmware/brcm/fw_bcm4359c0_ag.bin \
	lib/firmware/brcm/fw_bcm4359c0_ag_p2p.bin \
	lib/firmware/brcm/nvram_ap6398s.txt \
"

FILES:${PN}-ap6398s-bt = " \
	lib/firmware/brcm/BCM4359C0.hcd \
"

FILES:${PN}-rtl8723ds-bt = " \
	${base_libdir}/firmware/rtlbt/rtl8723d_config \
	${base_libdir}/firmware/rtlbt/rtl8723d_fw \
"

FILES:${PN}-rtl8723du-bt = " \
	${base_libdir}/firmware/rtl8723du_config \
	${base_libdir}/firmware/rtl8723du_fw \
"

FILES:${PN}-rtl8821cu-bt = " \
	${base_libdir}/firmware/rtl8821cu_config \
	${base_libdir}/firmware/rtl8821cu_fw \
"

FILES:${PN} = "*"

# Make it depend on all of the split-out packages.
python () {
    pn = d.getVar('PN')
    firmware_pkgs = oe.utils.packages_filter_out_system(d)
    d.appendVar('RDEPENDS_' + pn, ' ' + ' '.join(firmware_pkgs))
}

INSANE_SKIP_${PN} += "arch"
