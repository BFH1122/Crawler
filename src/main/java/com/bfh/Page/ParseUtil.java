package com.bfh.Page;


import com.bfh.Model.Medicine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParseUtil {



    //处理请求界面的函数，只能获得药品的名字，以及药品详细内容的链接
    //保存在HashMap中
    public static Set<String> parseStr(String content) throws IOException {
        Set<String> set = new HashSet<>();
        Document doc = Jsoup.parse(content);
        Elements nodes = doc.select("tbody").select("tr");
        for(int i = 0;i< nodes.size();i++) {
            Elements node = nodes.get(i).select("td");
//            for(int j = 0;j<node.size();j++){
            Elements href = node.get(0).select("a");
            String hrefStr = href.attr("href");
            String text = node.get(0).text();

            if (!set.contains(hrefStr)) {
                set.add(hrefStr);
            }
        }

        return set;
    }



    //根据网页信息提取某种药品的具体所有的信息


//    <tbody><tr><th><td>
//    <th> 名称　　<td> 属性值
    public static Medicine parseMedicine(String content){

        Medicine medicine = new Medicine();
        Document doc = Jsoup.parse(content);
        Elements nodes = doc.select("tbody").select("tr");//tr的集合
        for(int i =0;i<nodes.size();i++){
            Elements thnode = nodes.get(i).select("th");
            Elements tdnode = nodes.get(i).select("td");
            medicine.add(thnode.text(),tdnode.text());
        }
        return medicine;
    }



    //将字符串转化为流，便于解析
    private static InputStream getStringStream(String sInputString){
        if (sInputString != null && !sInputString.trim().equals("")){
            try{
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
}
