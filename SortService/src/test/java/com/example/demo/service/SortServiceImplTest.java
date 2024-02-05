package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import com.example.demo.dao.GoogleSheetsServiceImpl;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SortServiceImplTest {

    @Mock
    private GoogleSheetsServiceImpl googleSheetsServiceImpl;

    @InjectMocks
    private SortServiceImpl sortService;

    @Test
    void processDataFromGoogleSheets() throws GeneralSecurityException, IOException {
        // モックのデータを設定
        List<Map<String, String>> mockMappedList = Arrays.asList(
                Map.of("A", "16"),
                Map.of("B", "23"),
                Map.of("C", "1"),
                Map.of("D", "43"),
                Map.of("E", "2")
        );

        // モックの動作を設定
        // モックの動作を設定
        when(googleSheetsServiceImpl.readDataFromPublicSheet("https://docs.google.com/spreadsheets/d/{シートID}", 2, 7)).thenReturn(mockMappedList);

        // テスト対象メソッドを呼び出し
        List<String[]> result = sortService.processDataFromGoogleSheets("https://docs.google.com/spreadsheets/d/{シートID}", 2, 7);

        // 期待値と結果を比較
        assertEquals(5, result.size());

        // その他の検証が必要であれば追加
    }

}