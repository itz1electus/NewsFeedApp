package com.mufasa.newsfeed;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Result result);

    @Update
    void update(Result result);

    @Delete
    void delete(Result result);

    @Query("DELETE FROM result_table")
    void deleteAllResults();

    @Query("SELECT * FROM result_table")
    LiveData<List<Result>> getAllResults();

    @Query("SELECT id FROM result_table")
    LiveData<List<Integer>> getAllId();
}
