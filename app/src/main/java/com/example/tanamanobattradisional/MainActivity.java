package com.example.tanamanobattradisional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ModelData> listTanaman;
    SearchView searchView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listTanaman = new ArrayList<>();
        recyclerView = findViewById(R.id.rvListTanaman);


        // Get data from local JSON
        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("daftar_tanaman");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject dataTanaman = jsonArray.getJSONObject(i);
                listTanaman.add(new ModelData(
                        dataTanaman.getString("nama"),
                        dataTanaman.getString("image_url"),
                        dataTanaman.getString("deskripsi")
                ));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }


        // add to recyclerView
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterData adapterData = new AdapterData(this, listTanaman);
        recyclerView.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

        filteredListData();


    }


    private String JsonDataFromAsset(){
        String json = null;
        try {
            InputStream inputStream = getAssets().open("tanaman_obat.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

//    private void filterList(String text) {
//        ArrayList<ModelData> filteredList = new ArrayList<>();
//        for (ModelData modelData : listTanaman){
//            if (modelData.getNama().toLowerCase().contains(text.toLowerCase())){
//                filteredList.add(modelData);
//            }
//        }
//
//        if (filteredList.isEmpty()){
//            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
//        } else {
//            AdapterData adapterData = new AdapterData(this, filteredList);
//            adapterData.notifyDataSetChanged();
//        }
//    }

    private void filteredListData(){
        searchView = findViewById(R.id.searchTanaman);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {

                ArrayList<ModelData> filteredTanaman = new ArrayList<>();

                for (ModelData modelData : listTanaman){
                    if(modelData.getNama().toLowerCase().contains(searchText.toLowerCase())){
                        filteredTanaman.add(modelData);
                    }
                }

                AdapterData adapterFilteredData = new AdapterData(getApplicationContext(), filteredTanaman);
                recyclerView.setAdapter(adapterFilteredData);
                adapterFilteredData.notifyDataSetChanged();

                return true;
            }
        });
    }

}