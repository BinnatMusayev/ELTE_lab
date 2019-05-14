package practice.com.eltelinks.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import practice.com.eltelinks.R;
import practice.com.eltelinks.model.Website;

public class WebsitesAdapter extends BaseAdapter {

    private Context context;
    private List<Website> websites;

    public WebsitesAdapter(Context context, List<Website> websites) {
        this.context = context;
        this.websites = websites;
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

        Website website = websites.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.website_view, null);
        }

        ImageView imageView = convertView.findViewById(R.id.website_logo);
        TextView textView = convertView.findViewById(R.id.website_title);


        Drawable defaultLogo = context.getResources().getDrawable(R.drawable.temp_logo);
        Glide.with(context)
                .load(website.getLogo())
                .centerCrop()
                .error(defaultLogo)
                .into(imageView);

        if (website.getTitle().length() > 7){
            String shortTitle = website.getTitle().substring(0, 5) + "..";
            textView.setText(shortTitle);
        }else{
            textView.setText(website.getTitle());
        }

        return convertView;
    }
}
