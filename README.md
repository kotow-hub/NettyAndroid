## Netty for Android Client

 [ ![Download](https://api.bintray.com/packages/1024icloud/maven/netty-android/images/download.svg) ](https://bintray.com/1024icloud/maven/netty-android/_latestVersion)
 [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/FIRHQ/fir-cli/master/LICENSE.txt)
 [![Build Status](https://travis-ci.org/goAV/NettyAndroid.svg?branch=master)](https://travis-ci.org/goAV/NettyAndroid)
 
 
### Introduction

Don't worry about `socket` connect state. 

The link is broken or an exception is broken and the connect is resumed in 2 seconds 
or send some message to exit like [`ResponseHandler`](./app/src/main/java/com/goav/app/socket/ResponseHandler.java) `Logout`
    
* user library of [`netty-all:5.0.0.Alpha2`](https://github.com/netty/netty)
* parse data of [`gson-2.7`](http://mvnrepository.com/artifact/com.google.code.gson/gson/2.7)
    
### Action
* connect socket [ClientImpl](./netty-android/src/main/java/com/goav/netty/Handler/ClientImpl.java)
* network sniffing [ClientNetWorkIml](./netty-android/src/main/java/com/goav/netty/Handler/ClientNetWorkIml.java)
   
### Simple
See [`app->MainActivity`](./app/src/main/java/com/goav/app/MainActivity.java) and [`ClientBus`](./app/src/main/java/com/goav/app/socket/ClientBus.java)

* `init`
```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientBus.Initialization(this, "token", "host", 8080);
    }

    public void push(Object o) {
        ClientBus.request(o);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClientBus.Recycle();
    }
}
```

* `push` `use channel`
```java
ClientBus.request(o);
//or
ClientImpl.newInstances().request(messageSuper);
```

### Dependency

* Maven
```xml
<dependency>
  <groupId>com.goav</groupId>
  <artifactId>netty-android</artifactId>
  <version>0.1</version>
  <type>pom</type>
</dependency>
```

* Gradle
```groovy
compile 'com.goav:netty-android:0.1'
```


