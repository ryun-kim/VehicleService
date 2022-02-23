package com.koreait.vehicleservice;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

@Component
public class MyFileUtils {

    public void makeFolders(String path){
        File folder = new File(path);
        if(!folder.exists()){ //해당 path위치에 폴더존재?
            folder.mkdirs(); //없다면 파일만들기
        }
    }


    //폴더삭제
    public void delFolderFiles(String path, boolean isDelFolder){
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File[] fileArr = file.listFiles();
            for(File f : fileArr){
                if(f.isDirectory()){//제귀처리
                    delFolderFiles(f.getPath(),true);
                }else{
                    f.delete();
                }
            }
        }

        if(isDelFolder){file.delete();}
    }

    public void delFile(String path){
        File f = new File(path);
        if(f.exists()){
            f.delete();
        }

    }

    //랜덤파일명 만들기
    public String getRandomFileNm(){
        return UUID.randomUUID().toString();
    }

    public String getRandomFileNm(String fileNm){
        return getRandomFileNm()+getExt(fileNm);
    }

    //확장자 구하기
    public String getExt(String fileNm){
        String[] ext = fileNm.split("[.]");
        return "."+ext[ext.length-1];
    }

    //파일저장 > 저장된 랜덤 파일명 리턴
    public String saveFile(String path, MultipartFile mf) {
        makeFolders(path);
        String randomFileNm = getRandomFileNm(mf.getOriginalFilename());

        File targetFile = new File(path, randomFileNm);
        try {
            mf.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return randomFileNm;
    }
}