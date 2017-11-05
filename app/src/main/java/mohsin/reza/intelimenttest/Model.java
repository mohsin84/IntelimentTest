package mohsin.reza.intelimenttest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.graphics.Color;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mohsin.reza.intelimenttest.vo.Route;

/**
 * Created by Mohsin on 11/2/2017.
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = Spinner.class, attribute = "android:selectedItemPosition"),
})
public class Model extends BaseObservable {
    private List<Route> routeList;

    private String[] routes;
    private Integer position;
    private Route route;

    private Integer backColor=Color.WHITE;

    public Model() {
        Log.v("Model","New instance create");


    }


    @Bindable
    public String[] getRoutes(){return routes;}

    public void setRoutes(final List<Route> routeList)
    {
        Log.v("Model","Routes arrayList changed");
        this.routeList=routeList;
        List<String> abc=new ArrayList<String>();
        for (Route rt:routeList) {
            abc.add(rt.name);
        }
        routes=abc.toArray(new String[abc.size()]);
        this.routes=routes;
        notifyPropertyChanged(BR.routes);
    }

    @Bindable
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        Log.v("Model","Position changed");
        this.position = position;
        setRoute(routeList.get(position));
    }

    @Bindable
    public Route getRoute(){return route;}

    public void setRoute(Route route){
        Log.v("Model","Route changed");
        this.route=route;
        notifyPropertyChanged(BR.route);
    }


    @Bindable
    public void setBackColor(String backColor){
        if(backColor=="red")
            this.backColor=Color.RED;
        else if(backColor=="blue")
            this.backColor=Color.BLUE;
        else if(backColor=="green")
            this.backColor=Color.GREEN;
        notifyPropertyChanged(BR.backColor);
    }
    public Integer getBackColor()
    {
        return backColor;
    }

}
