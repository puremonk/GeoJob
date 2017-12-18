package nl.thijswijnen.geojob.Model;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.concurrent.Executor;

import nl.thijswijnen.geojob.UI.DetailPoiActivity;
import nl.thijswijnen.geojob.Util.Constants;

/**
 * Created by thijs_000 on 18-Dec-17.
 */

public class GeoFenceHandler
{
    private GeofencingClient geofencingClient;
    private Context context;
    private List<Geofence> geofenceList;
    private PendingIntent geofencePendingIntent;

    public GeoFenceHandler(Context context)
    {
        this.context = context;
        geofencingClient = LocationServices.getGeofencingClient(context);
    }

    @SuppressLint("MissingPermission")
    public void addGeofenceToClient()
    {
        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener((Executor) context, new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        // do things
                    }
                })
                .addOnFailureListener((Executor) context, new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        //do things
                    }
                });
    }

    public void createGeoFence(LatLng geoLatLng)
    {
        geofenceList.add(new Geofence.Builder()
        .setRequestId("?")
        .setCircularRegion(geoLatLng.latitude, geoLatLng.longitude, Constants.GEOFENCE_RADIUS_IN_METERS)
        .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
        .build());
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(geofenceList);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent()
    {
        if (geofencePendingIntent != null)
        {
            return geofencePendingIntent;
        }
        Intent intent = new Intent(context, DetailPoiActivity.class);
        geofencePendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return geofencePendingIntent;
    }
}
