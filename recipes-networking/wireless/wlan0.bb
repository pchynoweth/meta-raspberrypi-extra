SUMMARY = "wlan0 configuration recipe"
DESCRIPTION = "Recipe to configure wlan0 for RaspberryPi"
LICENSE = "MIT"

SRC_URI = "file://85-wireless.network"

def get_files(d):
    return " ".join([
        "${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service",
        "${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf",
        "${systemd_unitdir}/network/85-wireless.network"
    ])

FILES:${PN} = "${@get_files(d)}"

S = "${WORKDIR}"

do_install() {
       install -d ${D}${sysconfdir}/wpa_supplicant
       install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
       install -d ${D}${systemd_unitdir}/network

       cat > ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf << EOF
ctrl_interface=/var/run/wpa_supplicant
ctrl_interface_group=0
update_config=1

network={
    ssid="${WIFI_SSID}"
    psk="${WIFI_PASSWORD}"
    proto=RSN
    key_mgmt=WPA-PSK
 }
EOF

    ln -sf ${systemd_system_unitdir}/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
    install -m 644 ${S}/85-wireless.network ${D}${systemd_unitdir}/network
}

RDEPENDS:${PN}:append = " wpa-supplicant"

inherit features_check

REQUIRED_DISTRO_FEATURES = "systemd"