#!/bin/sh


#### 测试渠道
pack_debug_fun() {
    ./gradlew uploadArchives
}

### 预发布渠道
pack_uat_fun() {
    ./gradlew clean build bintrayUpload -PbintrayUser=momentslz -PbintrayKey=b24b935aec050ebd50d7f9b0b5678e0474307d6b -PdryRun=false
}

### 正式渠道
pack_release_fun() {
    ./gradlew uploadArchives
}

#### 帮助
if [[ "$1" == "-h" ]] || [[ "$1" == "-help" ]]
then
  echo "-d,-debug : 打正式测试渠道包"
  echo "-u,-uat : 打正式预发布渠道包"
  echo "-r,-release : 打正式渠道包"
  echo "如果打当前渠道使用命令 : ./gradlew assembleRelease"
  exit 0
fi

#### 打测试debug渠道包
if [[ "$1" == "-d" ]] || [[ "$1" == "-debug" ]]
then
  pack_debug_fun
  exit 0
fi

#### 打测试uat渠道包
if [[ "$1" == "-u" ]] || [[ "$1" == "-uat" ]]
then
  pack_uat_fun
  exit 0
fi

#### 设置制定正式渠道
if [[ "$1" == "-r" ]] || [[ "$1" == "-release" ]]
then
  pack_release_fun
  exit 0
fi

echo "参数不存在"