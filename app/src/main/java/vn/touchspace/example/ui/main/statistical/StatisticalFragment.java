package vn.touchspace.example.ui.main.statistical;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.touchspace.example.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.response.Statistical;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.utils.AppUtils;

public class StatisticalFragment extends BaseFragment implements StatisticalMvpView {

    @Inject
    StatisticalPresenter<StatisticalMvpView> mPresenter;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.tv_active)
    TextView tvActive;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    private Calendar startDate, endDate, dateNow;

    public static StatisticalFragment newInstance() {

        Bundle args = new Bundle();

        StatisticalFragment fragment = new StatisticalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_statistical;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);

        // lấy 1 tháng gần nhất
        endDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        dateNow = Calendar.getInstance();

        startDate.add(Calendar.MONTH, -1);
        startDate.add(Calendar.DATE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.SECOND, 59);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.HOUR_OF_DAY, 23);
        tvStartDate.setText(AppUtils.convertTime(startDate.getTimeInMillis()));
        tvEndDate.setText(AppUtils.convertTime(endDate.getTimeInMillis()));

        mPresenter.getActive(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));
        mPresenter.getFinish(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));
        mPresenter.getCancel(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.tv_start_date)
    void onClickStartDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            startDate.set(Calendar.YEAR, i);
            startDate.set(Calendar.MONTH, i1);
            startDate.set(Calendar.DAY_OF_MONTH, i2);

            if (startDate.after(endDate)) {
                endDate.set(Calendar.YEAR, i);
                endDate.set(Calendar.MONTH, i1);
                endDate.set(Calendar.DAY_OF_MONTH, i2);
                tvEndDate.setText(AppUtils.convertTime(endDate.getTimeInMillis()));
            }

            mPresenter.getActive(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));
            mPresenter.getFinish(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));
            mPresenter.getCancel(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));

            tvStartDate.setText(AppUtils.convertTime(startDate.getTimeInMillis()));
        }, startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(endDate.getTime().getTime());
        datePickerDialog.show();
    }


    @OnClick(R.id.tv_end_date)
    void onClickEndDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
            endDate.set(Calendar.YEAR, i);
            endDate.set(Calendar.MONTH, i1);
            endDate.set(Calendar.DAY_OF_MONTH, i2);

            mPresenter.getActive(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));
            mPresenter.getFinish(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));
            mPresenter.getCancel(AppUtils.convertTime(startDate.getTimeInMillis()), AppUtils.convertTime(endDate.getTimeInMillis()));

            tvEndDate.setText(AppUtils.convertTime(endDate.getTimeInMillis()));
        }, endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(startDate.getTime().getTime());
        datePickerDialog.getDatePicker().setMaxDate(dateNow.getTimeInMillis() + 1000);
        datePickerDialog.show();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void activeSuccess(List<Statistical> list) {
        double totalActive = 0;
        for (int i = 0; i < list.size(); i++) {
            totalActive += list.get(i).getTotal();
        }
        tvActive.setText(String.format("%s VNĐ", AppUtils.formatNumber(totalActive)));
    }

    @Override
    public void finishSuccess(List<Statistical> list) {
        double totalFinish = 0;
        for (int i = 0; i < list.size(); i++) {
            totalFinish += list.get(i).getTotal();
        }
        tvFinish.setText(String.format("%s VNĐ", AppUtils.formatNumber(totalFinish)));
    }

    @Override
    public void cancelSuccess(List<Statistical> list) {
        double totalCancel = 0;
        for (int i = 0; i < list.size(); i++) {
            totalCancel += list.get(i).getTotal();
        }
        tvCancel.setText(String.format("%s VNĐ", AppUtils.formatNumber(totalCancel)));
    }
}
