package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newsapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GridAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    Context context;
    List<DemoCategory> demoCategories;

    public GridAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        demoCategories = new ArrayList<>();
        demoCategories.add(new DemoCategory(R.drawable.star, "Cosmos"));
        demoCategories.add(new DemoCategory(R.drawable.sport, "Sport"));
        demoCategories.add(new DemoCategory(R.drawable.technology, "Tech"));
        demoCategories.add(new DemoCategory(R.drawable.health, "heath"));
        demoCategories.add(new DemoCategory(R.drawable.world, "world"));
        demoCategories.add(new DemoCategory(R.drawable.weather, "weather"));
        demoCategories.add(new DemoCategory(R.drawable.entertainment, "entertainment"));
        demoCategories.add(new DemoCategory(R.drawable.economics, "economics"));


    }

    @Override
    public int getCount() {
        return demoCategories.size();
    }

    @Override
    public Object getItem(int i) {
        return demoCategories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_category, null);
            holder = new ViewHolder();
            holder.circleImageView = view.findViewById(R.id.category_image);

            holder.categoryName = view.findViewById(R.id.category_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.categoryName.setText(demoCategories.get(i).imageName);
        Glide.with(context).load(demoCategories.get(i).imageId).into(holder.circleImageView);

        return view;
    }

    static class ViewHolder {
        CircleImageView circleImageView;
        TextView categoryName;
    }

    class DemoCategory {
        Integer imageId;

        public DemoCategory(Integer imageId, String imageName) {
            this.imageId = imageId;
            this.imageName = imageName;
        }

        String imageName;
    }
}
