package activity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("homepage_api") //fetching data from homepage api
    //foundation of retroritto use for api
    Call<HomepageModel> getHomepageApi(@QueryMap Map<String, String> params);//fetching data from gson gson to java object library is going to convert json obect into homepage model

}
