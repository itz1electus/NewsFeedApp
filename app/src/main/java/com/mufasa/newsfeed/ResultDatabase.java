package com.mufasa.newsfeed;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Result.class, version = 2)
public abstract class ResultDatabase extends RoomDatabase {

    private static ResultDatabase instance;
    public abstract ResultDao resultDao();

    public static synchronized ResultDatabase getInstance(Context context) {
        if (instance ==  null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ResultDatabase.class, "result_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ResultDao resultDao;
        private PopulateDbAsyncTask(ResultDatabase db) {
            resultDao = db.resultDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            resultDao.insert(new Result("Bruh", "bruh", "bruh", "dont"));
            resultDao.insert(new Result("aefkjfekj", "jsefbfjsbkjsbfk", "sfkjebfkjs", "sjefb"));
            return null;
        }
    }
}