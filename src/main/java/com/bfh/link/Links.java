package com.bfh.link;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Links {

    private static Set visitedUrlSet = new HashSet<>();

    private static LinkedList<String> unVisitedUrl = new LinkedList();


    public static void addAllUnvisitedUrl(Set<String> set){
        for(String uri : set){
            unVisitedUrl.add(uri);
        }
    }

    //获得已经访问的ＵＲＬ的个数
    public static int getVisitedNUm(){
        return visitedUrlSet.size();
    }

    //添加已经访问的ＵＲＬ
    public static void addVisitedUrl(String url){
        visitedUrlSet.add(url);
    }

    //删除已经访问的ｕｒｌ
    public static void removevisitedUrl(String url){
        visitedUrlSet.remove(url);
    }

    //获得 待访问的 url 集合
    public static LinkedList getUnVisitedUrlQueue() {
        return unVisitedUrl;
    }

    // 添加到待访问的集合中  保证每个 URL 只被访问一次
    public static void addUnvisitedUrlQueue(String url) {
        if (url != null && !url.trim().equals("")  && !visitedUrlSet.contains(url)  && !unVisitedUrl.contains(url)){
            unVisitedUrl.add(url);
        }
    }

    //删除 待访问的url
    public static Object removeHeadOfUnVisitedUrlQueue() {
        return unVisitedUrl.removeFirst();
    }

    //判断未访问的 URL 队列中是否为空
    public static boolean unVisitedUrlQueueIsEmpty() {
        return unVisitedUrl.isEmpty();
    }
}
