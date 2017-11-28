package uk.co.ribot.androidboilerplate.ui.ribot;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

/**
 * Created by wangweijun on 2017/11/26.
 */

public interface RibotMvpView extends MvpView{

    void showDataLoadSuccessTip(List<Contributor> list);

    void showDataLoadErrorTip();
}
