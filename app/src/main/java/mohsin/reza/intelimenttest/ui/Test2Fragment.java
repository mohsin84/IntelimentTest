package mohsin.reza.intelimenttest.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mohsin.reza.intelimenttest.BR;
import mohsin.reza.intelimenttest.Model;
import mohsin.reza.intelimenttest.R;
import mohsin.reza.intelimenttest.databinding.FragmentTest2Binding;
import mohsin.reza.intelimenttest.di.Injectable;
import mohsin.reza.intelimenttest.util.AutoClearedValue;
import mohsin.reza.intelimenttest.vo.Resource;
import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 10/31/2017.
 */

public class Test2Fragment extends Fragment implements Injectable{
    @Inject
    ViewModelProvider.Factory viewModelFactory;


    AutoClearedValue<FragmentTest2Binding> binding;

    private Test2ViewModel test2ViewModel;
    List<CharSequence> myList;
    ArrayAdapter<CharSequence> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        FragmentTest2Binding dataBinding= DataBindingUtil.inflate(layoutInflater, R.layout.fragment_test2,container,
                        false);

        binding=new AutoClearedValue<>(this,dataBinding);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        test2ViewModel= ViewModelProviders.of(this,viewModelFactory).get(Test2ViewModel.class);
        test2ViewModel.getResults_rList().observe(this,routeList -> {
            applyChange(routeList);
        });

        binding.get().mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Test2frag","inside onClickCallBack");
                Route route_obj=binding.get().getModel().getRoute();
                Fragment mapfragment=MapFragment.Create(route_obj.name,route_obj.location.latitude,route_obj.location.longitude);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame,mapfragment).addToBackStack("map")
                        .commit();
            }
        });
    }

    public void applyChange(final List<Route> froutList)
    {
        if(froutList!=null)
        {
            Model model=new Model(froutList);
            binding.get().setModel(model);
            binding.get().executePendingBindings();
        }
    }
    public int getPosition(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }

    public void OnClickCallback()
    {
        Log.v("Test2frag","inside onClickCallBack");
        Route route_obj=binding.get().getRoute();
        Fragment mapfragment=MapFragment.Create(route_obj.name,route_obj.location.latitude,route_obj.location.longitude);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame,mapfragment)
                .commit();
    }
}

