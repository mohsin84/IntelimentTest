package mohsin.reza.intelimenttest.util;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import mohsin.reza.intelimenttest.api.ApiResponse;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohsin on 10/4/2017.
 */



public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {

    private final Type responseType;
    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ApiResponse<R>> adapt(final Call<R> call) {
        return new LiveData<ApiResponse<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);
            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            Log.v("Success","Request succeed");

                            postValue(new ApiResponse<>(response));
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            Log.v("Failure","Request failed");
                            postValue(new ApiResponse<R>(t));
                        }
                    });
                }
            }
        };
    }

}
