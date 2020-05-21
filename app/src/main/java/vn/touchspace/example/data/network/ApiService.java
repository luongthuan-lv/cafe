package vn.touchspace.example.data.network;


import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.touchspace.example.data.network.model.request.SignInRequest;
import vn.touchspace.example.data.network.model.response.SignIn;

import static vn.touchspace.example.data.network.ApiEndPoint.ENDPOINT_SIGN_IN;

/**
 * Created by GNUD on 24/08/2017.
 */

public interface ApiService {

    /* login */
    @Headers("Content-Type: application/json; charset=utf-8")
    @POST(ENDPOINT_SIGN_IN)
    Single<SignIn> signIn(@Body SignInRequest signInRequest);

}
