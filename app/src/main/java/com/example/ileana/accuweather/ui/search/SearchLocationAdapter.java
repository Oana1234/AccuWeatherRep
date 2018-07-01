package com.example.ileana.accuweather.ui.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ileana.accuweather.R;
import com.example.ileana.accuweather.api.SearchLocation;
import com.example.ileana.accuweather.managers.LocationManager;
import com.example.ileana.accuweather.models.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oana-Maria on 22/03/2018.
 */

public class SearchLocationAdapter extends RecyclerView.Adapter<SearchLocationAdapter.ViewHolder> {

    public interface Listener {
        void onLocationSelected(SearchLocation location);
    }

    private final List<SearchLocation> locations = new ArrayList<>();
    private Listener listener;

    public void setLocations(List<SearchLocation> locations) {
        this.locations.clear();
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(locations.get(position));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvLocationName;
        private SearchLocation location;

        ViewHolder(View itemView) {
            super(itemView);
            tvLocationName = itemView.findViewById(R.id.tv_location_name);
            itemView.setOnClickListener(this);
        }

        void bind(SearchLocation location) {
            this.location = location;

            tvLocationName.setText(location.getName() + ", " + location.getCountry());
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onLocationSelected(location);

            }
        }
    }


}
