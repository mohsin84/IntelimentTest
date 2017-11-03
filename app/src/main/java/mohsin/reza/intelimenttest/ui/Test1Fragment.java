package mohsin.reza.intelimenttest.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import mohsin.reza.intelimenttest.R;
import mohsin.reza.intelimenttest.databinding.FragmentTest1Binding;
import mohsin.reza.intelimenttest.di.Injectable;
import mohsin.reza.intelimenttest.util.AutoClearedValue;

/**
 * Created by Mohsin on 10/31/2017.
 */

public class Test1Fragment extends Fragment implements FiveItemAdapter.OnItemClickListener {
    AutoClearedValue<FragmentTest1Binding> binding;
    private String[] ItemNames;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        FragmentTest1Binding dataBinding= DataBindingUtil.inflate(layoutInflater, R.layout.fragment_test1,container,
                false);
        binding=new AutoClearedValue<>(this,dataBinding);
        ItemNames=getResources().getStringArray(R.array.five_items_array);
        binding.get().itemList.setAdapter(new FiveItemAdapter(ItemNames,this));
        binding.get().redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return dataBinding.getRoot();
    }

    @Override
    public void onClick(String name) {
        binding.get().setIfour(name);
    }
}

