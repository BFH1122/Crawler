package com.bfh;

import com.bfh.FileHelper.FileHelp;
import com.bfh.Model.Medicine;
import com.bfh.Page.ParseUtil;
import com.bfh.Page.RequestAndResponse;
import com.bfh.link.Links;

import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) throws InterruptedException {
        Map<String,String> map = new HashMap<>();
        boolean flag = true;
        int i = 1;

        int sum = 0;

        List<Medicine> list = new LinkedList<>();
        while(flag){
            //构造传递的参数
            map.put("p",i+"");
            map.put("pageSize",30+"");

            //第一次请求获取并进行处理
            String str= RequestAndResponse.sendRequstAndGetResponse("https://db.yaozh.com/pijian",map);

            //将本页的所有请求放到没有请丢的ＵＲＬ中
            try {
                Links.addAllUnvisitedUrl(ParseUtil.parseStr(str));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("获取的药品详细的数目："+Links.getUnVisitedUrlQueue().size());
            sum+=Links.getUnVisitedUrlQueue().size();

            //保存药品的信息

            while(!Links.getUnVisitedUrlQueue().isEmpty()){
                String strs=RequestAndResponse.sendRequstAndGetResponse("https://db.yaozh.com/"+Links.getUnVisitedUrlQueue().getFirst(),null);
                list.add(ParseUtil.parseMedicine(strs));
                Links.addVisitedUrl(Links.getUnVisitedUrlQueue().getFirst().toString());
                Links.getUnVisitedUrlQueue().removeFirst();
                Thread.sleep(50);
            }

            if(list.size()>1000){
                FileHelp.doWrite("/home/package/result.txt",list);
                list.clear();
                Thread.sleep(10000);
            }

            if(Links.getVisitedNUm()>160000)
                flag = false;
            i++;
            System.out.println("请求第"+i+"页");
        }
        FileHelp.doWrite("/home/package/result.txt",list);
        System.out.println("共记录药品详细数据："+sum+"条");
    }
}
