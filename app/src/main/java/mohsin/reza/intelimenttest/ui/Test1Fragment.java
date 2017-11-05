package mohsin.reza.intelimenttest.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
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

public class Test1Fragment extends Fragment implements FiveItemAdapter.OnItemClickListener, Injectable {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private Test1ViewModel test1ViewModel;
    private AutoClearedValue<FragmentTest1Binding> binding;

    private LiveData<String> ifourItem;
    private LiveData<Integer> bColor;
    private String[] ItemNames;
    private static final int NUM_PAGES = 4;
    private PagerAdapter mPagerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        FragmentTest1Binding dataBinding= DataBindingUtil.inflate(layoutInflater, R.layout.fragment_test1,container,
                false);

        binding=new AutoClearedValue<>(this,dataBinding);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        test1ViewModel= ViewModelProviders.of(this,viewModelFactory).get(Test1ViewModel.class);
        ItemNames=getResources().getStringArray(R.array.five_items_array);
        binding.get().itemList.setAdapter(new FiveItemAdapter(ItemNames,this));

        bColor=test1ViewModel.getAppBagroundColor();
        bColor.observe(this,integer -> {
            binding.get().getRoot().setBackgroundColor(integer);

        });
        ifourItem=test1ViewModel.getIfourval();
        ifourItem.observe(this,s -> {
            binding.get().setIfour(s);
        });

        binding.get().redBtn.setOnClickListener(v -> test1ViewModel.setAppBackgroundColor(Color.RED));
        binding.get().blueBtn.setOnClickListener(v -> {test1ViewModel.setAppBackgroundColor(Color.BLUE);});
        binding.get().greenBtn.setOnClickListener(v -> {test1ViewModel.setAppBackgroundColor(Color.GREEN);});

        mPagerAdapter = new PagerAdapter(getChildFragmentManager());
        binding.get().pager.setAdapter(mPagerAdapter);

    }

    @Override
    public void onClick(String name) {//Listener for RecyclerView item click
            test1ViewModel.setIfourval(name);
    }


    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PagerFragment.create(position) ;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

