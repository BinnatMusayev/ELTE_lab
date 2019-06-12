package practice.com.eltelinks.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
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
    public void addTeacher(final Teacher teacher){

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                teacherDAO.addTeacher(teacher);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    public LiveData<List<Teacher>> getAllTeachers(){
        return allTeachers;
    }

    //websites operations
    public void addWebsite(Website website){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                websiteDao.addWebsite(website);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public LiveData<List<Website>> getAllWebsites(){
        return allWebsites;
    }



    public void deleteWebsite(Website website){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                websiteDao.deleteWebsite(website);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


}
