## 项目说明	

腾讯[Buyly SDK](https://bugly.qq.com/)的Cordova插件。



## 项目演示

[Demo](https://github.com/jasonz1987/ionic-bugly-sdk-demo)



## 项目使用

### 安装



```shell
cordova plugin add https://github.com/zch0928/cordova-plugin-bugly-sdk.git --variable ANDROID_APPID=value
```



### 调用



**初始化SDK**

*注意不同平台下的参数配置可能有差异*



```javascript

declare var Bugly:any;

Bugly.initSDK(function(success){
   console.log("初始化成功");
},function(err){
   console.log("初始化失败");
   console.log(err);
});

```

还有一些测试闪退的方法，具体参考demo里的代码。
