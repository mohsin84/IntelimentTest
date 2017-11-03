package mohsin.reza.intelimenttest.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.util.Log;
import android.widget.Spinner;

import java.util.List;

import javax.inject.Inject;

import mohsin.reza.intelimenttest.repository.TestTwoRepository;
import mohsin.reza.intelimenttest.util.AbsentLiveData;
import mohsin.reza.intelimenttest.vo.Resource;
import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 11/1/2017.
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = Spinner.class, attribute = "android:selectedItemPosition"),
})
public class Test2ViewModel extends ViewModel {
    private final LiveData<List<Route>> results;

    @Inject
    Test2ViewModel(TestTwoRepository testTwoRepository)
    {
        results=Transformations.switchMap(testTwoRepository.loadRoutes("Test 2"),input -> {
            if(input==null)
            {
                Log.v("inside Viewmodel", "Data not found");
                return AbsentLiveData.create();
            }
            else
            {
                Log.v("inside Viewmodel", "Data found "+input.get(0).name);
                return testTwoRepository.loadRoutes("Test 2");
            }
        });

    }

    LiveData<List<Route>> getResults_rList(){
        return  results;
    }

}
