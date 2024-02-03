package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class GoogleSheetsServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void readDataFromPublicSheet() {
        // モックのデータを設定
        String mockSheetsData = "T\nT\nA,16\nB,23\nC,1\nD,43\nE,2";
        when(restTemplate.getForObject("https://docs.google.com/spreadsheets/d/{シートI}", String.class))
                .thenReturn(mockSheetsData);
        // テスト対象のサービスを作成
        GoogleSheetsService googleSheetsService = new GoogleSheetsService(restTemplate);

        // テスト実行
        List<Map<String, String>> result = googleSheetsService.readDataFromPublicSheet();

        // 結果の検証（例：データが正しく処理されているか）
        // ここでは、データが正しく追加されていることを検証するサンプルです。
        assert result.size() == 5;
        assert result.get(0).get("A").equals("16");
        assert result.get(1).get("B").equals("23");
        // 他のデータも同様に検証

        // 追加の検証やアサーションを行うことができます。
    }

}