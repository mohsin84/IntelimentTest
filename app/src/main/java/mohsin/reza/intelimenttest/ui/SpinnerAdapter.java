package mohsin.reza.intelimenttest.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 11/7/2017.
 */

public class SpinnerAdapter extends ArrayAdapter<Route>{
    private Context context;
    private List<Route> routearr;
    public SpinnerAdapter(@NonNull Context context, int resource, List<Route> routearr) {
        super(context, resource,routearr);
        this.context=context;
        this.routearr=routearr;
    }

    @Override
    public int getCount(){
        return routearr.size();
    }

    @Override
    public Route getItem(int position){
        return routearr.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(routearr.get(position).name);
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setPadding(15,5,15,5);
        label.setText(routearr.get(position).name);
        return label;
    }

}
