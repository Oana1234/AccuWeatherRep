package com.example.ileana.accuweather.api;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.ileana.accuweather.models.WeatherCondition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by ILEANA on 4/12/2018.
 */

public class WeatherApi {

    private OkHttpClient okHttpClient;
    private static  final String API_KEY = "1675d207ed0449e474cbc00b4619d07f";
    private Gson gson;
    public final Handler mainHandler;

    public WeatherCondition getCurrentWeather(double latitude, double longitude) {
        WeatherCondition weatherCondition = new WeatherCondition();
        weatherCondition.setCity("London");
        weatherCondition.setDate(new Date());
        weatherCondition.setTemp(10);
        weatherCondition.setTempMin(20);
        weatherCondition.setTempMax(30);
        return weatherCondition;
    }

    public List<WeatherCondition> getWeatherForecast(double latitude, double longitude) {
        List<WeatherCondition> weatherConditions = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        WeatherCondition weatherCondition = new WeatherCondition();
        weatherCondition.setCity("London");
        weatherCondition.setDate(new Date());
        weatherCondition.setTemp(10);
        weatherCondition.setTempMin(20);
        weatherCondition.setTempMax(30);
        weatherConditions.add(weatherCondition);
        calendar.add(Calendar.DATE, 1);

        WeatherCondition weatherCondition1 = new WeatherCondition();
        weatherCondition1.setCity("London");
        weatherCondition1.setDate(new Date());
        weatherCondition1.setTemp(11);
        weatherCondition1.setTempMin(21);
        weatherCondition1.setTempMax(31);
        weatherConditions.add(weatherCondition1);
        calendar.add(Calendar.DATE, 1);

        WeatherCondition weatherCondition2 = new WeatherCondition();
        weatherCondition2.setCity("London");
        weatherCondition2.setDate(new Date());
        weatherCondition2.setTemp(11);
        weatherCondition2.setTempMin(21);
        weatherCondition2.setTempMax(31);
        weatherConditions.add(weatherCondition2);
        calendar.add(Calendar.DATE, 1);

        return weatherConditions;
    }

    public void searchLocation(String query, final ApiResponseCallback<List<SearchLocation>> callback) {

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegments("data/2.5/find")
                .addQueryParameter("q", query)
                .addQueryParameter("type", "like")
                .addQueryParameter("sort", "population")
                .addQueryParameter("cnt", String.valueOf(30))
                .addQueryParameter("appid", API_KEY)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()){
                    callback.onError();
                    return;
                }
                String json = response.body().string();

                SearchResponse searchResponse = gson.fromJson(json, SearchResponse.class);
                callback.onSuccess(searchResponse.getSearchLocations());
            }
        });
    }


    public WeatherApi(Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new ChuckInterceptor(context))
                .addInterceptor(httpLoggingInterceptor)
                .build();
        gson = new GsonBuilder().create();

        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void testNetwork(final ApiResponseCallback<String> callback) {

        Request request = new Request.Builder()
                .url("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                /*Log.e(" not working: ", "... nope");*/
                mainHandler.post(callback::onError);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code" + response);
                }
                String json = response.body().string();
                WeatherResponse weatherResponse = gson.fromJson(json, WeatherResponse.class);
                Log.d("API-SUCCESS", response.toString());
                callback.onSuccess(weatherResponse.getName());
            }
        });
    }

}

