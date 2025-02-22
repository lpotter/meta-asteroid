SUMMARY = "Nemomobile's MCE."
HOMEPAGE = "https://git.merproject.org/mer-core/mce"
LICENSE = "LGPL-2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git.merproject.org/mer-core/mce.git;protocol=https \
    file://0001-Fixes_build.patch \
    file://0002-Display-Sets-default-brightness-to-100.patch \
    file://0003-Display-Sets-dimming-timeout-to-10-sec-and-blanking-.patch \
    file://0004-event-input-Double-tap-emulation-Adapts-the-state-ma.patch \
    file://mce.service"
SRC_URI_append_qemux86 = " file://0005-Keep-screen-on-by-default-on-emulator.patch"
SRCREV = "0510206026438b9de0d6a0f4d46e0c5674d37398"
PR = "r1"
PV = "+git${SRCPV}"
S = "${WORKDIR}/git"

DEPENDS += "glib-2.0 libdsme libiphb systemd dbus-glib dbus libngf pkgconfig-native"

do_install() {
    oe_runmake install DESTDIR=${D}
    install -m 0644 ${WORKDIR}/mce.service ${D}${systemd_system_unitdir}
    rm -r ${D}/var/run/
}

FILES_${PN} += " /run/mce /lib/systemd/system "
FILES_${PN}-dbg += "/usr/lib/mce/modules/.debug"
