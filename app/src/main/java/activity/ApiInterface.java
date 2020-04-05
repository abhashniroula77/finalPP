package activity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("homepage_api")
    Call<Object> getHomepageApi(@QueryMap Map<String, String> params);

}
