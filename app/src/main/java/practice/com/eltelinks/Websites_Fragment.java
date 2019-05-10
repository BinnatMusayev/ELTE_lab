package practice.com.eltelinks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import practice.com.eltelinks.adapters.WebsitesAdapter;
import practice.com.eltelinks.model.Teacher;
import practice.com.eltelinks.model.Website;
import practice.com.eltelinks.view_model.WebsiteViewModel;

public class Websites_Fragment extends Fragment {

    private GridView gridView;
    //Floating Action Button
    private FloatingActionButton fab_websites;

    //View Model
    private WebsiteViewModel websiteViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_websites, container, false);

        websiteViewModel = ViewModelProviders.of(this).get(WebsiteViewModel.class);

        gridView = view.findViewById(R.id.websites_grid);
        final WebsitesAdapter websitesAdapter = new WebsitesAdapter(getActivity(), websiteViewModel.getAllWebsites().getValue());
        gridView.setAdapter(websitesAdapter);

        websiteViewModel.getAllWebsites().observe(getActivity(), new Observer<List<Website>>() {
            @Override
            public void onChanged(@Nullable List<Website> websites) {
                websitesAdapter.setWebsites(websites);
            }
        });

        //grid item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
                TextView tv = view.findViewById(R.id.websites_title);
//                tv.setText(websiteViewModel.getAllWebsites().getValue().get(position).getUrl());
                // opening fragment with URL data
                ((MainActivity)getActivity()).openBrowser(websiteViewModel.getAllWebsites().getValue().get(position).getUrl());

            }
        });

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
