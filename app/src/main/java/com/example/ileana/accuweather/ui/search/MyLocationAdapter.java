package com.example.ileana.accuweather.ui.search;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.ileana.accuweather.models.Location;

import java.util.ArrayList;

/**
 * Created by Oana-Maria on 27/03/2018.
 */

public class MyLocationAdapter extends ArrayAdapter<Location> {


    private static class ViewHolder {
     //   private TextView itemView;
    }

    public MyLocationAdapter(Context context, int textViewResourceId, ArrayList<Location> locationArrayList) {
        super(context, textViewResourceId, locationArrayList);
    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(this.getContext())
//                    .inflate(R.layout.listview_association, parent, false);
//
//            viewHolder = new ViewHolder();
//            viewHolder.itemView = (TextView) convertView.findViewById(R.id.ItemView);
//
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        MyClass item = getItem(position);
//        if (item != null) {
//            // My layout has only one TextView
//            // do whatever you want with your string and long
//            viewHolder.itemView.setText(String.format("%s %d", item.reason, item.long_val));
//        }
//
//        return convertView;
//    }

}
