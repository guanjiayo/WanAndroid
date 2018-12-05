# WanAndroid
基于WanAndroid API 开发的应用案例

目前主力在下图的开发版项目架构搭建中,这个项目后面完成

---

# 商城项目架构搭建

## 项目结构图

> 基本功能已开发完成
> 目前只有登录注册相关接口页面可用,由于没服务器,暂时实现相关页面功能

![开发版项目架构](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/test.png)
 
 ## 相关页面效果图
 
![首页](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/home.png)
 
![分类页面](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/category.png)
 
![详情页面](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/detail.png)

![个人页面](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/me.png)

![订单中心页](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/order.png)

![支付页](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/pay.png)

![历史记录页](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/search.png)

![设置页面](https://github.com/guanjiayo/WanAndroid/blob/master/WanAndroid/pic/setting.png)

## 商城项目内容简述

# MiMarker案例

### 用到的技术

1. MVP架构
2. kotlin语言(anko库,kotlin-extentions)
3. Dagger注入框架
4. 动态代理模式隔离第三方库
5. 自定义AOP库实现登录拦截和动态权限申请
6. 首页使用 ViewPager+ LazyViewPager + LazyFragment完美解决预加载问题
7. Rx系列(Rxlifecycle+RxBus+RxJava+RxKotlin)
8. Retrofit + okhttp 网络库二次封装
9. 自定义 ConverterFactory 处理统一的网络请求回调
10. 尺寸限定符屏幕适配
11. Theme优化App启动黑白屏,启动速度
12. ARouter 实现项目组件化
13. 封装分享库(ShareSDK),当作工具类API使用  (分享的内容要用bean类重新封装一下)

### 需要完善的任务
 1. 学完Java服务器,把服务器搭建上去,将分类页面,登录注册等相关页面数据改回来
 2. 项目开发完,除了主程序以外的模块,全部做成aar包,有利于提升App的构建速度
 3. 引入自己封装的BaseAdapter库,移除随手实现的
 4. 看下能不能再拓展一个ToolBarActivity
 5. 基于《阿里Android开发手册》优化代码规范
 6. Lottie动画实现启动页面
 7. 封装WebView基类,更好的与前端交互
 8. 将一些过时的API,警告的代码修复,以及Java代码改成Kotlin
 9. 封装SQL库(分库处理当一个人即是用户也是会员的场景)
 10. 重构UI
 > 1. 把Style的分类整合好,以便于维护

 12. 项目国际化优化
 12. 新增夜间模式(用户可自定义时间段,项目自动进入夜间模式)
 13. 重写BottomNavigationBar,轮播图,现在的库虽然好用,但是可拓展性还不够高
 14. 参考 https://github.com/gyf-dev/ImmersionBar 完善我们的状态栏工具类(异形屏兼容还没做)
 15. 参考 https://developer.android.google.cn/topic/performance/ 性能优化(电量,网络,图片,内存,apk压缩,进程包活)

##  BaseLibrary项目基类封装模块

> 1. 工具类按需从我们的总库拖进去
> 2. 自定义控件在需要的模块单独实现
> 3. 子类依赖库做成aar库,可以提高项目构建速度
> 4. isolation包将网络处理库,图片处理库...第三方的库,用动态接口代理完全隔离(后面怎么坑都不怕)
> 5. 一些通用的控件样式,drawable等定义在这里(TabLayout,RecycleView,自定义Item,HeadBar,多状态视图,加载弹窗)

---

# 模块简述

## Provider模块

1. "将一些公共的数据,路由路径常量,EventBus的接受事件统一放在这里处理"

## 主模块

> 项目混淆,多渠道打包,版本号升级,还有一些基础配置

1. 空壳模块,只有一个MyApplication

## 首页模块

1. VLayout等各种LayoutManage
2. 广告模块,轮播广告,文本广告
3. Fragment 管理类,HomeActivity 活动弹窗
4. 启动页广告及启动页优化


## 用户模块
> 目前使用的是自己封装的Retrofit库,但是RxJava解析Json有点问题,这里先Persenter粗糙解决

 1. 注册,登录,忘记密码,重置密码页面
 2. "我的"页面,退出登录,地址管理

## 商品模块

 1. 商品分类页面,商品搜索页面,商品详情,购物车(sku模块)

## 支付模块

 1. 选择支付类型页面
 2. 支付流程实现(微信,支付宝,银联等)

## 消息模块

 1. "推送消息(sdk,Socket)"

## 地图模块(待开发)

> 实现一些基本的地图功能(二次开发)


## 订单状态模块(我的订单)
 1. "支付模块" 请求支付后,服务器返回的几种状态页面处理
 2. 收货地址相关

## 即时通讯模块(待开发)

1. 接入通信SDK(环信)
2. 完全自定义聊天界面,避免跟换SDK又要重写一套聊天UI的麻烦


## 音视频模块(待开发)

1. 直播
> 两个版本,
> 1. 接入SDK实现的直播项目
> 2. 自己搭建WebRtc服务器实现,基于FFmpeg实现直播效果

2. 仿主流社交软件(抖音,Nice等)进行音视频处理
> 将学过的NDK开发技术练熟:
> Jpeg库,Fmod库1,FFmeng库,openCV,openGL库等
> 美颜,聊天动效等实现

## 串口模块(待开发)

> 目前没设备,后面自己买个草莓派搞吧


## 文档浏览模块(待开发)

> 封装PDF,Word文档,MarkDown文件浏览模块