package com.yarbsemaj.theadventurersguild.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.arrayAdapter.CharaAdapter;
import com.yarbsemaj.theadventurersguild.arrayList.CharaArrayList;
import com.yarbsemaj.theadventurersguild.modal.CharaEntry;

public class CharaList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chara_list);

        Log.d("Chra", "dsp");
        final ListView listView = (ListView) findViewById(R.id.charaList);
        final ArrayAdapter<CharaEntry> itemsAdapter = new CharaAdapter(this, new CharaArrayList(this));
        listView.setScrollingCacheEnabled(false);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent = new Intent(getApplicationContext(), CharaView.class);
                mIntent.putExtra("chara", itemsAdapter.getItem(i));
                startActivity(mIntent);
            }
        });
    }
}
