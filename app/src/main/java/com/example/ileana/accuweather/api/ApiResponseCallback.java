package com.example.ileana.accuweather.api;

/**
 * Created by Oana-Maria on 19/04/2018.
 */

public interface ApiResponseCallback <T>{

        void onSuccess(T result);

        void onError();

}
