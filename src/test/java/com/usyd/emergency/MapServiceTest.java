package com.usyd.emergency;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: JunyuLiang
 * @Date: 2023/10/23 - 10 - 23 -18:17
 */
public class MapServiceTest {
    @Test
    public void test1() throws  Exception{
        String address = "5 Sam Sing St, Waterloo NSW 2017";
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
        String responseContent = response.toString(); // 这里替换为你的JSON响应内容

        JSONArray jsonArray = JSON.parseArray(responseContent);
        if (jsonArray.size() > 0) {
            JSONObject firstResult = jsonArray.getJSONObject(0);
            String lat = firstResult.getString("lat");
            String lon = firstResult.getString("lon");
            System.out.println("Latitude: " + lat);
            System.out.println("Longitude: " + lon);
        } else {
            System.out.println("No results found");
        }
    }
}
