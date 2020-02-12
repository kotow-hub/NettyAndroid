## Netty for Android Client

[![GitHub tag](https://img.shields.io/github/tag/goAV/NettyAndroid.svg)](https://github.com/goAV/NettyAndroid/tags)
[![GitHub release](https://img.shields.io/github/release/goAV/NettyAndroid.svg)](https://github.com/goAV/NettyAndroid/releases/latest)
[![Github commits (since latest release)](https://img.shields.io/github/commits-since/goav/nettyandroid/latest.svg)](https://github.com/goAV/NettyAndroid/commits/kotlin-master)

[![NettyAndroid](https://jitpack.io/v/kotow-hub/NettyAndroid.svg)](https://jitpack.io/#kotow-hub/NettyAndroid)
 [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/FIRHQ/fir-cli/master/LICENSE.txt)
[![Build Status](https://travis-ci.org/kotow-hub/NettyAndroid.svg?branch=kotlin-master)](https://travis-ci.org/kotow-hub/NettyAndroid)
### Introduction

* user library of [`io.netty:netty-all:4.1.16.Final`](https://github.com/netty/netty)
* parse data of [`gson-2.7`](http://mvnrepository.com/artifact/com.google.code.gson/gson/2.7)

### Simple
See [`app->NettyTest.kt`](./app/src/main/java/com/goav/app/NettyTest.kt)

### Gradle
```groovy
	dependencies {
	        compile 'com.github.kotow-hub:NettyAndroid:latest.release'
	}
```

### PS 

* look at the [`EncodeHandler`](netty-android/src/main/java/com/goav/netty/Handler/EncodeHandler.kt)

The content’s length and `int`’s length are the length of request’s body, see the [`size + 4`](netty-android/src/main/java/com/goav/netty/Handler/EncodeHandler.kt#L27)


* look at the [`DecodeHandler`](netty-android/src/main/java/com/goav/netty/Handler/DecodeHandler.kt)

The content's length and `int`'s length are the length of response's body, see the [`'in'.readInt() - 4`](netty-android/src/main/java/com/goav/netty/Handler/DecodeHandler.kt#L40)


