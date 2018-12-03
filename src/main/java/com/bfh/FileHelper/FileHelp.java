package com.bfh.FileHelper;

import com.bfh.Model.Medicine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileHelp {

    //进行文件内容的追加
    public static void doWrite(String path, List<Medicine> medicines){

        System.out.println("进行一次写操作！");

        if(medicines==null)
            return;
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f=new File(path);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        for(int i = 0;i<medicines.size();i++){
            for(String key : medicines.get(i).getAttr()){
                pw.println(key+" #@# "+medicines.get(i).getOneAttr(key));
                pw.flush();
            }
            pw.println();
            pw.flush();
        }
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
