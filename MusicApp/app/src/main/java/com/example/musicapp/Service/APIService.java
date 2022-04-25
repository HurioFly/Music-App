package com.example.musicapp.Service;

public class APIService {
    private static final String base_url = "https://musicapphuriofly.000webhostapp.com/Server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
