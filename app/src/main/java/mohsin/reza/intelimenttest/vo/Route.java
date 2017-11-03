package mohsin.reza.intelimenttest.vo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsin on 11/1/2017.
 */
@Entity(indices = {@Index("id")},primaryKeys = {"id"})
public class Route {

    @NonNull
    @SerializedName("id")
    public final int id;

    @SerializedName("name")
    public final String name;

    @SerializedName("fromcentral")
    @Embedded
    public final FromCentral fromcentral;

    @SerializedName("location")
    @Embedded
    public final Location location;

    public Route(int id, String name, FromCentral fromcentral, Location location)
    {
        this.id=id;
        this.name=name;
        this.fromcentral=fromcentral;
        this.location=location;
    }
    @Override
    public String toString()
    {
        return name!=null?name:super.toString();
    }
}
