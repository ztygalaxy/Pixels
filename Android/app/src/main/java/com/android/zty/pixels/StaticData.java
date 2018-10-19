package com.android.zty.pixels;

import java.util.ArrayList;


public class StaticData {
    public static ArrayList<Images> lists;
    public static String code = "false";
    public static String version = "1.0";
    public static String id;
    public static String pwd;

    public static String getImgNewServer() {
        return imgNewServer;
    }

    public static void setImgNewServer(String imgNewServer) {
        StaticData.imgNewServer = imgNewServer;
    }

    public static String imgNewServer="http://192.168.1.110/Cyhs/NewImaServlet";
    public static String imgServer="http://192.168.1.110/Cyhs/ImgServlet";
    public static String userServer="http://192.168.1.110/Cyhs/UserServlet";
    public static String verServer="http://192.168.1.110/Cyhs/VerServlet";
    public static String registServer="http://192.168.1.110/Cyhs/RegistServlet";
    public static String apkServer="http://192.168.1.110/Cyhs/appdebug.apk";
    public static String upServer="http://192.168.1.110/Cyhs/UploadShipServlet";

    public static String getUpServer() {
        return upServer;
    }

    public static void setUpServer(String upServer) {
        StaticData.upServer = upServer;
    }

    public static String getRegistServer() {
        return registServer;
    }

    public static void setRegistServer(String registServer) {
        StaticData.registServer = registServer;
    }

    public static String getApkServer() {
        return apkServer;
    }

    public static void setApkServer(String apkServer) {
        StaticData.apkServer = apkServer;
    }

    public static String getVerServer() {
        return verServer;
    }

    public static void setVerServer(String verServer) {
        StaticData.verServer = verServer;
    }




    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        StaticData.version = version;
    }

    public static String getImgServer() {
        return imgServer;
    }

    public static void setImgServer(String imgServer) {
        StaticData.imgServer = imgServer;
    }

    public static String getUserServer() {
        return userServer;
    }

    public static void setUserServer(String userServer) {
        StaticData.userServer = userServer;
    }

    public StaticData() {
    }

    public static ArrayList<Images> getLists() {
        return lists;
    }

    public static void setLists(ArrayList<Images> lists) {
        StaticData.lists = lists;
    }

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        StaticData.code = code;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        StaticData.id = id;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        StaticData.pwd = pwd;
    }
}
