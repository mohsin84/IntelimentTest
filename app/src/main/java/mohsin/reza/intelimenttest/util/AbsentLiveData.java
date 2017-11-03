package mohsin.reza.intelimenttest.util;

import android.arch.lifecycle.LiveData;

/**
 * Created by mohsin on 10/5/2017.
 */

public class AbsentLiveData extends LiveData {
    private AbsentLiveData() {
        postValue(null);
    }
    public static <T> LiveData<T> create() {
        //noinspection unchecked
        return new AbsentLiveData();
    }
}
