package uk.co.ribot.androidboilerplate.ui.ribot;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;

/**
 * Created by wangweijun on 2017/11/26.
 */

public interface RibotMvpView extends MvpView{

    void showDataLoadSuccessTip();

    void showDataLoadErrorTip();
}
