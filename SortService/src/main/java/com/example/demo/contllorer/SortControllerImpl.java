package com.example.demo.contllorer;

import com.example.demo.service.SortServiceImpl;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SortControllerImpl {

    private final SortServiceImpl SortServiceImpl;

    @Autowired
    public SortControllerImpl(SortServiceImpl SortServiceImpl) {
        this.SortServiceImpl = SortServiceImpl;
    }

    @GetMapping("/index")
    public String processGoogleSheetsData() throws GeneralSecurityException, IOException {
        return "index";
    }

    @PostMapping("/submit")
    public String submitForm(Model model)
            throws GeneralSecurityException, IOException {

        // MyServiceを呼び出してGoogle Sheetsのデータを処理
        List<String[]> googleSheetsData = SortServiceImpl.processDataFromGoogleSheets();

        // モデルにデータを追加
        model.addAttribute("googleSheetsData", googleSheetsData);

        // 表示するHTMLのファイル名を返す
        return "index";
    }
}
