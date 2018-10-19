package com.android.zty.pixels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.fyb.cyhs.R;

import java.util.ArrayList;

public class StartActivity extends Activity {
    public static StartActivity mActivity = null;
    public static String msg = "OK";
    public static String code = "false";
     public ArrayList<Images> imagesArrayList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_start, null);
        setContentView(rootView);

        mActivity = this;
        //初始化渐变动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        //设置动画监听器
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            //动画结束后跳转到下一个Activity(TestActivity)
            @Override
            public void onAnimationEnd(Animation animation) {
//                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(StartActivity.this, TestActivity.class);
                if (msg.equals("网络出错"))
                    i.putExtra("net", "NoNET");
                else i.putExtra("net", "NET");
                startActivity(i);
                finish();
            }
        });
        //开始播放动画
        rootView.startAnimation(animation);
        Thread imaThread = new Thread(new ImaThread());
        imaThread.start();

        checkCode();
        try {
            Thread.sleep(200);
            System.out.println("______________________________________OK");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Images> imagesArrayList = ImageParse.imageParse(msg);

//        for (int i=0;i<imagesArrayList.size();i++){
//            System.out.println("______________________________________1"+imagesArrayList.get(i).getURL());
//        }
//        Toast.makeText(getApplicationContext(),imagesArrayList.size(),Toast.LENGTH_SHORT).show();
        StaticData.setLists(imagesArrayList);
//        for (int i=0;i<StaticData.getLists().size();i++){
//            System.out.println("______________________________________2"+StaticData.getLists().get(i).getURL());
//        }

    }

    static class ImaThread implements Runnable {
        public void run() {
            msg = HttpUtils.getServer(StaticData.getImgServer());
            System.out.println("______________________________________OK3" + msg);
        }
    }

    class CheckThread implements Runnable {
        public void run() {
            if (StaticData.getId() == null || StaticData.getId().isEmpty()) code = "false";
            else {
                code = HttpUtils.getServer(StaticData.getUserServer() + StaticData.getId() + "&pwd=" + StaticData.getPwd());
            }
            System.out.println("______________________________________OK3" + msg);
        }
    }

    public void checkCode() {
        Thread checkThread = new Thread(new CheckThread());
        checkThread.start();
        if (code.equals("false")) {
            StaticData.setCode("false");
//            Toast.makeText(getApplicationContext(), code, Toast.LENGTH_SHORT).show();

        } else {
            StaticData.setCode("true");
            Toast.makeText(getApplicationContext(), "o(*￣▽￣*)ブ登陆成功", Toast.LENGTH_SHORT).show();
        }
    }
}
