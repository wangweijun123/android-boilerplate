package uk.co.ribot.androidboilerplate.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import uk.co.ribot.androidboilerplate.data.local.DatabaseHelper;
import uk.co.ribot.androidboilerplate.data.local.PreferencesHelper;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.data.model.MyResp;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.data.remote.RibotsService;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Function<List<Ribot>, ObservableSource<? extends Ribot>>() {
                    @Override
                    public ObservableSource<? extends Ribot> apply(@NonNull List<Ribot> ribots)
                            throws Exception {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    public Observable<MyResp> getRankApps() {
        return mRibotsService.getRankApps("1", "1", "RANK_HOT");
    }

    public Observable<List<Contributor>> contributors() {
        Log.i("wang", "mRibotsService.contributors,tid:"+Thread.currentThread().getId());
        return mRibotsService.contributors("square", "retrofit");
    }

    public Observable<Contributor> contributorsStepByStep() {
        return mRibotsService.contributors("square", "retrofit").concatMap(new Function<List<Contributor>,
                ObservableSource<? extends Contributor>>() {
             @Override
             public ObservableSource<? extends Contributor> apply(@NonNull final List<Contributor> ribots)
                     throws Exception {
                 return Observable.create(new ObservableOnSubscribe<Contributor>() {
                     @Override
                     public void subscribe(ObservableEmitter<Contributor> emitter) throws Exception {
                         for (Contributor ribot : ribots) {
                             ribot.contributions = 10000;
                             Log.i("wang", "subscribe "+"tid:"+Thread.currentThread().getId()+", "+ribot.login);
                             emitter.onNext(ribot);
                         }
                         emitter.onComplete();
                     }
                 });
             }
         });
    }

}
