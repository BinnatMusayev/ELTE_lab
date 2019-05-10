package practice.com.eltelinks.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import practice.com.eltelinks.model.Website;

@Dao
public interface WebsiteDao {

    @Insert
    void addWebsite(Website website);

    @Query("SELECT * FROM websites")
    LiveData<List<Website>> getaAllWebsites();
}
