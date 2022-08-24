package com.education.exporter;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class DownloadImage {

    public static String getImage(String path, String image, Long id) throws  Exception {
        String srcImg="/home/karim/Bureau/education/src/main/resources/static/"+path;

        File file=new File(srcImg+"/"+image);
        if(id!=null){
            file=new File(srcImg+id+"/"+image);
        }
        String extension= FilenameUtils.getExtension(file.getName());
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes=new byte[(int)file.length()];
        fileInputStream.read(bytes);
        String encodeBase64= Base64.getEncoder().encodeToString(bytes);
        String img="data:image/"+extension+";base64,"+encodeBase64;
        fileInputStream.close();
        return  img;




    }
}








