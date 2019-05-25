package practice.com.eltelinks.adapters;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import practice.com.eltelinks.MainActivity;
import practice.com.eltelinks.R;
import practice.com.eltelinks.model.Website;

public class WebsitesAdapter extends BaseAdapter {

    private Context context;
    private List<Website> websites;

    private Map<Integer, ImageView> websiteLogos;
    private ImageView firstIV;

    public WebsitesAdapter(Context context, List<Website> websites) {
        this.context = context;
        this.websites = websites;

        websiteLogos = new HashMap<>();
    }

    public void setWebsites(List<Website> websiteList){
        this.websites = websiteList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return websites == null? 0 : websites.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Website website = websites.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.website_view, null);
        }

        final ImageView imageView = convertView.findViewById(R.id.website_logo);
        TextView textView = convertView.findViewById(R.id.website_title);



        Drawable defaultLogo = context.getResources().getDrawable(R.drawable.temp_logo);
//        Glide.with(context)
//                .load(website.getLogo())
//                .centerCrop()
//                .error(defaultLogo)
//                .into(imageView);

        Picasso.get()
                .load(website.getLogo())
//                .placeholder(R.drawable.temp_logo)
                .error(R.drawable.temp_logo)
                .into(imageView);

        if (website.getTitle().length() > 7){
            String shortTitle = website.getTitle().substring(0, 5) + "..";
            textView.setText(shortTitle);
        }else{
            textView.setText(website.getTitle());
        }

        //extra
        websiteLogos.put(position, imageView);
        if (position==0){
            firstIV = imageView;
        }

        return convertView;
    }

    public ImageView getWebsiteLogo(int i) {
        return i == 0 ? firstIV :websiteLogos.get(i);
    }

    public Map<Integer, ImageView> getMap(){
        return websiteLogos;
    }
}
