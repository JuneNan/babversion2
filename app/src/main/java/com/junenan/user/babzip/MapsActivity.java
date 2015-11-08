package com.junenan.user.babzip;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private LatLng loc;
    private Marker mMarker;
    private ImageView kakaobtn;
    private ImageView googlebtn;
    private ImageView naverbtn;
    private ImageView twitterbtn;
    private ImageView fbbtn;
    private ImageView linebtn;
    private TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initUI();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_char_1)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMyLocationEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
    }



    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kakaobtn:
                openApp(this, "com.kakao.talk");
                break;
            case R.id.googlebtn:
                openApp(this, "com.google.android.googlequicksearchbox");
                break;
            case R.id.naverbtn:
                openApp(this, "com.nhn.android.search");
                break;
            case R.id.twitterbtn:
                openApp(this, "com.twitter.android");
                break;
            case R.id.fbbtn:
                openApp(this, "com.facebook.katana");
                break;
            case R.id.linebtn:
                openApp(this, "jp.naver.line.android");
                break;

        }

    }

    public void initUI(){
        kakaobtn = (ImageView) findViewById(R.id.kakaobtn);
        googlebtn = (ImageView) findViewById(R.id.googlebtn);
        naverbtn = (ImageView) findViewById(R.id.naverbtn);
        twitterbtn = (ImageView) findViewById(R.id.twitterbtn);
        fbbtn = (ImageView) findViewById(R.id.fbbtn);
        linebtn = (ImageView) findViewById(R.id.linebtn);
        kakaobtn.setOnClickListener( this );
        googlebtn.setOnClickListener( this );
        naverbtn.setOnClickListener(this);
        twitterbtn.setOnClickListener(this);
        fbbtn.setOnClickListener(this);
        linebtn.setOnClickListener(this);

        infoText = (TextView) findViewById(R.id.infoText);
        Typeface face=Typeface.createFromAsset(getAssets(),"soyabarun9.ttf");
        infoText.setTypeface(face);
    }

    /** Open another app.
     * @param context current Context, like Activity, App, or Service
     * @param packageName the full package name of the app to open
     * @return true if likely successful, false if unsuccessful
     */
    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                Intent ei = new Intent(android.content.Intent.ACTION_VIEW);
                ei.setData(Uri.parse("https://play.google.com/store/apps/details?id="+packageName));
                context.startActivity(ei);
                return false;
                //throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
