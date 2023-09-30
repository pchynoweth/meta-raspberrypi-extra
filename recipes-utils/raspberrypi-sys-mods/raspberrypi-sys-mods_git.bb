DESCRIPTION = "raspberrypi-sys-mods files"
SECTION = "raspberrypi-sys-mods"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=b5c3c8bb856fffaa0b83eaf6c7fffd08"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/RPi-Distro/raspberrypi-sys-mods.git;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

PACKAGES:prepend = "rpi-firstboot-script "

FILES:rpi-firstboot-script = " \
    ${nonarch_libdir}/raspberrypi-sys-mods/firstboot \
    "

do_install() {
    install -d ${D}${nonarch_libdir}/raspberrypi-sys-mods

	install -m 744 ${S}/usr/lib/raspberrypi-sys-mods/firstboot ${D}${nonarch_libdir}/raspberrypi-sys-mods
}

RDEPENDS:rpi-firstboot-script = " bash e2fsprogs-resize2fs parted util-linux-findmnt whiptail"