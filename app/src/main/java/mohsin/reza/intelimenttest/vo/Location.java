package mohsin.reza.intelimenttest.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsin on 11/1/2017.
 */

public class Location {
    @SerializedName("latitude")
    public final String latitude;

    @SerializedName("longitude")
    public final String longitude;

    public Location(String latitude, String longitude)
    {
        this.latitude=latitude;
        this.longitude=longitude;
    }
}
