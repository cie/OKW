#!/usr/bin/env sh
# Installs Doxygen

set -ex

DOXYGEN_VER=doxygen-1.8.15
DOXYGEN_TAR=${DOXYGEN_VER}.linux.bin.tar.gz
DOXYGEN_URL="http://doxygen.nl/files//${DOXYGEN_TAR}"

mkdir -p $HOME/local/bin
wget -O - "${DOXYGEN_URL}" | tar xz -C ${TMPDIR-/tmp} ${DOXYGEN_VER}/bin/doxygen
mv ${TMPDIR-/tmp}/${DOXYGEN_VER}/bin/doxygen $HOME/local/bin/doxygen
export PATH=$PATH:$HOME/local/bin