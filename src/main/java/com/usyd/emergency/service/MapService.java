package com.usyd.emergency.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: JunyuLiang
 * @Date: 2023/10/23 - 10 - 23 -18:41
 */
@Service
public class MapService {

    public Map<String, String> getLongitudeAndLatitude(String str) throws  Exception{
        Map<String, String> map = new HashMap<>();
        String address = str;
        String url = "https://nominatim.openstreetmap.org/search?q=" +
                java.net.URLEncoder.encode(address, "UTF-8") +
                "&format=json";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String responseContent = response.toString();

        JSONArray jsonArray = JSON.parseArray(responseContent);
        if (jsonArray.size() > 0) {
            JSONObject firstResult = jsonArray.getJSONObject(0);
            String lat = firstResult.getString("lat");
            String lon = firstResult.getString("lon");
            map.put("Latitude", lat);
            map.put("Longitude", lon);
        } else {
            System.out.println("No results found");
        }
        return map;
    }

}
