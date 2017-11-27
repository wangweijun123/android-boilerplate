package uk.co.ribot.androidboilerplate.ui.ribot;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.MyResp;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

/**
 * Created by wangweijun on 2017/11/26.
 */

public class RibotPresenter extends BasePresenter<RibotMvpView> {

    private final DataManager mDataManager;// single

    @Inject
    public RibotPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void syncRibots() {
        mDataManager.syncRibots().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Ribot>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NonNull Ribot ribots) {
//                        if (ribots.isEmpty()) {
//                            getMvpView().showRibotsEmpty();
//                        } else {
//                            getMvpView().showRibots(ribots);
//                        }
                        Log.i("wang", "onNext ribots: "+ribots);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete");
                    }
                });
    }



    public void getRankApps() {
        mDataManager.getRankApps().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MyResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NonNull MyResp ribots) {
//                        if (ribots.isEmpty()) {
//                            getMvpView().showRibotsEmpty();
//                        } else {
//                            getMvpView().showRibots(ribots);
//                        }
                        Log.i("wang", "onNext ribots.status: "+ribots.status);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete");
                    }
                });
    }



}
