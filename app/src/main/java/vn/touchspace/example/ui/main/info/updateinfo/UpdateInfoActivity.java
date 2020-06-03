package vn.touchspace.example.ui.main.info.updateinfo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.touchspace.example.R;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.utils.AppUtils;

public class UpdateInfoActivity extends BaseActivity implements UpdateInfoMvpView {

    @Inject
    UpdateInfoMvpPresenter<UpdateInfoMvpView> mvpPresenter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.edt_new_name)
    EditText edtNewName;
    @BindView(R.id.edt_new_birthday)
    TextView edtNewBirthday;
    @BindView(R.id.edt_new_phone)
    EditText edtNewPhone;

    private Calendar date;
    @SuppressLint("SimpleDateFormat")

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        mvpPresenter.onAttach(this);

        mvpPresenter.getInfo();
        setUp();
    }

    @Override
    protected void setUp() {
        tvTitle.setText(R.string.title_update_info);

        edtNewName.setText(user.getFullName());
        edtNewBirthday.setText(user.getBirthday());
        edtNewPhone.setText(user.getTelephoneNumber());

        date = Calendar.getInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.edt_new_birthday)
    void onClickDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateInfoActivity.this, (datePicker, year, month, day) -> {
            if (month + 1 >= 10) {
                if(day >= 10){
                    edtNewBirthday.setText(String.format("%s/%s/%s", day, month + 1, year));
                }else{
                    edtNewBirthday.setText(String.format("0%s/%s/%s", day, month + 1, year));
                }
            }else {
                if(day >= 10){
                    edtNewBirthday.setText(String.format("%s/0%s/%s", day, month + 1, year));
                }else{
                    edtNewBirthday.setText(String.format("0%s/0%s/%s", day, month + 1, year));
                }
            }
        }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(date.getTimeInMillis() + 1000);
        datePickerDialog.show();
    }

    @OnClick({R.id.iv_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_save:
                if (edtNewName.getText().toString().trim().equals("") || edtNewBirthday.getText().toString().trim().equals("") || edtNewPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(UpdateInfoActivity.this, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (edtNewPhone.getText().toString().trim().length() != 10) {
                        Toast.makeText(UpdateInfoActivity.this, "Số điện thoại phải có 10 chữ số!", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                }
                AppUtils.isDoubleClick(btnSave);
                break;
        }
    }

    @Override
    public void getInfoSuccess(User user) {
        this.user = user;
    }
}