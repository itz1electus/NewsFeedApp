package com.mufasa.newsfeed;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ResultRepository {
    private ResultDao resultDao;
    private LiveData<List<Result>> allResults;

    public ResultRepository(Application application) {
        ResultDatabase resultDatabase = ResultDatabase.getInstance(application);
        resultDao = resultDatabase.resultDao();
        allResults = resultDao.getAllResults();
    }

    public void insert(Result result) {
        new InsertResultAsyncTask(resultDao).execute(result);
    }

    public void update(Result result) {
        new UpdateResultAsyncTask(resultDao).execute(result);
    }

    public void delete(Result result) {
        new DeleteResultAsyncTask(resultDao).execute(result);
    }

    public void deleteAllResults() {
        new DeleteAllResultsAsyncTask(resultDao).execute();
    }

    public LiveData<List<Result>> getAllResults() {
        return allResults;
    }

    public static class InsertResultAsyncTask extends AsyncTask<Result, Void, Void> {
        private ResultDao resultDao;
        private InsertResultAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.insert(results[0]);
            return null;
        }
    }

    public static class UpdateResultAsyncTask extends AsyncTask<Result, Void, Void> {
        private ResultDao resultDao;
        private UpdateResultAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.update(results[0]);
            return null;
        }
    }

    public static class DeleteResultAsyncTask extends AsyncTask<Result, Void, Void> {
        private ResultDao resultDao;
        private DeleteResultAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            resultDao.delete(results[0]);
            return null;
        }
    }

    public static class DeleteAllResultsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ResultDao resultDao;
        private DeleteAllResultsAsyncTask(ResultDao resultDao) {
            this.resultDao = resultDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            resultDao.deleteAllResults();
            return null;
        }
    }
}
