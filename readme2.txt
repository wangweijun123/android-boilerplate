ApplicationModule  :

1 提供基础application
2 提供单例RibotsService (组件ApplicationComponent,也是作用域,得获取这个组件才能获取单例得ribotService)


ActivityComponent 是子组件,
// ActivityComponent这是子组件,
    // ConfigPersistentComponent是ActivityComponent父组件,
    // 而 ConfigPersistentComponent 又依赖ApplicationComponent

验证重要的类的实例情况
DataManager 在整个项目中单例
Retrofit$1@dd8b8f0 在整个项目中也是单例

内部类的形式
DaggerConfigPersistentComponent$ActivityComponentImpl@fb6b6ad
匿名的内部类(retrofit 反射实例化)
Retrofit$1@dd8b8f0


