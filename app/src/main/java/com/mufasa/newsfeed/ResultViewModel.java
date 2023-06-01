package com.mufasa.newsfeed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ResultViewModel extends AndroidViewModel {
    private ResultRepository resultRepository;
    private LiveData<List<Result>> allResults;
    private LiveData<List<Integer>> allIds;

    public ResultViewModel(@NonNull Application application) {
        super(application);
        resultRepository = new ResultRepository(application);
        allResults = resultRepository.getAllResults();
        allIds = resultRepository.getAllIds();
    }

    public void insert(Result result) {
        resultRepository.insert(result);
    }

    public void update(Result result) {
        resultRepository.update(result);
    }

    public void delete(Result result) {
        resultRepository.delete(result);
    }

    public void deleteAllResults() {
        resultRepository.deleteAllResults();
    }

    public LiveData<List<Result>> getAllResults() {
        return allResults;
    }

    public LiveData<List<Integer>> getAllIds() {
        return allIds;
    }
}
