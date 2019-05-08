package practice.com.eltelinks.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import practice.com.eltelinks.model.Teacher;
import practice.com.eltelinks.repo.ElteLinksRepository;

public class TeacherViewModel extends AndroidViewModel {
    private ElteLinksRepository repo;
    private LiveData<List<Teacher>> allTeachers;

    public TeacherViewModel(@NonNull Application application) {
        super(application);

        repo = new ElteLinksRepository(application);
        allTeachers = repo.getAllTeachers();
    }

    public void addTeacher(Teacher teacher){
        repo.addTeacher(teacher);
    }

    public LiveData<List<Teacher>> getAllTeachers(){
        return allTeachers;
    }
}