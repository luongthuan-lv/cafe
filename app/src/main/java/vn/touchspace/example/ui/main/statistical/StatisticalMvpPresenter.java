package vn.touchspace.example.ui.main.statistical;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.product.ProductMvpView;

public interface StatisticalMvpPresenter<V extends StatisticalMvpView> extends MvpPresenter<V> {
    void getFinish(String startDate, String endDate);

    void getActive(String startDate, String endDate);

    void getCancel(String startDate, String endDate);

}
