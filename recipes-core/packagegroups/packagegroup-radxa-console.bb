SUMMARY = "Organize packages to avoid duplication across all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# contains basic dependencies, that can work without graphics/display
RDEPENDS_packagegroup-radxa-console = "\
    alsa-utils-aplay \
    coreutils \
    cpufrequtils \
    htop \
    iptables \
    iproute2 \
    kernel-modules \
    networkmanager \
    networkmanager-nmtui \
    openssh-sftp-server \
    sudo \
    net-tools \
    findutils \
    "
#RDEPENDS_packagegroup-radxa-console = "\
#    alsa-utils-aplay \
#    coreutils \
#    cpufrequtils \
#    gnupg \
#    hostapd \
#    htop \
#    iptables \
#    iproute2 \
#    kernel-modules \
#    networkmanager \
#    networkmanager-nmtui \
#    openssh-sftp-server \
#    bluez5 \
#    dialog \
#    i2c-tools \
#    sudo \
#    net-tools \
#    findutils \
#    mraa-radxa-dev \
#    mraa-radxa-utils \
#    "
