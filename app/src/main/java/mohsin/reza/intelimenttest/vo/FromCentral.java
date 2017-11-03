package mohsin.reza.intelimenttest.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsin on 11/1/2017.
 */

public class FromCentral {
    @SerializedName("car")
    public final String car;

    @SerializedName("train")
    public final String train;

    public FromCentral(String car, String train)
    {
        this.car=car;
        this.train=train;
    }
}
