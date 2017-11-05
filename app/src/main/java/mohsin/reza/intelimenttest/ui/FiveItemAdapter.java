package mohsin.reza.intelimenttest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mohsin.reza.intelimenttest.databinding.FiveItemsBinding;

/**
 * Created by Mohsin on 11/3/2017.
 */

public class FiveItemAdapter extends RecyclerView.Adapter<FiveItemAdapter.ViewHolder> {

    private String[] mDataset;
    private OnItemClickListener mListener;

    public FiveItemAdapter(String[] mDataset, OnItemClickListener mListener){
        this.mDataset=mDataset;
        this.mListener=mListener;
    }
    public interface OnItemClickListener {
        void onClick(String name);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final FiveItemsBinding binding=FiveItemsBinding.inflate(layoutInflater,parent,false);
        binding.getRoot().setOnClickListener(v -> mListener.onClick(binding.getItem()));

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final FiveItemsBinding binding;

        public ViewHolder(FiveItemsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void bind(String item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
