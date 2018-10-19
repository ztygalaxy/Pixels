package com.android.zty.pixels;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ImageParse {
    public static ArrayList<Images> imageParse(String imaData) {
        ArrayList<Images> imagesArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(imaData);
            System.out.println("___________________" + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String sURL = jsonObject.getString("url");
                String sAuthor = jsonObject.getString("author");
                Images images = new Images(sURL, sAuthor);
                System.out.println("______________________+   " + images.getsURL());
                imagesArrayList.add(images);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < imagesArrayList.size(); i++) {
            System.out.println("______________________________________3" + imagesArrayList.get(i).getsURL());
        }
        return imagesArrayList;
    }
//    public static void main(String[] args){
//        System.out.println("Hello");
//    }
}
