package com.example.celi_labexer5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] comNames, comCountries, comIndustries, comCeos, des;
    int[] logo = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.apple, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.wells, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l};
    ArrayList<CompanyList> cmp = new ArrayList<>();
    ListView lstCompanies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TOP GLOBAL COMPANIES");

        comNames = getResources().getStringArray(R.array.comName);
        comCountries = getResources().getStringArray(R.array.comCountry);
        comIndustries = getResources().getStringArray(R.array.comIndustry);
        comCeos = getResources().getStringArray(R.array.comCeo);
        des = getResources().getStringArray(R.array.desc);

        for(int i=0; i < comNames.length; i++) {
            cmp.add(new CompanyList(logo[i], comNames[i], comCountries[i], comIndustries[i], comCeos[i]));
        }

        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.companies, cmp);
        lstCompanies = findViewById(R.id.lvCompany);
        lstCompanies .setAdapter(adapter);
        lstCompanies .setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) ;
        File read = new File(folder, "write.txt");
        try {

            final FileOutputStream show = new FileOutputStream(read);
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            String choice = cmp.get(i).getName() + "\n"
                    + cmp.get(i).getCountry() + "\n"
                    + cmp.get(i).getIndustry() + "\n"
                    + cmp.get(i).getCeo() + "\n";

            show.write(choice.getBytes());

            dialog.setTitle(cmp.get(i).getName());
            dialog.setIcon(cmp.get(i).getLogo());

            dialog.setMessage(des[i]);

            dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    try {
                        FileInputStream fin;
                        fin = new FileInputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/write.txt"));
                        int i;
                        String str = "";
                        while ((i = fin.read()) != -1) {
                            str += Character.toString((char) i);
                        }
                        fin.close();
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
            dialog.create().show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found.", Toast.LENGTH_LONG ).show();
        } catch (IOException e) {
            Toast.makeText(this, "Cannot Write...", Toast.LENGTH_LONG).show();
        }
    }
}
