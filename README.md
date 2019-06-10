# History
历史和我

主要展示历史大事件和我自己提交的事件


暂时先把页面全部写出来，遇到数据会使用假数据；后面才去调接口

### 为什么写这个项目？

1. 为了更好的学习 kotlin 语言，所以使用了 kotlin 语言编写；

2. 学习 Android 新技术，使用的是 androidx 依赖包；

3. 该项目主要针对那些初级基础，看了文档却又难以实践成功，所以我就自己写了初步的 Demo，尽量把各个技术都用上；

4. 目前没有使用搭建框架，我想等到页面画完调数据的时候开始（主要是没想好用什么，嘿嘿~）

### 用到哪些技术

1. 主页面用到了 viewpager+Fragment+BottomNavigationViewEx 的开源项目，其实是想用 YoKeyword 开源的
 [Fragmentation](https://github.com/YoKeyword/Fragmentation) 项目，可能由于作者最近较忙，
 没有更新适配最新的 androidx。如果作者后期更新，我可能会考虑重新引用。
 
2. 时间轴页面 用的是 RecycleView 嵌套 RecycleView；

3. 分类页面是用 viewpager2+Fragment 技术，fragment 的页面嵌套 ScrollView；

4. 我的大事件页面显示用户自己记录的事件，有 FloatingActionButton 进行跳转至记录页面；
   
   - 数据库（未完成）

5. 探索页面展示附近的博物馆，点击该博物馆可以自动规划路线
   
   - 高德地图获取POI 数据并显示 marker 标记时，采用 AsyncTask，后期准备试试 Coroutine()；
   - 点击博物馆后跳转页面规划路线（参照高德地图的 Demo）


### 出现的问题
- 第一次进入探索页面，地图是最大化，没有缩放好比例
- 点击某个 marker 后还会留下新的marker，应该是我新增加了 marker 的原因
- 点击 marker 后显示的详情框是自带的，以后会加上自定义：左边显示图片右边显示详情

- 高德地图是如何实现海量点的绘制性能、绘制路径的思路、如何实现地图缩放比例


### 引用第三方库
好的项目肯定离不开团队合作，所以要写出好项目，肯定要借鉴其他项目和思想。在此感谢以下第三方开源库
- [Glide](https://github.com/bumptech/glide)
- [BottomNavigationView](https://github.com/ittianyu/BottomNavigationViewEx)
- [高德地图](https://lbs.amap.com/)
 
