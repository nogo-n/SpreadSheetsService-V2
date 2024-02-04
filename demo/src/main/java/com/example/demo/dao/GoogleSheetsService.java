package com.example.demo.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class GoogleSheetsService {

    //スプレッドシートのURLを記載
    private String publicSheetsUrl = "https://docs.google.com/spreadsheets/d/126R5vAWmuNA8tiTQ4KN-K3IfuwD5w5OSOAvZUaUZ5wE";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    //RestTemplateをコンストラクタインジェクションをしている
    public GoogleSheetsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map<String, String>> readDataFromPublicSheet() {
        // Google Sheetsの公開URLを使ってデータを取得
        String sheetsData = restTemplate.getForObject(publicSheetsUrl, String.class);
        System.out.println("sheetsData" + sheetsData);

        // CSV形式のデータを行ごとに分割
        String[] rows = sheetsData.split("\n");

        // 特定の範囲のデータ行を取得（ここでは第2行から第7行）
        //newRowsには[A,16, B,23, ...]のように値が入っている("A,16"が1つの要素である)
        String[] newRows = Arrays.copyOfRange(rows, 2, 7);
        // newRows を出力
        System.out.println("newRows: " + Arrays.toString(newRows));

        // データ行からMapのリストを作成(keyがA行、valueがB行)
        List<Map<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < newRows.length; i++) {
            String[] values = newRows[i].split(",");
            Map<String, String> dataMap = new HashMap<>();
            if (values.length > 1) {
                dataMap.put(values[0], values[1]);
            }
            dataList.add(dataMap);
        }

        dataList.forEach(System.out::println);

        return dataList;
    }

}
