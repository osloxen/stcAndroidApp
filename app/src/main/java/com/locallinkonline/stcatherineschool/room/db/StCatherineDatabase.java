package com.locallinkonline.stcatherineschool.room.db;

import android.content.Context;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.tasks.GetNewDataResourcesTask;
import com.locallinkonline.locallinkschool.room.converter.DateConverter;
import com.locallinkonline.stcatherineschool.room.dao.DataResourcesDao;
import com.locallinkonline.stcatherineschool.room.dao.LunchDao;
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LunchEntity.class,
                      ScheduleEntity.class,
                      MenuEntity.class},
          version = 1,
          exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class StCatherineDatabase extends RoomDatabase {
    public abstract LunchDao lunchDao();
    public abstract SchoolScheduleDao schoolScheduleDao();
    public abstract DataResourcesDao dataResourcesDao();

    private static StCatherineDatabase INSTANCE;

    public static StCatherineDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StCatherineDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            StCatherineDatabase.class,
                            "stc_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    String url = context.getString(R.string.stcBaseUrl);
                                    new GetNewDataResourcesTask(INSTANCE, url, null).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }
}
