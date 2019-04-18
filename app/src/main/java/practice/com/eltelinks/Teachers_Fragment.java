package practice.com.eltelinks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Teachers_Fragment extends Fragment {

    private ExpandableListView expandableListView;
    private ExpandableTeachersListAdapter expandableTeachersListAdapter;
    private List<String> listHeader;
    private HashMap<String, List<String>> listHashMap;

    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachers, container, false);

        expandableListView = view.findViewById(R.id.teachers_expandable_list_view);
        initData();
        expandableTeachersListAdapter = new ExpandableTeachersListAdapter(getActivity(), listHeader, listHashMap);
        expandableListView.setAdapter(expandableTeachersListAdapter);

        //for animation
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandableListView.isGroupExpanded(groupPosition))
                    expandableListView.collapseGroup(groupPosition);
                else
                    expandableListView.expandGroup(groupPosition, true);
                return true;
            }

        });


        //floating action button
        fab = view.findViewById(R.id.fab);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        return view;
    }

    private void initData(){

        // get data from SQLLite - Room

        listHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listHeader.add("Robert");
        listHeader.add("Marta");
        listHeader.add("Tamas");



        List<String> robertData = new ArrayList<>();
        robertData.add("kitlei@elte.hu");
        robertData.add("Informatics");
        robertData.add("kitle.hu");


        List<String> martaData = new ArrayList<>();
        martaData.add("marta@elte.hu");
        martaData.add("Informatics");
        martaData.add("marta.hu");



        List<String> tamasData = new ArrayList<>();
        tamasData.add("tamas@elte.hu");
        tamasData.add("Informatics");
        tamasData.add("tamas.hu");


        listHashMap.put(listHeader.get(0), robertData);
        listHashMap.put(listHeader.get(1), martaData);
        listHashMap.put(listHeader.get(2), tamasData);
    }
}
