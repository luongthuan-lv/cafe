package vn.touchspace.example.data.network;


import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.touchspace.example.data.network.model.Message;
import vn.touchspace.example.data.network.model.request.SignInRequest;
import vn.touchspace.example.data.network.model.request.UpdateInfoRequest;
import vn.touchspace.example.data.network.model.request.UpdatePasswordRequest;
import vn.touchspace.example.data.network.model.response.SignIn;
import vn.touchspace.example.data.network.model.response.User;

import static vn.touchspace.example.data.network.ApiEndPoint.ENDPOINT_SIGN_IN;
import static vn.touchspace.example.data.network.ApiEndPoint.ENDPOINT_UPDATE_INFO;
import static vn.touchspace.example.data.network.ApiEndPoint.ENDPOINT_UPDATE_PASSWORD;

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


}
