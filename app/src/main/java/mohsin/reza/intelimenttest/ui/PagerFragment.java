package mohsin.reza.intelimenttest.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import mohsin.reza.intelimenttest.R;
import mohsin.reza.intelimenttest.databinding.PagerItemBinding;
import mohsin.reza.intelimenttest.di.Injectable;
import mohsin.reza.intelimenttest.util.AutoClearedValue;

/**
 * Created by Mohsin on 11/3/2017.
 */

public class PagerFragment extends Fragment implements Injectable {
    public static final String ARG_PAGE = "page";
    private int mPageNumber;
    private AutoClearedValue<PagerItemBinding> binding;

    public static PagerFragment create(int pageNumber) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PagerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PagerItemBinding databinding= DataBindingUtil.inflate(layoutInflater, R.layout.pager_item,container, false);

        binding=new AutoClearedValue<>(this,databinding);
        binding.get().pitext.setText("Fragment "+(mPageNumber+1));
        binding.get().pitext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Fragment "+(mPageNumber+1),Toast.LENGTH_LONG).show();
            }
        });

        return databinding.getRoot();
    }

}
