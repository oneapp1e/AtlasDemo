#!/bin/bash

echo "(1) 发布远程bundle"

echo "(2) 上传 tpatch"

adb push app/build/outputs/remote-bundles-debug/libbm_hd_mlr_firstbundle.so /sdcard/Android/data/com.v.mlr/cache

echo "(4) 点击动态部署完成安装 "
