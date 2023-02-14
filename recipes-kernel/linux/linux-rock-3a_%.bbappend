
do_compile:prepend() {
    sed -i \
        -e 's|CONFIG_RTL8852BE=m|#lCONFIG_RTL8852BE=m|g' \
        -e 's|CONFIG_BCMDHD_FW_PATH.*$|CONFIG_BCMDHD_FW_PATH="/lib/firmware/brcm/brcmfmac43456-sdio.bin"|g' \
        -e 's|CONFIG_BCMDHD_NVRAM_PATH.*$|CONFIG_BCMDHD_NVRAM_PATH="/lib/firmware/brcm/brcmfmac43456-sdio.txt"|g' \
        ${B}/.config
}

