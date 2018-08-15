#  MVP RxJava Retrofit Dagger2

## MVP

## 网络请求模块

## 生命周期管理

## 权限管理
    RxPermission 管理Andorid 权限，callback 回掉会返回是否允许权限，拒绝权限，拒绝并不在询问
## 图片管理
    1.当项目遇到切换图片网络框架的时候，Glide切换到Picasso时，改动会非常大，因此统一管理。

## 工具类
    1.LogUtil 日志的工具类,配置gradle,项目上线改为relese版的时候自动关闭log日志
    2.NotchUtil 刘海屏幕的适配工具类，目前支持华为，小米，vivo,Oppo等设备,如果还有其他设备感谢pr
