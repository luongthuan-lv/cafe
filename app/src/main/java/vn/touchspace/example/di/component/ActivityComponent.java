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

package vn.touchspace.example.di.component;


import vn.touchspace.example.di.PerActivity;
import vn.touchspace.example.di.module.ActivityModule;
import vn.touchspace.example.ui.login.LoginActivity;
import vn.touchspace.example.ui.main.MainActivity;
import vn.touchspace.example.ui.main.account.AccountFragment;
import vn.touchspace.example.ui.main.info.InfoFragment;
import vn.touchspace.example.ui.main.info.updateinfo.UpdateInfoActivity;
import vn.touchspace.example.ui.main.info.updateinfo.UpdatePasswordActivity;
import vn.touchspace.example.ui.main.invoice.InvoiceFragment;
import vn.touchspace.example.ui.main.product.ProductFragment;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductActivity;
import vn.touchspace.example.ui.main.statistical.StatisticalFragment;
import vn.touchspace.example.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by GNUD on 27/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(UpdateProductActivity activity);

    void inject(InfoFragment fragment);

    void inject(UpdateInfoActivity activity);

    void inject(UpdatePasswordActivity activity);

    void inject(StatisticalFragment fragment);

    void inject(AccountFragment fragment);

    void inject(InvoiceFragment fragment);

    void inject(ProductFragment fragment);

}
