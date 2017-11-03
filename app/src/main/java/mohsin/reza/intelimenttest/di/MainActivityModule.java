package mohsin.reza.intelimenttest.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mohsin.reza.intelimenttest.MainActivity;

/**
 * Created by Mohsin on 11/1/2017.
 */

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
