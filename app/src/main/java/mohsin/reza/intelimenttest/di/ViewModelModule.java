package mohsin.reza.intelimenttest.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import mohsin.reza.intelimenttest.ui.Test1ViewModel;
import mohsin.reza.intelimenttest.ui.Test2ViewModel;
import mohsin.reza.intelimenttest.viewmodel.IntelViewModelFactory;

/**
 * Created by Mohsin on 11/1/2017.
 */

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(Test2ViewModel.class)
    abstract ViewModel binTest2ViewModel(Test2ViewModel test2ViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(Test1ViewModel.class)
    abstract ViewModel binTest1ViewModel(Test1ViewModel test1ViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(IntelViewModelFactory intelViewModelFactory);
}
