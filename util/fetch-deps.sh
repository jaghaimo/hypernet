#!/usr/bin/env bash

set -eu

# Starsector
if [ ! -f "libs/starfarer.api.jar" ]; then
    tmp_dir=$(mktemp -d -t libs-ss-XXXXXXXXXX)
    pushd $tmp_dir
    wget https://s3.amazonaws.com/fractalsoftworks/starsector/starsector_linux-0.9.1a-RC8.zip
    unzip *
    popd
    cp $tmp_dir/*/starfarer.api.jar libs/
fi
