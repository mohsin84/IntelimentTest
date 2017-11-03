package mohsin.reza.intelimenttest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 11/2/2017.
 */
@Database(entities = {Route.class},version = 1)
public abstract class TestDB extends RoomDatabase{
    abstract public RoutDao routDao();

}
