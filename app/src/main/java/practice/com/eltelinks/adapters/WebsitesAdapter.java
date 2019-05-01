package practice.com.eltelinks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import practice.com.eltelinks.R;
import practice.com.eltelinks.model.Website;

public class WebsitesAdapter extends BaseAdapter {

    private Context context;
    private Website[] websites;

    public WebsitesAdapter(Context context, Website[] websites) {
        this.context = context;
        this.websites = websites;
    }

    @Override
    public int getCount() {
        return websites.length;
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

        Website website = websites[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.website_view, null);
        }

        ImageView imageView = convertView.findViewById(R.id.website_logo);
        TextView textView = convertView.findViewById(R.id.website_title);

        //later get these from database
        imageView.setImageResource(R.drawable.logo);
        textView.setText(website.getTitle());

        return convertView;
    }
}
