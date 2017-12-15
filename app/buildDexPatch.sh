#!/bin/bash

echo "(1) 修改bundle的版本和源码"
echo "    修改versionName"


echo "(2)  重新构建apk， 生成patch 包"
echo "     注意此处DapVersion DversionName 这两个参数指定的都是宿主的版本名"

./gradlew clean assembleDebug -DapVersion=1.0.0 -DversionName=1.0.0

echo "(3) 上传 tpatch"

//zy
adb push app/build/outputs/tpatch-debug/dexpatch-*.json /sdcard/Android/data/com.v.mlr/cache
adb push app/build/outputs/tpatch-debug/*@*.tpatch /sdcard/Android/data/com.v.mlr/cache

echo "(4) 点击动态DexPatch完成安装 "
