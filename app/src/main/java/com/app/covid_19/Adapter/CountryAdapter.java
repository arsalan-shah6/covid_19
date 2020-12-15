package com.app.covid_19.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.covid_19.DetailedActivity;
import com.app.covid_19.Model.Allcountry.CountryInfo;
import com.app.covid_19.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.Myholder> {
    Context context;
    List<CountryInfo> countryInfoList;

    public CountryAdapter(Context context, List<CountryInfo> countryInfoList) {
        this.context = context;
        this.countryInfoList = countryInfoList;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate(R.layout.country_list,parent,false);
        return new Myholder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        String flag=countryInfoList.get( position ).getFlag();
        String name=countryInfoList.get( position ).getName();
        holder.countryName.setText( name);
        Glide.with(context ).load( flag ).into( holder.countryFlag );

    }

    @Override
    public int getItemCount() {
        return countryInfoList.size();
    }

    class Myholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView countryFlag;
        TextView countryName;
        public Myholder(@NonNull View itemView) {
            super( itemView );
            countryFlag=itemView.findViewById( R.id.CountryFlag );
            countryName=itemView.findViewById( R.id.CountryName );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, DetailedActivity.class );
            intent.putExtra( "key" , countryInfoList.get(getAdapterPosition()).getName());
            context.startActivity( intent );
        }
    }
}
