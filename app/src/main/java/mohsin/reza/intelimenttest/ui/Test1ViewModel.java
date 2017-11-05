package mohsin.reza.intelimenttest.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * Created by Mohsin on 11/3/2017.
 */

public class Test1ViewModel extends ViewModel {
    private final MutableLiveData<Integer> appBackgroundColor = new MutableLiveData<Integer>();
    private final MutableLiveData<String> ifourval=new MutableLiveData<String>();

    @Inject
    Test1ViewModel(){}

    public void setIfourval(String val)
    {
        ifourval.setValue(val);
    }
    public LiveData<String> getIfourval(){return ifourval;}

    public void setAppBackgroundColor(Integer color)
    {
        appBackgroundColor.setValue(color);
    }
    public LiveData<Integer> getAppBagroundColor(){return  appBackgroundColor;}

}

