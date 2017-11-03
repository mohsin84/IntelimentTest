package mohsin.reza.intelimenttest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.graphics.Color;
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
    private int position;
    private Route route;
    private Integer backColor=Color.WHITE;
    public Model(final List<Route> routeList) {
        this.routeList=routeList;
        List<String> abc=new ArrayList<String>();
        for (Route rt:routeList) {
            abc.add(rt.name);
        }
        routes=abc.toArray(new String[abc.size()]);
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

    @Bindable
    public String[] getRoutes(){return routes;}

    public void setRoutes(String[] routes)
    {
        this.routes=routes;
        //notifyPropertyChanged(BR.);
        notifyPropertyChanged(BR.routes);
    }

    @Bindable
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        setRoute(routeList.get(position));
        //if (position != 0)

    }

    @Bindable
    public Route getRoute(){return route;}

    public void setRoute(Route route){
        this.route=route;
        notifyPropertyChanged(BR.route);
    }

    public int getPosition(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }

}
