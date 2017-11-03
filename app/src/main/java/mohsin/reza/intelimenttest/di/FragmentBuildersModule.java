package mohsin.reza.intelimenttest.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mohsin.reza.intelimenttest.ui.Test1Fragment;
import mohsin.reza.intelimenttest.ui.Test2Fragment;

/**
 * Created by Mohsin on 11/1/2017.
 */

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract Test2Fragment contributeTest2Fragment();

    @ContributesAndroidInjector
    abstract Test1Fragment contributeTest1Fragment();
}
