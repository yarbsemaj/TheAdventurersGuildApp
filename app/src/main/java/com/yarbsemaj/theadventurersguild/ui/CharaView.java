package com.yarbsemaj.theadventurersguild.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.arrayAdapter.StatArrayAdapter;
import com.yarbsemaj.theadventurersguild.arrayList.CharaArrayList;
import com.yarbsemaj.theadventurersguild.arrayList.StatArrayList;
import com.yarbsemaj.theadventurersguild.modal.CharaEntry;

public class CharaView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chara_view);

        final CharaEntry charaEntry = (CharaEntry) getIntent().getExtras().get("chara");

        TextView name = (TextView) findViewById(R.id.charaName);
        TextView heal = (TextView) findViewById(R.id.heal);

        ImageView charaImage = (ImageView) findViewById(R.id.imageView);

        ListView statList = (ListView) findViewById(R.id.statList);

        name.setText(charaEntry.name);
        heal.setText(charaEntry.currentHealth + "/" + charaEntry.maxHealth);

        charaImage.setImageResource(CharaArrayList.getCharaImage(charaEntry.type));

        statList.setAdapter(new StatArrayAdapter(this, new StatArrayList(this, charaEntry.characterID)));
    }
}
