package practice.com.eltelinks.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import practice.com.eltelinks.model.Website;
import practice.com.eltelinks.repo.ElteLinksRepository;

public class WebsiteViewModel extends AndroidViewModel {
    private ElteLinksRepository repo;
    private LiveData<List<Website>> allWebsites;


    public WebsiteViewModel(@NonNull Application application) {
        super(application);

        repo = new ElteLinksRepository(application);
        allWebsites = repo.getAllWebsites();
    }

    public void addWebsite(Website website){
        repo.addWebsite(website);
    }

    public void deleteWebsite(Website website){
        repo.deleteWebsite(website);
    }

    public LiveData<List<Website>>  getAllWebsites(){
        return repo.getAllWebsites();
    }

}
