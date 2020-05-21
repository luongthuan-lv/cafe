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

package vn.touchspace.example.ui.base;

/**
 * Created by GNUD on 27/01/17.
 */

import android.os.Handler;
import android.text.TextUtils;

import com.touchspace.example.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.Message;
import vn.touchspace.example.utils.AppLogger;
import vn.touchspace.example.utils.GsonUtils;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";
    private Handler mHandler;
    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        return mHandler;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void handleApiError(Throwable error) {
        if (error == null) {
            getMvpView().onError(R.string.api_default_error);
            return;
        }

        if (error instanceof HttpException) {
            AppLogger.i(((HttpException) error).message() + ((HttpException) error).code());
            switch (((HttpException) error).code()) {
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    try {
                        Message message = GsonUtils.deserialize(
                                ((HttpException) error).response().errorBody().string(),
                                Message.class);
                        getMvpView().showMessage(message.getMessage());
                    } catch (IOException e) {
                        e.printStackTrace();
                        getMvpView().showMessage(error.getMessage());
                    }
                    break;
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                case HttpsURLConnection.HTTP_FORBIDDEN:
//                    setUserAsLoggedOut();
//                    getMvpView().openActivityOnTokenExpire();
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                case HttpsURLConnection.HTTP_NOT_FOUND:
                default:
                    getMvpView().onError(error.getMessage());
            }
            //ResponseBody responseBody = ((HttpException) error).response().errorBody();
            //getMvpView().showMessage();
        } else if (error instanceof SocketTimeoutException) {
            getMvpView().onError(R.string.text_all_has_error_timeout);
        } else if (error instanceof IOException) {
            getMvpView().onError(R.string.text_all_has_error_network);
        } else {
            if (!TextUtils.isEmpty(error.getMessage())) {
                getMvpView().onError(error.getMessage());
            } else {
                getMvpView().onError(R.string.text_all_has_error_please_try);
            }
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
