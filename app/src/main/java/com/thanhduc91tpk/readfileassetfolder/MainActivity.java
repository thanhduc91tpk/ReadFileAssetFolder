package com.thanhduc91tpk.readfileassetfolder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.thanhduc91tpk.readfileassetfolder.adapter.Adapter;
import com.thanhduc91tpk.readfileassetfolder.model.Header;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    List<String> strings;
    List<Header> listAdapter = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReadFile();
        grouping();
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(listAdapter);
        recyclerView.setAdapter(adapter);
    }

    public void ReadFile(){
        String str = "";
        try {
            InputStream inputStream = getAssets().open("name_list.txt");
            int size = inputStream.available();
            byte [] buffer = new byte[size];
            inputStream.read(buffer);

            str = new String(buffer);


        } catch (IOException e) {
            e.printStackTrace();
        }

        String lines[] = str.split("[\\r\\n]+");
        strings = new ArrayList<>();
        for(int i = 0 ; i < lines.length ; i++){
            strings.add(lines[i]);
        }

        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.substring(0,1).compareTo(t1.substring(0,1));
            }
        });

    }

    public void grouping(){
        int index = 0;
        int partitionSize = 12;
        List<List<String>> partitions = new ArrayList<>();

        for (int i=0; i<strings.size(); i += partitionSize) {
            partitions.add(strings.subList(i, Math.min(i + partitionSize, strings.size())));
        }

//        for (List<String> list : partitions) {
//            //Do your stuff on each sub list
//        }

            for(List<String> list : partitions){
                index++;
                listAdapter.add(new Header("Group "+index,list));
            }
    }
}