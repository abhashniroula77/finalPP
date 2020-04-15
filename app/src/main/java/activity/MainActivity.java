package activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.DefaultSliderView;
import com.newsapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.GridAdapter;
import adapter.NewsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    SliderLayout sliderLayout;
    Toolbar toolbar;
    GridAdapter gridAdapter;
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    List<HomepageModel.News> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();




        getHomeData();

    }
//creating function for loading data in homepage
    private void getHomeData() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);//getting instance of retrofit
        Map<String ,String> params = new HashMap<>();
        params.put("page",1+"");
        params.put("posts",10+"");
        Call<HomepageModel> call = apiInterface.getHomepageApi(params);//calling data
        call.enqueue(new Callback<HomepageModel>() {
            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {
                updateDataToHomepage(response.body());

            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateDataToHomepage(HomepageModel body) {//Loading our banner

        for (int i = 0; i < body.getBanners().size(); i++) {
            //creating individual slider
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView.setRequestOption(new RequestOptions().centerCrop());
            defaultSliderView.image(body.getBanners().get(i).getImage());
            defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    //TODO: handing click on image

                }
            });
            sliderLayout.addSlider(defaultSliderView);//adding individual slider to slider layout


        }
        sliderLayout.startAutoCycle();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setDuration(4000);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

        for(int i = 0; i<body.getNews().size();i++)
        {
            news.add(body.getNews().get(i));
        }
        recyclerView.setAdapter(newsAdapter);
    }

    private void initViews() {
        sliderLayout = findViewById(R.id.slider);//return slider layout Linked xml layout into java
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.grid_view);
        gridAdapter = new GridAdapter(this);
        gridView.setAdapter(gridAdapter);
        recyclerView = findViewById(R.id.recy_news);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        news = new ArrayList<>();
        newsAdapter = new NewsAdapter(this,news);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();//prevent us from any memory leak
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.video){
            //we know user has clicked youtube icon
            Toast.makeText(this,  "Video is clicked",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
