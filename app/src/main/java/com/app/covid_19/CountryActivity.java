package com.app.covid_19;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.covid_19.Adapter.CountryAdapter;
import com.app.covid_19.Model.Allcountry.CountryInfo;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {
 RecyclerView recyclerView;
 List<CountryInfo> countryInfoList;
 CountryAdapter countryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_country );
        recyclerView=findViewById( R.id.recyclerView );
        countryInfoList=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
         ApiCall();
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle( "All Countries" );
        actionBar.setDisplayHomeAsUpEnabled( true );
        actionBar.setDisplayHomeAsUpEnabled( true );

    }

    private void ApiCall() {
        String URL="https://disease.sh/v3/covid-19/countries";
        StringRequest request=new StringRequest( Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for (int i=0; i<jsonArray.length();i++)
                            {

                                JSONObject jsonObject= jsonArray.getJSONObject( i );
                                String countryname=jsonObject.getString( "country" ) ;
                             JSONObject jsonObject1=jsonObject.getJSONObject( "countryInfo" );
                             String countryflag=jsonObject1.getString( "flag" );
                             CountryInfo countryInfo=new CountryInfo( countryname,countryflag );
                             countryInfoList.add( countryInfo );
                            }
                            countryAdapter =new CountryAdapter(CountryActivity.this,countryInfoList);
                            recyclerView.setAdapter( countryAdapter );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText( CountryActivity.this, ""+error, Toast.LENGTH_SHORT ).show();

            }
        } );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add( request );
    }
}