SUMMARY = "Nemomobile's sensorfw"
HOMEPAGE = "https://git.merproject.org/mer-core/sensorfw"
LICENSE = "LGPL-2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

SRC_URI = "git://github.com/AsteroidOS/sensorfw.git;protocol=https \
           file://sensorfwd.service"
SRCREV = "847718face9eff033ed1dbe5ac26c9882550f97e"
PR = "r1"
PV = "+git${SRCPV}"
S = "${WORKDIR}/git"

inherit qmake5

EXTRA_QMAKEVARS_PRE = "CONFIG+=autohybris"

do_configure_prepend() {
    sed -i '/include( doc\/doc.pri )/d' ../git/sensorfw.pro
    sed -i 's:-L/usr/lib ::' ../git/core/hybris.pro
}

do_install_append() {
    install -d ${D}/etc/sensorfw/ ${D}/lib/systemd/system/multi-user.target.wants/
    cp ${S}/config/sensord-hybris.conf ${D}/etc/sensorfw/primaryuse.conf
    cp ../sensorfwd.service ${D}/lib/systemd/system/sensorfwd.service
    ln -s ../sensorfwd.service ${D}/lib/systemd/system/multi-user.target.wants/
}

DEPENDS += "qtbase"

FILES_${PN} += "/usr/lib/sensord-qt5/*.so /usr/lib/sensord-qt5/testing/*.so /lib/systemd/system"
FILES_${PN}-dbg += "/usr/share/sensorfw-tests/ /usr/lib/sensord-qt5/.debug/ /usr/lib/sensord-qt5/testing/.debug/"
FILES_${PN}-dev += "/usr/share/qt5/mkspecs/ /usr/include"
