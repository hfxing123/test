package com.example.service.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestCVS {

        public static void main(String[] args) throws IOException {
            File csv = new File("D:\\1.csv");  // CSV文件路径
            BufferedReader br = null;
            try {
                //br = new BufferedReader(new FileReader(csv));
                br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\\\1.csv"),"UTF8"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line = "";
            String everyLine = "";
            try {
                List<String> allString = new ArrayList<>();
                while ((line = br.readLine()) != null)  //读取到的内容给line变量
                {
                    everyLine = line;
                    System.out.println(everyLine);
                    allString.add(everyLine);
                }
                System.out.println("csv表格中所有行数：" + allString.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

}
