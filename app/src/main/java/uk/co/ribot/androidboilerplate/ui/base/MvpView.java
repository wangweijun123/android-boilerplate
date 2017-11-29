package uk.co.ribot.androidboilerplate.ui.base;


/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 * 通用UI包括loading, error界面
 */
public interface MvpView {

    void showLoading();

    void hideLoading();

    void showErrorUI();

    void hideErrorUI();
}
