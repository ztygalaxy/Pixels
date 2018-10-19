package com.android.zty.pixels;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fyb.cyhs.R;
import com.google.android.apps.markers.MarkersActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton btn_left_menu, btn_left_menu_upload, mail_btn_left_menu, btn_left_menu_regist, btn_left_menu_login, iv_upload;
    private View kankan, upload, ll_upload, login, send, regist;
    private ImageView iv_logo, iv_pic_up;
    private Button btn_login, btn_send, btn_regist;
    private TextView tv_id, tv_regis_inlogin;
    private DrawerLayout drawer;
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private Uri uri;
    private String s_up_title,s_up_content,responseMsg = "再来一次", id, pwd, success = "false", version = StaticData.getVersion(), id_regist, pwd_regist,srcPath,result="error",result1="error";
    private EditText edt_up_title, edt_up_content, edt_id, edt_pwd, edt_mail_title, edt_mail_content, edt_id_inregist, edt_pwd_inregist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        //加载侧滑菜单
        setContentView(R.layout.activity_nav);

//        Log.d("TAG", "TestActivity");
        //接受上一个页面传过来的参数
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            String NET = intent.getStringExtra("net");
            if (NET.equals("NoNET")) {
                showDia("网络错误", "请检查网络连接后重试", 1);
            }
        }
        StartActivity.mActivity.finish();


        System.out.println("_________" + StaticData.getCode());


        iv_logo = findViewById(R.id.login_logo);
        btn_left_menu = findViewById(R.id.btn_left_menu);
        btn_left_menu_upload = findViewById(R.id.btn_left_menu_upload);
        kankan = findViewById(R.id.kankan);
        upload = findViewById(R.id.upload);
        login = findViewById(R.id.login);
        send = findViewById(R.id.send);
        regist = findViewById(R.id.regist);

        ll_upload = findViewById(R.id.ll_upload);

        edt_id = findViewById(R.id.edt_id);
        edt_pwd = findViewById(R.id.edt_pwd);

        tv_id = findViewById(R.id.tvvvv_id);

        btn_login = findViewById(R.id.btn_logo);
        drawer = findViewById(R.id.drawer_layout);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //侧滑菜单设置点击监听时间
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final View drawview = navigationView.inflateHeaderView(R.layout.nav_header_main);
        tv_id = drawview.findViewById(R.id.tvvvv_id);
        drawview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticData.getCode().equals("true")) {
                    drawer.closeDrawer(GravityCompat.START);
                    showDia("提示", "<(￣︶￣)↗您已解锁所有功能", 1);
                } else {
                    //VISI可见  GONE不占位置消失
                    login.setVisibility(View.VISIBLE);
                    kankan.setVisibility(View.GONE);
                    upload.setVisibility(View.GONE);
                    send.setVisibility(View.GONE);
                    //对登陆页面元素查找并设置监听事件

                    //注册按钮
                    tv_regis_inlogin = findViewById(R.id.tv_register_inlogin);
                    //点击打开侧滑菜单
                    btn_left_menu_login = findViewById(R.id.btn_left_menu_login);
                    btn_left_menu_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //打开侧滑菜单
                            drawer.openDrawer(GravityCompat.START);
                        }
                    });
                    tv_regis_inlogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //加载注册页面
                            regist.setVisibility(View.VISIBLE);
                            login.setVisibility(View.GONE);
                        }
                    });
                    drawer.closeDrawer(GravityCompat.START);

                    //注册页面的表单，id password
                    edt_id_inregist = findViewById(R.id.edt_id_inregist);
                    edt_pwd_inregist = findViewById(R.id.edt_pwd_inregist);
                    btn_left_menu_regist = findViewById(R.id.btn_left_menu_regist);
                    btn_left_menu_regist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            drawer.openDrawer(GravityCompat.START);
                        }
                    });
                    //注册按钮及监听事件
                    btn_regist = findViewById(R.id.btn_regist);
                    btn_regist.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //从编辑框内取出id password
                            id_regist = edt_id_inregist.getText().toString();
                            pwd_regist = edt_pwd_inregist.getText().toString();
                            Toast.makeText(getApplicationContext(),id_regist+pwd_regist,Toast.LENGTH_SHORT).show();
                            //检查数据是否合法
                            if (id_regist.isEmpty() || id_regist.equals("") || pwd_regist.isEmpty() || pwd_regist.equals("")) {
                                Toast.makeText(getApplicationContext(), "(●'◡'●)是不是有什么东西还没写", Toast.LENGTH_SHORT).show();
                            } else {
                                //新开线程访问相应的注册Servlet
                                Thread registThread = new Thread(new RegistThread());
                                registThread.start();
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (success.equals("true")) {
                                    Toast.makeText(getApplicationContext(), "ヾ(≧▽≦*)o注册成功", Toast.LENGTH_SHORT).show();
                                    regist.setVisibility(View.GONE);
                                    kankan.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(getApplicationContext(), ",,ԾㅂԾ,,服务器开小差了", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            }
        });

        btn_left_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
                if (StaticData.getCode().equals("true")) {
                    tv_id.setText(StaticData.getId());
                }
            }
        });
        btn_left_menu_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        ll_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "上传照片", Toast.LENGTH_SHORT).show();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查数据合法性
                if (Check() == "OK") {
                    Thread loginThread = new Thread(new LoginThread());
                    loginThread.start();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (responseMsg.equals("true")) {
                        responseMsg = "φ(゜▽゜*)♪登陆成功";
                        StaticData.setCode("true");
                        StaticData.setId(id);
                        StaticData.setPwd(pwd);

                        //打开瀑布流
                        kankan.setVisibility(View.VISIBLE);
                        upload.setVisibility(View.GONE);
                        login.setVisibility(View.GONE);
                        send.setVisibility(View.GONE);
                        regist.setVisibility(View.GONE);
                        //设置页面的登录名
                        tv_id.setText(StaticData.getId());
                    } else {
                        responseMsg = "￣へ￣登录失败";
                        StaticData.setCode("false");
                    }
                    //显示操作信息
                    Toast.makeText(getApplicationContext(), responseMsg, Toast.LENGTH_SHORT).show();
                } else {
                    //数据不合法提示信息
                    Toast.makeText(getApplicationContext(), Check(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //登录线程
    class LoginThread implements Runnable {
        public void run() {
            responseMsg = HttpUtils.getServer(StaticData.getUserServer()+"?name="+id+"&pwd="+pwd);
        }
    }

    //版本线程
    class VersionThread implements Runnable {
        public void run() {
            version = HttpUtils.getServer(StaticData.getVerServer());
        }
    }

    //注册线程
    class RegistThread implements Runnable {
        public void run() {
            success = HttpUtils.getServer(StaticData.getRegistServer() + "?name=" + id_regist + "&pwd=" + pwd_regist);
        }
    }

    //更新列表线程
    static class ImaThread implements Runnable {
        public void run() {
            ArrayList<Images> imageArrayList=ImageParse.imageParse(HttpUtils.getServer(StaticData.getImgServer()));
            if (imageArrayList.size()>StaticData.getLists().size())
                StaticData.setLists(imageArrayList);
        }
    }

    //上传线程
    class UpThread implements Runnable {
        public void run() {
//            File file = new File(getRealFilePath(getApplicationContext(), uri)); //这里的path就是那个地址的全局变量

            File file = new File(srcPath);

            result = UploadUtil.uploadFile(file, StaticData.getUpServer());
            if (result.equals("error")) result="error";
            else {
                result1=HttpUtils.getServer(StaticData.getImgNewServer() + "?name=" + s_up_title + "&author=" + s_up_content);
                if (result1.equals("error")) result="error";
            }
        }
    }

    //检查登陆页面的数据合法性
    public String Check() {
        id = edt_id.getText().toString();
        pwd = edt_pwd.getText().toString();
        if (id.isEmpty() || id == "") return "(T_T)账号不可为空";
        else if (pwd.isEmpty() || pwd == "") return "(T_T)密码不可为空";
        else return "OK";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //侧滑菜单点击事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_kankan) {
            Thread imgThread=new Thread(new ImaThread());
            imgThread.start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kankan.setVisibility(View.VISIBLE);
            upload.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            send.setVisibility(View.GONE);
            regist.setVisibility(View.GONE);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_draw) {
            //跳转至绘画activity
            drawer.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(TestActivity.this, MarkersActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "(‾◡◝)点击左上角打开选项", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gall) {
            if (StaticData.getCode().equals("true")) {
                kankan.setVisibility(View.GONE);
                login.setVisibility(View.GONE);
                send.setVisibility(View.GONE);
                regist.setVisibility(View.GONE);
                upload.setVisibility(View.VISIBLE);
                drawer.closeDrawer(GravityCompat.START);

                iv_pic_up = findViewById(R.id.iv_pic_up);
                iv_upload = findViewById(R.id.iv_upload);
                edt_up_title = findViewById(R.id.edt_up_title);
                edt_up_content = findViewById(R.id.edt_up_content);
                //选择相册里的图片显示在当前页面
                iv_pic_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setImage();
                    }
                });
                //上传
                iv_upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s_up_title = edt_up_title.getText().toString();
                        s_up_content = edt_up_content.getText().toString();
                        if (s_up_title.isEmpty() || s_up_title.equals("") || s_up_content.isEmpty() || s_up_content.equals("")) {
                            Toast.makeText(getApplicationContext(), "(●'◡'●)是不是有什么东西还没写", Toast.LENGTH_SHORT).show();
                        } else {
                            Thread upThread = new Thread(new UpThread());
                            upThread.start();
                            try {
                                Thread.sleep(1000);
                                if (result.equals("error"))
                                    Toast.makeText(getApplicationContext(), "＞︿＜上传失败，请重试", Toast.LENGTH_SHORT).show();
                                else{
                                    Toast.makeText(getApplicationContext(), "上传成功，下次启动就可以看到啦", Toast.LENGTH_SHORT).show();
                                    kankan.setVisibility(View.VISIBLE);
                                    login.setVisibility(View.GONE);
                                    upload.setVisibility(View.GONE);
                                    regist.setVisibility(View.GONE);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

            } else {
                //没有相应权限，提示
                drawer.closeDrawer(GravityCompat.START);
                showDia("提示", "该功能仅对登陆用户开放，请登录后尝试！", 5);
            }
        } else if (id == R.id.nav_share) {
            //检查版本更新
            drawer.closeDrawer(GravityCompat.START);
            Thread verThread = new Thread(new VersionThread());
            verThread.start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (version.equals(StaticData.getVersion())) {
                showDia("版本信息", "o(*￣▽￣*)ブ\n当前版本已是最新版本！", 1);
            } else {
                showDia("版本信息", "o(*￣▽￣*)ブ\n发现新版本，请及时更新！", 3);
            }
        } else if (id == R.id.nav_send) {
            showDia("联系作者", "作者：张天宇\nE-Mail:zhangty1996@163.com", 4);
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }


    //从相册选择并显示图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(getApplicationContext(),"处理中",Toast.LENGTH_SHORT).show();
        // RESULT_OK 是系统自定义得一个常量
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult", "返回的resultCode出错");
            return;
        }
        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = getContentResolver();
        // 判断接收的Activity是不是选择图片的
        if (requestCode == IMAGE_CODE) {
            try {
                // 获得图片的地址Uri
                uri = data.getData();
                // 新建一个字符串数组用于存储图片地址数据。
                String[] proj = {MediaStore.Images.Media.DATA};
                // android系统提供的接口，用于根据uri获取数据
                Cursor cursor = managedQuery(uri, proj, null, null,
                        null);
                // 获得用户选择图片的索引值
                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                // 将游标移至开头 ，防止引起队列越界
                cursor.moveToFirst();
                //获取路径
                srcPath = cursor.getString(cursor.getColumnIndex("_data"));
                String[] aa=srcPath.split("/");
                String all=aa[aa.length-1];
                String name=all.substring(0,all.lastIndexOf("."));
                System.out.println("-------------------------------------"+srcPath+"   "+all+"  "+name);
                // 根据图片的URi生成bitmap
                Bitmap bm = MediaStore.Images.Media.getBitmap(resolver, uri);
                edt_up_title.setText(name);
                if (StaticData.getId()!=null)
                    edt_up_content.setText(StaticData.getId());
                iv_pic_up.setImageBitmap(bm);
                iv_upload.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setImage() {
        // 使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片的地址
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
//        getAlbum.setType(IMAGE_TYPE);

        getAlbum.setAction(Intent.ACTION_PICK);
        getAlbum.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    //弹窗函数，根据参数不同使用不同弹窗
    public void showDia(String title, String msg, int op) {
        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.logo);
        builder.setTitle(title);
        builder.setMessage(msg);
        if (op == 1) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
        } else if (op == 2) {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //                    android.os.Process.killProcess(android.os.Process.myPid());
                    android.os.Process.killProcess(android.os.Process.myPid());
//                    finish();
                }
            });
        } else if (op == 3) {
            builder.setPositiveButton("稍后", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //                    android.os.Process.killProcess(android.os.Process.myPid());
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                    finish();
                }
            });
            builder.setNegativeButton("下载并安装", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //                    android.os.Process.killProcess(android.os.Process.myPid());
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                    finish();
                    Intent intent = new Intent(TestActivity.this, DownAPKService.class);
                    intent.putExtra("apk_url", StaticData.getApkServer());
                    startService(intent);
                }
            });
        } else if (op == 4) {
            builder.setNegativeButton("反馈", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    kankan.setVisibility(View.GONE);
                    login.setVisibility(View.GONE);
                    upload.setVisibility(View.GONE);
                    regist.setVisibility(View.GONE);
                    send.setVisibility(View.VISIBLE);
                    drawer.closeDrawer(GravityCompat.START);

                    mail_btn_left_menu = findViewById(R.id.mail_btn_left_menu);
                    btn_send = findViewById(R.id.btn_send);
                    edt_mail_content = findViewById(R.id.edt_mail_content);
                    edt_mail_title = findViewById(R.id.edt_mail_title);

                    //反馈按钮
                    btn_send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String title = edt_mail_title.getText().toString();
                            String content = edt_mail_content.getText().toString();
                            if (title.isEmpty() || title.equals("") || content.isEmpty() || content.equals("")) {
                                Toast.makeText(getApplicationContext(), "(●'◡'●)是不是有什么东西还没写", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent data = new Intent(Intent.ACTION_SENDTO);
                                data.setData(Uri.parse("mailto:zhangty1996@163.com"));
                                data.putExtra(Intent.EXTRA_SUBJECT, edt_mail_title.getText().toString());
                                data.putExtra(Intent.EXTRA_TEXT, edt_mail_content.getText().toString());
                                startActivity(data);
                                Toast.makeText(getApplicationContext(), "(●'◡'●)将采用系统邮件为您发送", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    mail_btn_left_menu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            drawer.openDrawer(GravityCompat.START);
                        }
                    });
                }
            });
            builder.setPositiveButton("晓得了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        } else if (op == 5) {
            builder.setNegativeButton("现在登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    login.setVisibility(View.VISIBLE);
                    kankan.setVisibility(View.GONE);
                    upload.setVisibility(View.GONE);
                    send.setVisibility(View.GONE);

                    tv_regis_inlogin = findViewById(R.id.tv_register_inlogin);
                    tv_regis_inlogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            regist.setVisibility(View.VISIBLE);
                            login.setVisibility(View.GONE);
                        }
                    });
                    drawer.closeDrawer(GravityCompat.START);
                }
            });
            builder.setPositiveButton("再看看", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
        builder.show();
    }

    /**
     * Try to return the absolute file path from the given Uri
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
            System.out.println(uri.toString()+img_path);
        }
        File file = new File(img_path);
        return file;
    }
}
