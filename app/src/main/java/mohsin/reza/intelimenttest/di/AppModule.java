package mohsin.reza.intelimenttest.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mohsin.reza.intelimenttest.api.IntelimentService;
import mohsin.reza.intelimenttest.db.RoutDao;
import mohsin.reza.intelimenttest.db.TestDB;
import mohsin.reza.intelimenttest.util.LiveDataCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohsin on 11/1/2017.
 */

@Module(includes = ViewModelModule.class)
class AppModule {

    @Singleton @Provides
    IntelimentService provideIntelimentService(){
        return new Retrofit.Builder()
                .baseUrl("http://express-it.optusnet.com.au/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(IntelimentService.class);
    }

    @Singleton @Provides
    TestDB providesDB(Application application){
        return Room.databaseBuilder(application,TestDB.class,"intelimenttest.db").fallbackToDestructiveMigration().build();
    }

    @Singleton @Provides
    RoutDao provideRoutDao(TestDB testDB){ return testDB.routDao();}
}
