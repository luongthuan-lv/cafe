package vn.touchspace.example.data;

import android.content.Context;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import vn.touchspace.example.data.network.ApiService;
import vn.touchspace.example.data.network.model.Message;
import vn.touchspace.example.data.network.model.request.AddCustomerRequest;
import vn.touchspace.example.data.network.model.request.AddProductRequest;
import vn.touchspace.example.data.network.model.request.AddStaffRequest;
import vn.touchspace.example.data.network.model.request.RemoveRequest;
import vn.touchspace.example.data.network.model.request.SignInRequest;
import vn.touchspace.example.data.network.model.request.UpdateCustomerRequest;
import vn.touchspace.example.data.network.model.request.UpdateInfoRequest;
import vn.touchspace.example.data.network.model.request.UpdatePasswordRequest;
import vn.touchspace.example.data.network.model.request.UpdateProductRequest;
import vn.touchspace.example.data.network.model.response.Customer;
import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.data.prefs.PreferencesHelper;
import vn.touchspace.example.data.realm.RealmHelper;
import vn.touchspace.example.data.room.DbHelper;
import vn.touchspace.example.data.room.model.User;
import vn.touchspace.example.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.http.Body;

/**
 * Created by GNUD on 02/12/2017.
 */

@Singleton
public class AppDataManager implements DataManager {


    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final RealmHelper mRealmHelper;
    private final ApiService mApiService;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper mPreferencesHelper,
                          RealmHelper mRealmHelper,
                          ApiService apiService,
                          DbHelper mDbHelper) {
        this.mContext = context;
        this.mPreferencesHelper = mPreferencesHelper;
        this.mRealmHelper = mRealmHelper;
        this.mApiService = apiService;
        this.mDbHelper = mDbHelper;
    }
    @Override
    public void save(String key, boolean value) {
        mPreferencesHelper.save(key, value);
    }

    @Override
    public void save(String key, String value) {
        mPreferencesHelper.save(key, value);
    }

    @Override
    public void save(String key, float value) {
        mPreferencesHelper.save(key, value);
    }

    @Override
    public void save(String key, int value) {
        mPreferencesHelper.save(key, value);
    }

    @Override
    public void save(String key, long value) {
        mPreferencesHelper.save(key, value);
    }

    @Override
    public boolean getBoolean(String key) {
        return mPreferencesHelper.getBoolean(key);
    }

    @Override
    public String getString(String key) {
        return mPreferencesHelper.getString(key);
    }

    @Override
    public long getLong(String key) {
        return mPreferencesHelper.getLong(key);
    }

    @Override
    public int getInt(String key) {
        return mPreferencesHelper.getInt(key);
    }

    @Override
    public float getFloat(String key) {
        return mPreferencesHelper.getFloat(key);
    }

    @Override
    public void remove(String key) {
        mPreferencesHelper.remove(key);
    }

    @Override
    public void clear() {
        mRealmHelper.clear();
    }

    @Override
    public <T extends RealmObject> void clear(Class<T> type) {
        mRealmHelper.clear(type);
    }

    @Override
    public <T extends RealmObject> void delete(T item) {
        mRealmHelper.delete(item);
    }

    @Override
    public <T extends RealmObject> void save(T item) {
        mRealmHelper.save(item);
    }

    @Override
    public <T extends RealmObject> T findFirst(Class<T> type, String key, int value) {
        return mRealmHelper.findFirst(type, key, value);
    }

    @Override
    public <T extends RealmObject> T findFirst(Class<T> type, String key, String value) {
        return mRealmHelper.findFirst(type, key, value);
    }

    @Override
    public <T extends RealmObject> T findFirst(Class<T> type) {
        return mRealmHelper.findFirst(type);
    }

    @Override
    public <T extends RealmObject> RealmResults findAll(Class<T> type) {
        return mRealmHelper.findAll(type);
    }

    @Override
    public <T extends RealmObject> RealmResults findAllSorted(Class<T> type, String field, Sort sort) {
        return mRealmHelper.findAllSorted(type, field, sort);
    }

    @Override
    public <T extends RealmObject> RealmResults findAllAsync(Class<T> type) {
        return mRealmHelper.findAllAsync(type);
    }

    @Override
    public <T extends RealmObject> RealmResults findAllSortedAsync(Class<T> type, String field, Sort sort) {
        return mRealmHelper.findAllSortedAsync(type, field, sort);
    }

    @Override
    public <T extends RealmObject> T findOneAsync(Class<T> type, String key, int value) {
        return mRealmHelper.findOneAsync(type, key, value);
    }

    @Override
    public <T extends RealmObject> RealmResults findAllSortedAsync(Class<T> type, String key, int value, String field, Sort sort) {
        return mRealmHelper.findAllSortedAsync(type, key, value, field, sort);
    }

    @Override
    public Flowable<User> findUser(String nameUser) {
        return mDbHelper.findUser(nameUser);
    }

    @Override
    public Observable<Boolean> saveUser(User user) {
        return mDbHelper.saveUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public Single<vn.touchspace.example.data.network.model.response.User> signIn(SignInRequest request) {
        return mApiService.signIn(request);
    }

    @Override
    public Single<Message> updateInfo(UpdateInfoRequest request) {
        return mApiService.updateInfo(request);
    }

    @Override
    public Single<Message> updatePassword(UpdatePasswordRequest request) {
        return mApiService.updatePassword(request);
    }

    @Override
    public Single<List<Product>> getProducts(String productName) {
        return mApiService.getProducts(productName);
    }

    @Override
    public Single<List<vn.touchspace.example.data.network.model.response.User>> getStaffs(String role) {
        return mApiService.getStaffs(role);
    }

    @Override
    public Single<Message> removeStaff(RemoveRequest request) {
        return mApiService.removeStaff(request);
    }

    @Override
    public Single<Message> createStaff(AddStaffRequest request) {
        return mApiService.createStaff(request);
    }

    @Override
    public Single<List<Customer>> getCustomers() {
        return mApiService.getCustomers();
    }

    @Override
    public Single<Message> createCustomer(AddCustomerRequest request) {
        return mApiService.createCustomer(request);
    }

    @Override
    public Single<Message> updateCustomer(UpdateCustomerRequest request) {
        return mApiService.updateCustomer(request);
    }

    @Override
    public Single<Message> removeCustomer(RemoveRequest request) {
        return mApiService.removeCustomer(request);
    }

    @Override
    public Single<Message> createProduct(AddProductRequest request) {
        return mApiService.createProduct(request);
    }

    @Override
    public Single<Message> updateProduct(UpdateProductRequest request) {
        return mApiService.updateProduct(request);
    }

    @Override
    public Single<Message> removeProduct(RemoveRequest request) {
        return mApiService.removeProduct(request);
    }

    @Override
    public Single<List<Invoice>> getInvoices(String state) {
        return mApiService.getInvoices(state);
    }
}
