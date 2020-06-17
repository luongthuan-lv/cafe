package vn.touchspace.example.data.network;


import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.data.network.model.response.SignIn;
import vn.touchspace.example.data.network.model.response.User;

import static vn.touchspace.example.data.network.ApiEndPoint.*;

/**
 * Created by GNUD on 24/08/2017.Ap
 */

public interface ApiService {

    /* login */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_SIGN_IN)
    Single<User> signIn(@Body SignInRequest request);

    /* update info */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_UPDATE_INFO)
    Single<Message> updateInfo(@Body UpdateInfoRequest request);

    /* update password */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_UPDATE_PASSWORD)
    Single<Message> updatePassword(@Body UpdatePasswordRequest request);

    /* get products */
    @Headers("Content-Type: application/json; charset=utf-8")
    @GET(ENDPOINT_GET_PRODUCTS)
    Single<List<Product>> getProducts(@Query("productName") String productName);

    /* get staffs */
    @Headers("Content-Type: application/json; charset=utf-8")
    @GET(ENDPOINT_GET_STAFFS)
    Single<List<User>> getStaffs(@Query("role") String role);

    /* remove staff */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_REMOVE_STAFF)
    Single<Message> removeStaff(@Body RemoveRequest request);

    /* add staff */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_CREATE_STAFF)
    Single<Message> createStaff(@Body AddStaffRequest request);

    /* get customers */
    @Headers("Content-Type: application/json; charset=utf-8")
    @GET(ENDPOINT_GET_CUSTOMERS)
    Single<List<Customer>> getCustomers();

    /* create customer */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_CREATE_CUSTOMER)
    Single<Message> createCustomer(@Body AddCustomerRequest request);

    /* update customer */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_UPDATE_CUSTOMER)
    Single<Message> updateCustomer(@Body UpdateCustomerRequest request);

    /* remove customer */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_REMOVE_CUSTOMER)
    Single<Message> removeCustomer(@Body RemoveRequest request);

    /* add product */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_CREATE_PRODUCT)
    Single<Message> createProduct(@Body AddProductRequest request);

    /* update product */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_UPDATE_PRODUCT)
    Single<Message> updateProduct(@Body UpdateProductRequest request);

    /* remove product */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_REMOVE_PRODUCT)
    Single<Message> removeProduct(@Body RemoveRequest request);

}
