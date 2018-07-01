package com.example.ileana.accuweather.tabs.current;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ileana.accuweather.R;
import com.example.ileana.accuweather.models.WeatherCondition;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherAdapter extends RecyclerView.Adapter<CurrentWeatherAdapter.WeatherListViewHolder> {

    private final List<WeatherCondition> weatherConditionList = new ArrayList<>();
    public OnWeatherClickedListener onWeatherClickedListener;

    public interface OnWeatherClickedListener {
        void onWeatherClicked(WeatherCondition weatherCondition);
    }

    public  void setWeather(List<WeatherCondition> weatherCondition) {
        this.weatherConditionList.clear();
        this.weatherConditionList.addAll(weatherCondition);
        notifyDataSetChanged();
    }

     public CurrentWeatherAdapter(OnWeatherClickedListener onWeatherClickedListener) {
        this.onWeatherClickedListener = onWeatherClickedListener;
    }

    @Override
    public WeatherListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_view, parent, false);
        return new WeatherListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherListViewHolder holder, int position) {
        holder.bind(weatherConditionList.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherConditionList.size();
    }

    class WeatherListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        WeatherCondition weatherCondition;

        CardView tvCardView;
        final TextView tvId;
        final TextView tvDay;
        final TextView tvHour;
        final TextView tvWeatherConditions;

        WeatherListViewHolder(View itemView) {
            super(itemView);

            tvCardView = itemView.findViewById(R.id.card_view);
            tvId = itemView.findViewById(R.id.tv_id);
            tvDay = itemView.findViewById(R.id.tv_day);
            tvHour = itemView.findViewById(R.id.tv_hour);
            tvWeatherConditions = itemView.findViewById(R.id.tv_weather_conditions);

            itemView.setOnClickListener(this);
        }

        void bind(WeatherCondition weatherCondition) {
            this.weatherCondition = weatherCondition;

            tvId.setText(String .valueOf(weatherCondition.getCity()));
            tvDay.setText(String .valueOf(weatherCondition.getTemp()));
            tvHour.setText(String .valueOf(weatherCondition.getDate()));
            tvWeatherConditions.setText(String .valueOf("BAD"));
        }



        @Override
        public void onClick(View v) {
            if (onWeatherClickedListener != null) {
                onWeatherClickedListener.onWeatherClicked(weatherCondition);
            }
        }
    }
}