package practice.com.eltelinks.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        TeacherDAO teacherDAO;
        WebsiteDao websiteDao;

        public PopulateDbAsyncTask(ElteLinksDatabase elteLinksDatabase){
            this.teacherDAO = elteLinksDatabase.teacherDao();
            this.websiteDao = elteLinksDatabase.websiteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            teacherDAO.addTeacher(new Teacher("Kitlei Roberts", "kitlei@elte.hu", "Informatics", "kitlei.hu"));
            teacherDAO.addTeacher(new Teacher("Melinda Toth", "melinda@elte.hu", "Informatics", "melinda.hu"));

            websiteDao.addWebsite(new Website("Elte", "https://www.elte.hu"));
            websiteDao.addWebsite(new Website("Canvas", "https://canvas.elte.hu"));
            websiteDao.addWebsite(new Website("Neptun", "https://neptun.elte.hu"));
            return null;
        }
    }

}
