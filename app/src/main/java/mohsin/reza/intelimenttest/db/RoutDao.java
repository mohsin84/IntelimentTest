package mohsin.reza.intelimenttest.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 11/2/2017.
 */
@Dao
public abstract class RoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertRouteList(List<Route> routeList);

    @Query("Select * from Route") //check whether an order by is required
    public abstract LiveData<List<Route>> loadRouteList();
}
