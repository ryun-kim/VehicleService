package com.koreait.vehicleservice;

import java.io.File;

public class test {
    public static void main(String[] args) {
        String strDirPath = "D:\\upload\\images\\vehicle\\11\\sub";
        ListFile( strDirPath );
    }



    private static void ListFile( String strDirPath ) {
        File path = new File(strDirPath);
        File[] fList = path.listFiles();
        for (int i = 0; i < fList.length; i++) {
            if (fList[i].isFile()) {
                System.out.println(fList[i].getName());
            }
        }
    }
}
