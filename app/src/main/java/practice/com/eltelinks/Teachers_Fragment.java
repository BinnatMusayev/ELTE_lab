package practice.com.eltelinks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

import practice.com.eltelinks.adapters.TeacherRecyclerViewAdapter;
import practice.com.eltelinks.model.Teacher;
import practice.com.eltelinks.view_model.TeacherViewModel;

public class Teachers_Fragment extends Fragment {

    //RecyclerView Stuff
    private RecyclerView recyclerView;
    private TeacherRecyclerViewAdapter adapter;

    //Floating Action Button
    private FloatingActionButton fab;

    //ViewMode
    private TeacherViewModel teacherViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachers, container, false);

        //RecyclerViewStuff

        adapter = new TeacherRecyclerViewAdapter();

        recyclerView = view.findViewById(R.id.teachers_recycler_view);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        //floating action button
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddTeacher.class);
                startActivity(intent);
            }
        });


        //view model
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel.class);
        teacherViewModel.getAllTeachers().observe(getActivity(), new Observer<List<Teacher>>() {
            @Override
            public void onChanged(@Nullable List<Teacher> teachers) {
                adapter.setTeachers(teachers);
            }
        });


        return view;
    }
}
