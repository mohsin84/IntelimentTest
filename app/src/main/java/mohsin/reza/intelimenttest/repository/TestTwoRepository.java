package mohsin.reza.intelimenttest.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import mohsin.reza.intelimenttest.AppExecutors;
import mohsin.reza.intelimenttest.api.ApiResponse;
import mohsin.reza.intelimenttest.api.IntelimentService;
import mohsin.reza.intelimenttest.db.RoutDao;
import mohsin.reza.intelimenttest.db.TestDB;
import mohsin.reza.intelimenttest.util.RateLimiter;
import mohsin.reza.intelimenttest.vo.Resource;
import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 11/1/2017.
 */

public class TestTwoRepository {

    private final AppExecutors appExecutors;
    private final TestDB testDB;
    private final RoutDao routDao;
    private final IntelimentService intelimentService;

    private MutableLiveData<List<Route>> routeList=new MutableLiveData<>();
    private LiveData<List<Route>> repo_routelist;
    private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);
    //private MediatorLiveData<ApiResponse<List<Route>>> result=new MediatorLiveData<>();

    @Inject
    public TestTwoRepository(AppExecutors appExecutors, TestDB testDB, RoutDao routDao,IntelimentService intelimentService)
    {
        this.appExecutors=appExecutors;
        this.testDB=testDB;
        this.routDao=routDao;
        this.intelimentService=intelimentService;

    }

    public LiveData<List<Route>> loadRoutes(String fetchData)
    {
        return new NetworkBoundResource<List<Route>,List<Route>>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull List<Route> item) {
                testDB.beginTransaction();
                try {
                    routDao.insertRouteList(item);
                    testDB.setTransactionSuccessful();
                }finally {
                    testDB.endTransaction();
                }

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Route> data) {
                return data==null || data.isEmpty();//||repoListRateLimit.shouldFetch(fetchData);
            }

            @NonNull
            @Override
            protected LiveData<List<Route>> loadFromDb() {
                return routDao.loadRouteList();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Route>>> createCall() {
                return intelimentService.getTransportRoute();
            }
            @Override
            protected void onFetchFailed() {
                repoListRateLimit.reset(fetchData);
            }
            @Override
            protected List<Route> processResponse(ApiResponse<List<Route>> response){
                List<Route> body=response.body;
                return body;

            }
        }.asLiveData();
    }
}
