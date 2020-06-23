package vn.touchspace.example.data.network;

import com.touchspace.example.BuildConfig;

import vn.touchspace.example.data.network.model.Message;
import vn.touchspace.example.data.network.model.request.AddCustomerRequest;
import vn.touchspace.example.data.network.model.request.AddInvoiceRequest;
import vn.touchspace.example.data.network.model.request.AddProductRequest;
import vn.touchspace.example.data.network.model.request.AddStaffRequest;
import vn.touchspace.example.data.network.model.request.RemoveRequest;
import vn.touchspace.example.data.network.model.request.SignInRequest;
import vn.touchspace.example.data.network.model.request.UpdateCustomerRequest;
import vn.touchspace.example.data.network.model.request.UpdateInfoRequest;
import vn.touchspace.example.data.network.model.request.UpdatePasswordRequest;
import vn.touchspace.example.data.network.model.request.UpdateProductRequest;
import vn.touchspace.example.data.network.model.request.UpdateStateRequest;
import vn.touchspace.example.data.network.model.response.Customer;
import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.data.network.model.response.SignIn;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.main.invoice.addinvoice.AddInvoiceActivity;

/**
 * Created by GNUD on 02/12/2017.
 */

@Singleton
public class AppApiHelper implements ApiService {

    private static final String TAG = AppApiHelper.class.getSimpleName();
    private static ApiService apiService;

    @Inject
    public AppApiHelper() {
    }


    public static void init() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);  // <-- this is the important line!
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private static RequestBody toRequestBody(JSONObject object) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());
    }


    @Override
    public Single<User> signIn(SignInRequest request) {
        return apiService.signIn(request);
    }

    @Override
    public Single<Message> updateInfo(UpdateInfoRequest request) {
        return apiService.updateInfo(request);
    }

    @Override
    public Single<Message> updatePassword(UpdatePasswordRequest request) {
        return apiService.updatePassword(request);
    }

    @Override
    public Single<List<Product>> getProducts(String productName) {
        return apiService.getProducts(productName);
    }

    @Override
    public Single<List<User>> getStaffs(String role) {
        return apiService.getStaffs(role);
    }

    @Override
    public Single<Message> removeStaff(RemoveRequest request) {
        return apiService.removeStaff(request);
    }

    @Override
    public Single<Message> createStaff(AddStaffRequest request) {
        return apiService.createStaff(request);
    }

    @Override
    public Single<List<Customer>> getCustomers() {
        return apiService.getCustomers();
    }

    @Override
    public Single<Message> createProduct(AddProductRequest request) {
        return apiService.createProduct(request);
    }

    @Override
    public Single<Message> updateProduct(UpdateProductRequest request) {
        return apiService.updateProduct(request);
    }

    @Override
    public Single<Message> removeProduct(RemoveRequest request) {
        return apiService.removeProduct(request);
    }

    @Override
    public Single<Message> createCustomer(AddCustomerRequest request) {
        return apiService.createCustomer(request);
    }

    @Override
    public Single<Message> updateCustomer(UpdateCustomerRequest request) {
        return apiService.updateCustomer(request);
    }

    @Override
    public Single<Message> removeCustomer(RemoveRequest request) {
        return apiService.removeCustomer(request);
    }

    @Override
    public Single<List<Invoice>> getInvoices(String state) {
        return apiService.getInvoices(state);
    }

    @Override
    public Single<Message> updateState(UpdateStateRequest request) {
        return apiService.updateState(request);
    }

    @Override
    public Single<Message> addInvoice(AddInvoiceRequest request) {
        return apiService.addInvoice(request);
    }
}
