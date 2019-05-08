package practice.com.eltelinks.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import practice.com.eltelinks.model.Teacher;

@Dao
public interface TeacherDAO {

    @Insert
    void addTeacher(Teacher teacher);

    @Query("SELECT * FROM teachers")
    LiveData<List<Teacher>> getAllTeachers();

}
