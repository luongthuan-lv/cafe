/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package vn.touchspace.example.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import vn.touchspace.example.di.ActivityContext;
import vn.touchspace.example.di.PerActivity;
import vn.touchspace.example.service.notify.NotifyService;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.login.LoginMvpPresenter;
import vn.touchspace.example.ui.login.LoginMvpView;
import vn.touchspace.example.ui.login.LoginPresenter;
import vn.touchspace.example.ui.main.MainMvpPresenter;
import vn.touchspace.example.ui.main.MainMvpView;
import vn.touchspace.example.ui.main.MainPresenter;
import vn.touchspace.example.ui.main.account.AccountMvpPresenter;
import vn.touchspace.example.ui.main.account.AccountMvpView;
import vn.touchspace.example.ui.main.account.AccountPresenter;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffMvpPresenter;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffMvpView;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffPresenter;
import vn.touchspace.example.ui.main.customer.CustomerMvpPresenter;
import vn.touchspace.example.ui.main.customer.CustomerMvpView;
import vn.touchspace.example.ui.main.customer.CustomerPresenter;
import vn.touchspace.example.ui.main.account.manager.ManagerMvpPresenter;
import vn.touchspace.example.ui.main.account.manager.ManagerMvpView;
import vn.touchspace.example.ui.main.account.manager.ManagerPresenter;
import vn.touchspace.example.ui.main.account.staff.StaffMvpPresenter;
import vn.touchspace.example.ui.main.account.staff.StaffMvpView;
import vn.touchspace.example.ui.main.account.staff.StaffPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpView;
import vn.touchspace.example.ui.main.info.InfoPresenter;
import vn.touchspace.example.ui.main.info.updateinfo.UpdateInfoMvpPresenter;
import vn.touchspace.example.ui.main.info.updateinfo.UpdateInfoMvpView;
import vn.touchspace.example.ui.main.info.updateinfo.UpdateInfoPresenter;
import vn.touchspace.example.ui.main.info.updateinfo.UpdatePasswordMvpPresenter;
import vn.touchspace.example.ui.main.info.updateinfo.UpdatePasswordMvpView;
import vn.touchspace.example.ui.main.info.updateinfo.UpdatePasswordPresenter;
import vn.touchspace.example.ui.main.product.ProductMvpPresenter;
import vn.touchspace.example.ui.main.product.ProductMvpView;
import vn.touchspace.example.ui.main.product.ProductPresenter;
import vn.touchspace.example.ui.main.product.addproduct.AddProductMvpPresenter;
import vn.touchspace.example.ui.main.product.addproduct.AddProductMvpView;
import vn.touchspace.example.ui.main.product.addproduct.AddProductPresenter;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductMvpPresenter;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductMvpView;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductPresenter;
import vn.touchspace.example.ui.splash.SplashMvpPresenter;
import vn.touchspace.example.ui.splash.SplashMvpView;
import vn.touchspace.example.ui.splash.SplashPresenter;
import vn.touchspace.example.utils.rx.AppSchedulerProvider;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by GNUD on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    InfoMvpPresenter<InfoMvpView> provideInfoPresenter(
            InfoPresenter<InfoMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AccountMvpPresenter<AccountMvpView> provideAccountPresenter(
            AccountPresenter<AccountMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    StaffMvpPresenter<StaffMvpView> provideStaffPresenter(
            StaffPresenter<StaffMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ManagerMvpPresenter<ManagerMvpView> provideManagerPresenter(
            ManagerPresenter<ManagerMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    CustomerMvpPresenter<CustomerMvpView> provideCustomerPresenter(
            CustomerPresenter<CustomerMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    UpdateInfoMvpPresenter<UpdateInfoMvpView> provideUpdateInfoPresenter(
            UpdateInfoPresenter<UpdateInfoMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    UpdatePasswordMvpPresenter<UpdatePasswordMvpView> provideUpdatePasswordPresenter(
            UpdatePasswordPresenter<UpdatePasswordMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ProductMvpPresenter<ProductMvpView> provideProductPresenter(
            ProductPresenter<ProductMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    UpdateProductMvpPresenter<UpdateProductMvpView> provideUpdateProductPresenter(
            UpdateProductPresenter<UpdateProductMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AddProductMvpPresenter<AddProductMvpView> provideAddProductPresenter(
            AddProductPresenter<AddProductMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AddStaffMvpPresenter<AddStaffMvpView> provideAddStaffPresenter(
            AddStaffPresenter<AddStaffMvpView> presenter) {
        return presenter;
    }



    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    NotifyService provideNotifyService(AppCompatActivity activity) {
        return new NotifyService();
    }
}
