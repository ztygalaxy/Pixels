package com.android.zty.pixels;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;


/**
 * @Title:HttpUtils.java
 * @Description:用于打开HTTP链接，访问指定URL
 */

public class HttpUtils {
    public static String getServer(String URL) {
        boolean loginValidate = false;
        String responseMsg = "网络出错";
        //servlet服务器地址是传入参数URL
        HttpPost request = new HttpPost(URL);

        try {
            HttpClient client = getHttpClient();
            //执行请求
            HttpResponse response = client.execute(request);
            //response是servlet给出的返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                loginValidate = true;
                responseMsg = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return responseMsg;
    }

    public static HttpClient getHttpClient() {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
        HttpClient client = new DefaultHttpClient(httpParams);
        return client;
    }
}
