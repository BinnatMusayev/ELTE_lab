package practice.com.eltelinks.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import practice.com.eltelinks.dao.TeacherDAO;
import practice.com.eltelinks.dao.WebsiteDao;
import practice.com.eltelinks.database.ElteLinksDatabase;
import practice.com.eltelinks.model.Teacher;
import practice.com.eltelinks.model.Website;

public class ElteLinksRepository {
    private TeacherDAO teacherDAO;
    private LiveData<List<Teacher>> allTeachers;

    private WebsiteDao websiteDao;
    private LiveData<List<Website>> allWebsites;

    public ElteLinksRepository(Application application){
        ElteLinksDatabase elteDb = ElteLinksDatabase.getInstance(application);
        teacherDAO = elteDb.teacherDao();
        allTeachers = teacherDAO.getAllTeachers();

        websiteDao = elteDb.websiteDao();
        allWebsites = websiteDao.getaAllWebsites();
    }

    //Teachers operations
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

    //websites operations
    public void addWebsite(Website website){
        new AddWebsiteAsyncTask(websiteDao).execute(website);
    }

    public LiveData<List<Website>> getAllWebsites(){
        return allWebsites;
    }

    private static class AddWebsiteAsyncTask extends AsyncTask<Website, Void, Void>{
        private WebsiteDao websiteDao;

        private AddWebsiteAsyncTask(WebsiteDao websiteDao){
            this.websiteDao = websiteDao;
        }

        @Override
        protected Void doInBackground(Website... websites) {
            websiteDao.addWebsite(websites[0]);
            return null;
        }
    }
}
