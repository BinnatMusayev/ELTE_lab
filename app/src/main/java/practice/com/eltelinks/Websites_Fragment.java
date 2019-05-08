package practice.com.eltelinks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import practice.com.eltelinks.adapters.WebsitesAdapter;
import practice.com.eltelinks.model.Website;

public class Websites_Fragment extends Fragment {

    private GridView gridView;
    //Floating Action Button
    private FloatingActionButton fab_websites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_websites, container, false);

        Website[] websites = new Website[]{
                new Website("title1", "logo 1"),
                new Website("title2", "logo 2"),
                new Website("title3", "logo 3"),
                new Website("title4", "logo 4"),
        };
        gridView = view.findViewById(R.id.websites_grid);
        WebsitesAdapter websitesAdapter = new WebsitesAdapter(getActivity(), websites);
        gridView.setAdapter(websitesAdapter);


        //floating action button
        fab_websites = view.findViewById(R.id.fab_websites);

        fab_websites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddWebsite.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
