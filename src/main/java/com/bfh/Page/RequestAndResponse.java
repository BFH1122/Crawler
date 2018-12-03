package com.bfh.Page;


import com.bfh.FileHelper.FileHelp;
import com.bfh.Model.Medicine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class RequestAndResponse {


    public static String sendRequstAndGetResponse(String url, Map<String,String> param) {


        //创建对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        String resultConent  = "";

        try {
            URIBuilder builder = new URIBuilder(url);

//            添加请求的参数
            if(param!=null){
                for(String key : param.keySet()){
                    builder.addParameter(key,param.get(key));
                }
            }

            URI uri = builder.build();

//            创建ｇｅｔ请求
            HttpGet httpGet = new HttpGet(uri);


            httpGet.setConfig(RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build());

            httpGet.addHeader("Accept", "text/html");
            httpGet.addHeader("Accept-Charset", "utf-8");
            httpGet.addHeader("Accept-Encoding", "gzip");
            httpGet.addHeader("Accept-Language", "en-US,en");
            httpGet.addHeader("User-Agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
//            执行请求
            response = httpClient.execute(httpGet);

//            判断返回的状态是否是２００
            if(response.getStatusLine().getStatusCode()==200){
                resultConent = EntityUtils.toString(response.getEntity(),"UTF-8");

                System.out.println(url+":响应成功");

            }else{
                //请求失败的例子
                return "请求失败！";

            }

        } catch (URISyntaxException e) {
            return "请求失败1！";
        } catch (ClientProtocolException e) {
            return "请求失败2！";
        } catch (IOException e) {
            return "请求失败3！";
        }

        return resultConent;
    }
}
