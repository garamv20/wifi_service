package service;

import com.google.gson.*;
import dto.Wifi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class ApiService {
    static String wifiUrl = "http://openapi.seoul.go.kr:8088/414d467047676172363257774c6d6d/json/TbPublicWifiInfo/";
    // client 객체 생성
    private static OkHttpClient client;
    private static Request.Builder builder;
    public static int getApiInfoCnt() {

        client = new OkHttpClient.Builder().build();
        builder = new Request.Builder();
        int start = 1;
        int end = 1;
        int totalCnt = 0;

        String url = wifiUrl + start + "/" + end;
        Request request = new Request.Builder().url(url).get().build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                ResponseBody body = response.body();
                if (body != null) {
                    System.out.println("API연결 성공");
                    JsonElement jsonElement = JsonParser.parseString(body.string());

                    totalCnt = jsonElement.getAsJsonObject().get("TbPublicWifiInfo")
                                         .getAsJsonObject().get("list_total_count")
                                         .getAsInt();
                    System.out.println(totalCnt);
                    JsonArray jsonArray = jsonElement.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("row").getAsJsonArray();
                    System.out.println(jsonArray.toString());
                    System.out.println("jsonsize(): " +jsonArray.size());

                    // json 객체 줄바꿈 출력
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonOutput = gson.toJson(jsonArray);
                    System.out.println(jsonOutput);

//                    insertWifi(totalCnt);
                }
            }else {
                System.out.println("API 연결 실패");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return totalCnt;
    }

    public static int insertWifi(int totalCnt) {
//        client = new OkHttpClient.Builder().build();
        builder = new Request.Builder();
        int start = 0;
        int end = 0;
        int count = 0;

        try {
            for (int i = 0; i <= totalCnt / 1000; i++) {
                start = 1 + (1000 * i);
                end = (i + 1) * 1000;
                String url = wifiUrl + start + "/" + end;
                Request request = new Request.Builder().url(url).get().build();
                Response response = client.newCall(request).execute();

                if (response.isSuccessful()){
                    ResponseBody body = response.body();
                    if (body != null) {
                        JsonElement jsonElement = JsonParser.parseString(body.string());
                        JsonArray jsonArray = jsonElement.getAsJsonObject().get("TbPublicWifiInfo")
                                                         .getAsJsonObject().get("row").getAsJsonArray();
                        count += WifiService.wifiInsert(jsonArray);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return count;
    }
}
