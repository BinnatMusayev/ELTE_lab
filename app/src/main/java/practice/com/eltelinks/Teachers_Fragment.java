package practice.com.eltelinks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import practice.com.eltelinks.model.Teacher;

public class Teachers_Fragment extends Fragment {

    //RecyclerView Stuff
    private RecyclerView recyclerView;
    private TeacherRecyclerViewAdapter adapter;
    private List<Teacher> teachers;

    //Floating Action Button
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachers, container, false);

        //RecyclerViewStuff
        teachers = new ArrayList<>();
        initData();

        adapter = new TeacherRecyclerViewAdapter(teachers);

        recyclerView = view.findViewById(R.id.teachers_recycler_view);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


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
        teachers.add(new Teacher("kitleiii", "kit@gmail.com", "informatics", "kit.com"));
        teachers.add(new Teacher("tamas", "tam@gmail.com", "informatics", "tam.com"));
        teachers.add(new Teacher("marta", "mar@gmail.com", "informatics", "mar.com"));
        teachers.add(new Teacher("melinda", "mel@gmail.com", "informatics", "mel.com"));
        teachers.add(new Teacher("marta", "mar@gmail.com", "informatics", "mar.com"));
        teachers.add(new Teacher("marta", "mar@gmail.com", "informatics", "mar.com"));
        teachers.add(new Teacher("marta", "mar@gmail.com", "informatics", "mar.com"));
        teachers.add(new Teacher("marta", "mar@gmail.com", "informatics", "mar.com"));
        teachers.add(new Teacher("marta", "mar@gmail.com", "informatics", "mar.com"));
        teachers.add(new Teacher("marta", "mar@gmail.com", "informatics", "mar.com"));

    }
}
