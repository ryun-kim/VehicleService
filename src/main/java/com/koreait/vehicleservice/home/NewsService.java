package com.koreait.vehicleservice.home;

import com.koreait.vehicleservice.home.model.NewsEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.vehicleservice.home.model.NewsEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    @Autowired private NewsMapper mapper;

    public List<NewsEntity> selNewsBoardList(NewsEntity newsEntity) {
        return mapper.selNewsBoardList(newsEntity);
    }

    // api 받아오기 --------------------------------- [start]
    public List<NewsEntity> getData(NewsEntity newsEntity) throws Exception {
        String clientId = "GCFxAi1DEYVeYhHAYAih"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "PYYRgrApw_"; //애플리케이션 클라이언트 시크릿값"

        String text = null;
        try {
            text = URLEncoder.encode("자동차", "UTF-8"); //검색어
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text;    // json 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders); //json데이터

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);

        JSONArray items = (JSONArray) jsonObject.get("items");
        /*System.out.println(items);*/

        //
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<NewsEntity> itemsList = objectMapper.convertValue(items, new TypeReference<List<NewsEntity>>() {});

        if (mapper.selNewsBoardList(newsEntity).size() == 0) {
            /*System.out.println(mapper.selNewsBoardList(newsEntity).size());*/
            for (NewsEntity item : itemsList) {
                /*System.out.println(item);*/
                mapper.insNewsBoard(item);
            }
        } else {
            /*System.out.println(mapper.selNewsBoardList(newsEntity).size());*/
            for (NewsEntity item : itemsList) {
                /*System.out.println(item);*/
                mapper.upNewsBoardList(item);
            }
        }


        List<NewsEntity> itemsList_final = mapper.selNewsBoardList(newsEntity);
        return itemsList_final;
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    // api 받아오기 --------------------------------- [end]
}
