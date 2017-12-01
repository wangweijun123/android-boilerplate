package uk.co.ribot.androidboilerplate.injection.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    // 注意这里没有定义singleton，因为这里不需要，就算再次调用这个方法
    // 还是返回同一个实例
    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext // module中以自定义注解的形式注解实例，其实就是一个名字，不是scope
    Context provideContext() {
        Log.i("xxxx", "##provideContext##");
        return mApplication;
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

}
