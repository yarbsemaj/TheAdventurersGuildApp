package com.yarbsemaj.theadventurersguild.arrayAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.arrayList.CharaArrayList;
import com.yarbsemaj.theadventurersguild.modal.CharaEntry;

import java.util.ArrayList;

/**
 * Created by yarbs on 06/02/2017.
 */


public class CharaAdapter extends ArrayAdapter<CharaEntry> {
    public CharaAdapter(Context context, ArrayList<CharaEntry> charaEntries) {
        super(context, 0, charaEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CharaEntry charaEntry = getItem(position);
        RecyclerView.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chara_list_item, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.listCharaName);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView2);

        name.setText(charaEntry.name);
        image.setImageResource(CharaArrayList.getCharaImage(charaEntry.type));
        Log.d("Run", charaEntry.name);
        return convertView;
    }


}
