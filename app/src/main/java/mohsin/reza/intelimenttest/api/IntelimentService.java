package mohsin.reza.intelimenttest.api;

import android.arch.lifecycle.LiveData;

import java.util.List;

import mohsin.reza.intelimenttest.vo.Route;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mohsin on 11/1/2017.
 */

public interface IntelimentService {
    @GET("sample.json")
    LiveData<ApiResponse<List<Route>>> getTransportRoute();

    @GET("sample.json")
    LiveData<List<Route>> getSimpleTransportRoute();
}
