package mohsin.reza.intelimenttest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import mohsin.reza.intelimenttest.databinding.DrawerListItemBinding;

/**
 * Created by Mohsin on 11/1/2017.
 */

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder> {

    private String[] mDataset;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onClick(String position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final DrawerListItemBinding binding;

        public ViewHolder(DrawerListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(String item) {
            binding.setTname(item);
            binding.executePendingBindings();
        }
    }

    public NavAdapter(String[] myDataset, OnItemClickListener listener) {
        this.mDataset = myDataset;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final DrawerListItemBinding binding=DrawerListItemBinding.inflate(layoutInflater,parent,false);
        binding.getRoot().setOnClickListener(v -> mListener.onClick(binding.getTname()));

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String abc=mDataset[position];
        holder.bind(abc);
     }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
