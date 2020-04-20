package activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    GridView gridView;
    SliderLayout sliderLayout;
    Toolbar toolbar;
    GridAdapter gridAdapter;
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    List<HomepageModel.News> news;
    List<HomepageModel.CategoryButton> categoryButtons;
    int post = 5; //retrive no of post per time
    int page = 1;
    boolean isFromStart = true;
    //this will note whether we are loading the api for the first time or not
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


        page = 1;
        isFromStart = true;

        getHomeData();

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()))//this will only be executed when we reach the last item of our recycle view
                {
                    isFromStart = false;
                    progressBar.setVisibility(View.VISIBLE);
                    page++;
                    getHomeData();
                }
            }
        });

    }

    //creating function for loading data in homepage
    private void getHomeData() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);//getting instance of retrofit
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("posts", post + "");
        Call<HomepageModel> call = apiInterface.getHomepageApi(params);//calling data
        call.enqueue(new Callback<HomepageModel>() {
            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {
                updateDataToHomepage(response.body());

            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void updateDataToHomepage(HomepageModel body) {//Loading our banner

        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        if (isFromStart) {
            news.clear();
            categoryButtons.clear();
        }

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
        int beforeNewsSize = news.size();
        for (int i = 0; i < body.getNews().size(); i++) {
            news.add(body.getNews().get(i));
        }
        categoryButtons.addAll(body.getCategoryButton());
        if (isFromStart) {
            recyclerView.setAdapter(newsAdapter);
            gridView.setAdapter(gridAdapter);
        } else {
            newsAdapter.notifyItemRangeInserted(beforeNewsSize, body.getNews().size());

        }


    }

    private void initViews() {

        sliderLayout = findViewById(R.id.slider);//return slider layout Linked xml layout into java
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progressbar);
        categoryButtons = new ArrayList<>();
        gridView = findViewById(R.id.grid_view);
        gridAdapter = new GridAdapter(this, categoryButtons);
        nestedScrollView = findViewById(R.id.nested);


        recyclerView = findViewById(R.id.recy_news);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setNestedScrollingEnabled(false);
        news = new ArrayList<>();
        newsAdapter = new NewsAdapter(this, news);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setRefreshing(true);
        recyclerView.setNestedScrollingEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(this);
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
        if (item.getItemId() == R.id.video) {
            //we know user has clicked youtube icon
            Toast.makeText(this, "Video is clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        isFromStart = true;
        page = 1;
        getHomeData();
    }
}
