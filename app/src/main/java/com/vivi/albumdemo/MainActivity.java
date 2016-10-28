package com.vivi.albumdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mTabCameraPhoto;
    private LinearLayout mTabAllPhoto;

    private ImageButton mImgCameraPhoto;
    private ImageButton mImgAllPhoto;
    private TextView mCameraText;
    private TextView mAllPhotoText;
    private ImageView mImageView;

    private Fragment mTab01;
    private Fragment mTab02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        resetImgs();
        setSelect(0);
    }

    private Bitmap getDiskBitmap(String pathString)
    {
        Bitmap bitmap = null;
        try
        {
            File file = new File(pathString);
            if(file.exists())
            {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e)
        {
            // TODO: handle exception
        }

        return bitmap;
    }

    private void initEvent() {
        mTabCameraPhoto.setOnClickListener(this);
        mTabAllPhoto.setOnClickListener(this);
    }

    private void initView() {
        mTabCameraPhoto = (LinearLayout) findViewById(R.id.id_tab_camera_photo);
        mTabAllPhoto = (LinearLayout) findViewById(R.id.id_tab_allphoto);

        mImgCameraPhoto = (ImageButton) findViewById(R.id.id_tab_camera_photo_img);
        mImgAllPhoto = (ImageButton) findViewById(R.id.id_tab_allphoto_img);
        mAllPhotoText = (TextView) findViewById(R.id.allphoto_text);
        mImageView = (ImageView) findViewById(R.id.imageView);
    }

    private void setSelect(int i)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        //把图片设置为亮的
        //设置内容区域
        switch (i)
        {
            case 0:
                if (mTab01 == null)
                {
                    mTab01 = new CameraPhotoFragment();
                    transaction.add(R.id.id_content, mTab01);
                } else
                {
                    transaction.show(mTab01);
                }
                mImgCameraPhoto.setImageResource(R.drawable.bottom_camera_photo_focus);
                break;
            case 1:
                if (mTab02 == null)
                {
                    mTab02 = new AllPhotoFragment();
                    transaction.add(R.id.id_content,mTab02);
                } else
                {
                    transaction.show(mTab02);
                }
                mImgAllPhoto.setImageResource(R.drawable.bottom_allphoto_focus);
                break;
            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (mTab01 != null)
        {
            transaction.hide(mTab01);
        }
        if (mTab02 != null)
        {
            transaction.hide(mTab02);
        }
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId())
        {
            case R.id.id_tab_camera_photo:
                setSelect(0);
                break;
            case R.id.id_tab_allphoto:
                setSelect(1);
                break;
            default:
                break;
        }

    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs()
    {
        mImgCameraPhoto.setImageResource(R.drawable.bottom_camera_photo_normal);
        mImgAllPhoto.setImageResource(R.drawable.bottom_allphoto_normal);
    }
}
