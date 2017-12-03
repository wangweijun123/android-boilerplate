package uk.co.ribot.androidboilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.SyncService;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;
import uk.co.ribot.androidboilerplate.injection.module.ApplicationModule;
import uk.co.ribot.androidboilerplate.util.RxEventBus;

// 在这里产生的实例是在整个应用中是单例, 因为这个ApplicationComponent实例是单例
// component声明的单例，不能说明module中所能提供的实例就是单例，当然scope域是一致(如果定义)
// ConfigPersistentComponent 依赖 ApplicationComponent,所以在组件中必须定义自己的能力，
// 也就是自己能new 什么样实例，这种能力在对应module和构造函数中找
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();// 告诉依赖组件，我能提供Application实例
    RibotsService ribotsService();// 告诉依赖组件，我能提供RibotsService实例
    PreferencesHelper preferencesHelper();// 告诉依赖组件，我能提供PreferencesHelper实例
    DatabaseHelper databaseHelper();// 告诉依赖组件，我能提供DatabaseHelper实例
    DataManager dataManager();// 告诉依赖组件，我能提供DataManager实例
    RxEventBus eventBus();// 告诉依赖组件，我能提供RxEventBus实例

}
