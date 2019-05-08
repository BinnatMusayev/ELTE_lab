package practice.com.eltelinks.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import practice.com.eltelinks.dao.TeacherDAO;
import practice.com.eltelinks.database.ElteLinksDatabase;
import practice.com.eltelinks.model.Teacher;

public class ElteLinksRepository {
    private TeacherDAO teacherDAO;
    private LiveData<List<Teacher>> allTeachers;

    public ElteLinksRepository(Application application){
        ElteLinksDatabase elteDb = ElteLinksDatabase.getInstance(application);
        teacherDAO = elteDb.teacherDao();
        allTeachers = teacherDAO.getAllTeachers();
    }

    public void addTeacher(Teacher teacher){
        new AddTeacherAsyncTask(teacherDAO).execute(teacher);
    }

    public LiveData<List<Teacher>> getAllTeachers(){
        return allTeachers;
    }

    private static class AddTeacherAsyncTask extends AsyncTask<Teacher, Void, Void>{
        private TeacherDAO teacherDAO;

        private AddTeacherAsyncTask(TeacherDAO teacherDAO){
            this.teacherDAO = teacherDAO;
        }

        @Override
        protected Void doInBackground(Teacher... teachers) {
            teacherDAO.addTeacher(teachers[0]);
            return null;
        }
    }
}
