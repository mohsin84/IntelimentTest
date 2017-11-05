package mohsin.reza.intelimenttest.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import javax.inject.Inject;

import mohsin.reza.intelimenttest.Model;
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
    LiveData<List<Route>> routeListBind;
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
        routeListBind=test2ViewModel.getResults_rList();
        routeListBind.observe(this,routeList -> {
            applyChange(routeList);
        });

        binding.get().mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            Model model=new Model();
            model.setRoutes(froutList);
            binding.get().setModel(model);
            binding.get().executePendingBindings();
        }
    }

}

