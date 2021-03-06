package mohsin.reza.intelimenttest.ui;

import android.arch.lifecycle.LiveData;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mohsin.reza.intelimenttest.R;
import mohsin.reza.intelimenttest.databinding.FragmentTest2Binding;
import mohsin.reza.intelimenttest.di.Injectable;
import mohsin.reza.intelimenttest.util.AutoClearedValue;
import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 10/31/2017.
 */

public class Test2Fragment extends Fragment implements Injectable{
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    AutoClearedValue<FragmentTest2Binding> binding;

    private Test2ViewModel test2ViewModel;
    List<Route> myroutarr;
    SpinnerAdapter spinnerAdapter;

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

        myroutarr=new ArrayList<Route>();
        test2ViewModel.getResults_rList().observe(this,routeList -> {
            if(routeList!=null)
            {
                int i = 0;
                if(myroutarr==null) {
                    myroutarr=routeList;
                }
                else if(myroutarr.size()<routeList.size())
                {
                    myroutarr=routeList;
                }
                spinnerAdapter=new SpinnerAdapter(getContext(),android.R.layout.simple_spinner_item,myroutarr);
                binding.get().spinner.setAdapter(spinnerAdapter);
            }
        });

        LiveData<Integer> pos=test2ViewModel.getPosition_viewmodel();
        pos.observe(this,integer -> {
            binding.get().setPosition(integer);
            if(myroutarr.size()>0)
            binding.get().setRoute(myroutarr.get(integer));
            binding.get().executePendingBindings();
        });

        binding.get().spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                test2ViewModel.setPosition_viewmodel(position);
                binding.get().setRoute(myroutarr.get(position));
                binding.get().executePendingBindings();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.get().mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route route_obj=binding.get().getRoute();
                Fragment mapfragment=MapFragment.Create(route_obj.name,route_obj.location.latitude,route_obj.location.longitude);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame,mapfragment).addToBackStack("map")
                        .commit();
            }
        });
    }

}

