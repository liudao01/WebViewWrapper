# WebViewWrapper

本项目是 webveiw 配置好设置的项目  H5框架  注意点

0. 此项目使用到DSBridge , 后面我看下做一个jsbridge的
1. 做成依赖库,方便其他项目引用
2. 通过fragment加载,业务代码不和webview控件相关联
3. 加载状态需要封装 加载失败, 重新加载,加载中, 加载失败需要重新加载
4. 支持和JS交互,支持java调用JS并有回调, 支持JS调用Android并有回调
5. 和JS交互单独封装,不在业务逻辑层做,解耦

#  使用篇

引入依赖库

[![](https://jitpack.io/v/liudao01/WebViewDemo.svg)](https://jitpack.io/#liudao01/WebViewDemo)



#  原理篇
