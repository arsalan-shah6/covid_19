package com.app.covid_19;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailedActivity extends AppCompatActivity {
    TextView cases,todayCases,deaths,todayDeaths,recovered,active,critical,globalstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detailed );
        cases=findViewById( R.id.CASES );
        todayCases=findViewById( R.id.TODAY_CASES );
        deaths=findViewById( R.id.DEATHS );
        todayDeaths=findViewById( R.id.TODAYS_DEATHS );
        recovered=findViewById( R.id.RECOVERED );
        active=findViewById( R.id.ACTIVE );
        critical=findViewById( R.id.CRITICAL );
        globalstate=findViewById( R.id.GlobalState );

        Intent intent=getIntent();
        String Cname=intent.getStringExtra( "key" );
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle( Cname );
        actionBar.setDisplayHomeAsUpEnabled( true );
        actionBar.setDisplayHomeAsUpEnabled( true );
        globalstate.setText( Cname+"State" );


        ApiCall(Cname);
    }

    private void ApiCall(String cname) {
        String URI="https://disease.sh/v3/covid-19/countries/"+cname;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest( Request.Method.GET, URI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            cases.setText( jsonObject.getString( "cases" ) );
                            todayCases.setText(  jsonObject.getString( "todayCases" )  );
                            deaths.setText(  jsonObject.getString( "deaths" )  );
                            todayDeaths.setText(  jsonObject.getString( "todayDeaths" )  );
                            recovered.setText(  jsonObject.getString( "recovered" )  );
                            active.setText(  jsonObject.getString( "active" )  );
                            critical.setText(  jsonObject.getString( "critical" )  );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( DetailedActivity.this, ""+error, Toast.LENGTH_SHORT ).show();

            }
        } );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add( request );

    }
}