package com.example.appsale29032022.data.remote;

import com.example.appsale29032022.data.remote.dto.AppResource;
import com.example.appsale29032022.data.remote.dto.FoodDTO;
import com.example.appsale29032022.data.remote.dto.OrderDTO;
import com.example.appsale29032022.data.remote.dto.UserDTO;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/sign-in")
    Call<AppResource<UserDTO>> signIn(@Body HashMap<String,Object>body);

    @POST("user/sign-up")
    Call<AppResource<UserDTO>> signUp(@Body HashMap<String, Object> body);

    @GET("product")
    Call<AppResource<List<FoodDTO>>> fetchFoods();

    @POST("cart/add")
    Call<AppResource<OrderDTO>> addToCart(@Body HashMap<String, String> body);

}
