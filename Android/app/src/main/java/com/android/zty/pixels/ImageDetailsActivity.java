package com.android.zty.pixels;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fyb.cyhs.R;

import java.io.File;

/**
 * 查看详情大图的Activity界面
 */
public class ImageDetailsActivity extends Activity implements ViewPager.OnPageChangeListener {

    /**
     * 自定义的ImageView控制，可对图片进行多点触控缩放和拖动
     */
    private ZoomImageView zoomImageView;

    /**
     * 待展示的图片
     */
    private Bitmap bitmap = null;
    private Bitmap bitmap_a = null;

    /*
    * 标题设置
    * */
    private TextView mTextView;

    /*
    * 收藏点赞按钮
    * */
    private Button mButtonColl;
    private Button mButtonLike;
    private Button mButtonDown;
    private ImageButton mImageButton;

    /*
    * 图片管理类
    * */
    private ImageLoader imageLoader;
    /**
     * 用于管理图片的滑动
     */
    private ViewPager viewPager;

    /**
     * 显示当前图片的页数
     */
    private TextView pageText;

    private int downPosi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		//全屏
//		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//				WindowManager.LayoutParams. FLAG_FULLSCREEN);
//		setContentView(R.layout.image_details);
//		mTextView= (TextView) findViewById(R.id.details_item);
//		zoomImageView = (ZoomImageView) findViewById(R.id.zoom_image_view);

//
//
        // 取出图片路径，并解析成Bitmap对象，然后在ZoomImageView中显示
//		String imagePath = getIntent().getStringExtra("image_path");
//		bitmap = BitmapFactory.decodeFile(imagePath);
//
//
//		blurBitmap(getApplicationContext(),bitmap);
//		mTextView.setText("这里是标题\n"+imagePath);
//		zoomImageView.setImageBitmap(bitmap);
//
//		init();

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        setContentView(R.layout.image_details);
        int imagePosition = getIntent().getIntExtra("image_position", 10);
        pageText = findViewById(R.id.page_text);
        viewPager = findViewById(R.id.view_pager);
        mButtonColl = findViewById(R.id.details_coll);
        mButtonLike = findViewById(R.id.details_like);
        mButtonDown = findViewById(R.id.details_down);
        mImageButton = findViewById(R.id.btn_detail_back);
        mTextView = findViewById(R.id.details_item);

        init();

        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(imagePosition);
        viewPager.setOnPageChangeListener(this);
        // 设定当前的页数和总页数
        pageText.setText((imagePosition + 1) + "/" + StaticData.getLists().size());
        mTextView.setText("@ " + StaticData.getLists().get(imagePosition).getsAuthor());
        downPosi = imagePosition;
    }

    /**
     * 获取图片的本地存储路径。
     *
     * @param imageUrl 图片的URL地址。
     * @return 图片的本地存储路径。
     */
    private String getImagePath(String imageUrl) {
        int lastSlashIndex = imageUrl.lastIndexOf("/");
        String imageName = imageUrl.substring(lastSlashIndex + 1);
        String imageDir = Environment.getExternalStorageDirectory().getPath()
                + "/PhotoWallFalls/";
        File file = new File(imageDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String imagePath = imageDir + imageName;
        return imagePath;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int currentPage) {
        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        mTextView.setText("@ " + StaticData.getLists().get(currentPage).getsAuthor());
        pageText.setText((currentPage + 1) + "/" + StaticData.getLists().size());
        downPosi = currentPage;
    }

    class ViewPagerAdapter extends PagerAdapter {
        //		String imagePath;
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imagePath = getImagePath(StaticData.getLists().get(position).getsURL());
            System.out.println("_=" + imagePath + " " + position);
            bitmap = BitmapFactory.decodeFile(imagePath);

            View view = LayoutInflater.from(ImageDetailsActivity.this).inflate(
                    R.layout.zoom_image_layout, null);
            ZoomImageView zoomImageView = view
                    .findViewById(R.id.zoom_image_view);
            zoomImageView.setImageBitmap(bitmap);
            zoomImageView.setBackgroundDrawable(new BitmapDrawable(blurBitmap(getApplicationContext(), bitmap)));


            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return StaticData.getLists().size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 记得将Bitmap对象回收掉
        if (bitmap != null) {
            bitmap.recycle();
        }
        if (bitmap_a != null) {
            bitmap_a.recycle();
        }
    }

    private void init() {
        mButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonLike.setBackgroundResource(R.mipmap.like);
                Toast.makeText(getApplicationContext(), "o(*￣▽￣*)ブ喜欢~", Toast.LENGTH_SHORT).show();
            }
        });
        mButtonColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButtonColl.setBackgroundResource(R.mipmap.like);
                Toast.makeText(getApplicationContext(), "φ(゜▽゜*)♪收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
        mButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imagePath = getImagePath(StaticData.getLists().get(downPosi).getsURL());
                System.out.println("_=" + imagePath + " " + downPosi);
                Bitmap down_bitmap = BitmapFactory.decodeFile(imagePath);
                mButtonDown.setBackgroundResource(R.mipmap.down);
                ImageDownload.SavaImage(down_bitmap, Environment.getExternalStorageDirectory().getPath() + "/Cyhs");
                Toast.makeText(getApplicationContext(), "φ(゜▽゜*)♪下载成功", Toast.LENGTH_SHORT).show();
            }
        });
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static Bitmap blurBitmap(Context context, Bitmap bitmap) {
        //用需要创建高斯模糊bitmap创建一个空的bitmap
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // 初始化Renderscript，该类提供了RenderScript context，创建其他RS类之前必须先创建这个类，其控制RenderScript的初始化，资源管理及释放
        RenderScript rs = RenderScript.create(context);
        // 创建高斯模糊对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // 创建Allocations，此类是将数据传递给RenderScript内核的主要方 法，并制定一个后备类型存储给定类型
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);
        //设定模糊度(注：Radius最大只能设置25.f)
        blurScript.setRadius(23.f);
        // Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        // Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);
        // recycle the original bitmap
        // bitmap.recycle();
        // After finishing everything, we destroy the Renderscript.
        rs.destroy();
        return outBitmap;
    }
}