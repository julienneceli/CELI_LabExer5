package com.example.celi_labexer5;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AndroidAdapter extends ArrayAdapter<CompanyList> {
    private Context context;
    private int resource;


    public AndroidAdapter(@NonNull Context context, int resource, @NonNull List<CompanyList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
        int logo = getItem(i).getLogo();
        String name = getItem(i).getName();
        String country = getItem(i).getCountry();
        String industry = getItem(i).getIndustry();
        String ceo = getItem(i).getCeo();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView img = convertView.findViewById(R.id.cLogo);
        TextView compName = convertView.findViewById(R.id.cName);
        TextView compCountry = convertView.findViewById(R.id.cCountry);
        TextView compIndustry = convertView.findViewById(R.id.cIndustry);
        TextView compCeo = convertView.findViewById(R.id.cCeo);

        img.setImageResource(logo);
        compName.setText(name);
        compCountry.setText(country);
        compIndustry.setText(industry);
        compCeo.setText(ceo);
        return convertView;


    }
}
