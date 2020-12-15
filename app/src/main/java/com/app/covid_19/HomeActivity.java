package com.app.covid_19;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.covid_19.Model.GlobalResponse;
import com.app.covid_19.network.ApiClientPrivate;
import com.app.covid_19.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
   TextView cases,todayCases,deaths,todayDeaths,recovered,active,critical,affectedCountries;
   Button countryTracker;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        cases=findViewById( R.id.CASES );
        todayCases=findViewById( R.id.TODAY_CASES );
        deaths=findViewById( R.id.DEATHS );
        todayDeaths=findViewById( R.id.TODAYS_DEATHS );
        recovered=findViewById( R.id.RECOVERED );
        active=findViewById( R.id.ACTIVE );
        critical=findViewById( R.id.CRITICAL );
        affectedCountries=findViewById( R.id.AFFECTED_COUNTRIES );
        countryTracker=findViewById( R.id.countryTracker );
        ApiCall();
        countryTracker.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CountryActivity.class);
                startActivity( intent );
            }
        } );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void ApiCall() {
        ApiInterface apiInterface=null;
        apiInterface= ApiClientPrivate.getApiClient().create( ApiInterface.class );
        Call<GlobalResponse> call= apiInterface.globalResponse();
        call.enqueue( new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {
                if (response.isSuccessful())
                {

                    String strCases= String.valueOf( response.body().getCases() );
                   String stodayCases=String.valueOf( response.body().getTodayCases() );
                   String sDeath=String.valueOf( response.body().getDeaths() );
                   String sTodayDeath=String.valueOf( response.body().getTodayDeaths() );
                   String sRecovered=String.valueOf( response.body().getRecovered() );
                   String sActive=String.valueOf( response.body().getActive() );
                   String sCritical=String.valueOf( response.body().getCritical() );
                   String sAffectedCountries=String.valueOf( response.body().getAffectedCountries() );

                  cases.setText( strCases );
                  todayCases.setText( stodayCases );
                  deaths.setText( sDeath );
                  todayDeaths.setText( sTodayDeath );
                  recovered.setText( sRecovered );
                  active.setText( sActive );
                  critical.setText( sCritical );
                  affectedCountries.setText( sAffectedCountries );


                }
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {

            }
        } );
    }
}