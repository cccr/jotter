#!/bin/bash
# installs to /opt/git
# existing versions are not overwritten/deleted
# seamless upgrades/downgrades
yum install unzip curl-devel expat-devel gettext-devel openssl-devel zlib-devel autoconf gcc
yum install perl-ExtUtils-MakeMaker -y
git_version=2.3.5
mkdir /opt/git
wget -N https://github.com/git/git/archive/v${git_version}.zip
unzip -oq ./v${git_version}.zip -d /opt/git
cd /opt/git-v{git_version}
make configure
./configure --prefix=/usr
make all doc info
sudo make install install-doc install-html install-info
git --version
