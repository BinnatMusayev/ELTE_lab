package practice.com.eltelinks.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import practice.com.eltelinks.dao.TeacherDAO;
import practice.com.eltelinks.dao.WebsiteDao;
import practice.com.eltelinks.model.Teacher;
import practice.com.eltelinks.model.Website;

@Database(entities = {Teacher.class, Website.class}, version = 1)
public abstract class ElteLinksDatabase extends RoomDatabase {

    private static ElteLinksDatabase instance;

    public abstract TeacherDAO teacherDao();
    public abstract WebsiteDao websiteDao();

    public static synchronized ElteLinksDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ElteLinksDatabase.class, "elte_db")
                    //if we don't do this when we change db/tables app will crash
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Completable.fromAction(new Action() {
                @Override
                public void run() throws Exception {
                    populate();
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
    };

    private static void populate() {
        TeacherDAO teacherDAO = instance.teacherDao();
        WebsiteDao websiteDao = instance.websiteDao();
        teacherDAO.addTeacher(new Teacher("Kitlei Róbert", "kitlei@elte.hu", "Informatics", "http://kitlei.web.elte.hu"));


        websiteDao.addWebsite(new Website("Elte", "https://www.elte.hu"));
        websiteDao.addWebsite(new Website("Canvas", "https://canvas.elte.hu"));
        websiteDao.addWebsite(new Website("Questura", "https://qter.elte.hu"));
        websiteDao.addWebsite(new Website("Neptun", "https://hallgato.neptun.elte.hu"));
        websiteDao.addWebsite(new Website("Master", "http://csmsc.elte.hu"));
        websiteDao.addWebsite(new Website("Bachelor", "http://csbsc.elte.hu"));
        websiteDao.addWebsite(new Website("Facebook", "https://www.facebook.com/elteinternational"));
        websiteDao.addWebsite(new Website("Instagram", "https://www.instagram.com/elte_official"));
        websiteDao.addWebsite(new Website("Twitter", "https://twitter.com/eotvos_uni"));
        websiteDao.addWebsite(new Website("LinkedIn", "https://www.linkedin.com/company/elte-faculty-of-informatics/"));
    }

}
