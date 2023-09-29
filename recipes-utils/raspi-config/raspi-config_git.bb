DESCRIPTION = "raspi-config files"
SECTION = "raspi-config"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=87c25b1874010cc3f7a251d7956b4e57"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/RPi-Distro/raspi-config.git;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

FILES:${PN} = " \
    ${nonarch_libdir}/raspi-config \
    "

do_install() {
    install -d ${D}${nonarch_libdir}/raspi-config

	install -m 744 ${S}/usr/lib/raspi-config/init_resize.sh ${D}${nonarch_libdir}/raspi-config
}

RDEPENDS:${PN} = " bash e2fsprogs-resize2fs libnewt parted util-linux-findmnt"