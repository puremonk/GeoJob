package nl.thijswijnen.geojob.Model;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thijs_000 on 05-Dec-17.
 */

public abstract class Route implements Serializable
{
    private String routeTitle;
    private List<PointOfInterest> allPointOfInterests;
    private List<PointOfInterest> HKPointsOfInterests;
    private String descriptionEN;
    private String descriptionNL;

    public List<PointOfInterest> getAllPointsOfInterest()
    {
        return allPointOfInterests;
    }

    public List<PointOfInterest> getHKPointsOfInterests(){ return HKPointsOfInterests; }

    public Route() {
        allPointOfInterests = new ArrayList<>();
        HKPointsOfInterests = new ArrayList<>();
    }

    public void load(Context context)
    {
        //fill method 
    }

    public void setAllPointOfInterests(List<PointOfInterest> allPointOfInterests)
    {
        this.allPointOfInterests = allPointOfInterests;
    }

    public void setHKPointsOfInterests(List<PointOfInterest> HKPointsOfInterests){
        this.HKPointsOfInterests = HKPointsOfInterests;
    }

    public void setRouteTitle(String routeTitle)
    {
        this.routeTitle = routeTitle;
    }

    public String getRouteTitle()
    {
        return routeTitle;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionNL() {
        return descriptionNL;
    }

    public void setDescriptionNL(String descriptionNL) {
        this.descriptionNL = descriptionNL;
    }
}
