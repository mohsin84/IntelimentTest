package mohsin.reza.intelimenttest.ui;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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
import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 11/1/2017.
 */

public class Test2ViewModel extends ViewModel {
    private final LiveData<List<Route>> results;
    @Inject
    Test2ViewModel(TestTwoRepository testTwoRepository)
    {
        results=Transformations.switchMap(testTwoRepository.loadRoutes("Test 2"),input -> {
            if(input==null)
            {
                return AbsentLiveData.create();
            }
            else
            {
                return testTwoRepository.loadRoutes("Test 2");
            }
        });
    }

    LiveData<List<Route>> getResults_rList(){
        return  results;
    }

}
