package vn.touchspace.example.ui.main.statistical;

import java.util.List;

import vn.touchspace.example.data.network.model.response.Statistical;
import vn.touchspace.example.ui.base.MvpView;

public interface StatisticalMvpView extends MvpView {
    void activeSuccess(List<Statistical> list);

    void finishSuccess(List<Statistical> list);

    void cancelSuccess(List<Statistical> list);
}
