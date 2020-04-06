package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newsapp.R;

import java.util.List;

import activity.HomepageModel;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomepageModel.News> news;
    int image_left = 1;
    int image_top = 2;

    public NewsAdapter(Context context, List<HomepageModel.News> news) {
        this.context = context;//blue context represents up context blue onw and other represents inside newsadapter
        this.news = news; //same as context


    }

    @Override
    public int getItemViewType(int position) { //this is a important method it helps us to retrive two different view
       if((position+1)%8==0 || position ==0)//if position == 0 or for every 8 position return top image or left image
       {
           return image_top;
       }
       else
       {
           return image_left;
       }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==image_left)
        {
            View v = LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
            return new ViewHolder(v);
        }
        return  null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View holder;
        ImageView newsImage;
        TextView newsTitle,newsDescp,newsDate,newsSource,newsViews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            holder = itemView;
            newsImage = holder.findViewById(R.id.news_image);
            newsTitle = holder.findViewById(R.id.news_title);
            newsDescp = holder.findViewById(R.id.news_description);
            newsDate = holder.findViewById(R.id.news_date);
            newsViews = holder.findViewById(R.id.news_view);
            newsSource = holder.findViewById(R.id.news_source);

        }
    }
}
