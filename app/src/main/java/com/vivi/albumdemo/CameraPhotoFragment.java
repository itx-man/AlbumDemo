package com.vivi.albumdemo;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 11041730 on 2016/10/28.
 */

public class CameraPhotoFragment extends Fragment {

    public static final int CACHE_SIZE = 100;
    private MediaObject[] mItems = null;
    private Activity mActivity;

    public CameraPhotoFragment(){
        mActivity = this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab01, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new MediaObject[100];
    }

    @Override
    public void onResume() {
        new ProviderLoader().start();
        super.onResume();
    }

    private class ProviderLoader extends Thread{
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        public ProviderLoader(Context context){
        }

        @Override
        public void run(){
            Cursor cursor = mActivity.getContentResolver().query(uri, projection);
            while(cursor != null && cursor.moveToNext()){
                String path = cursor.getString(0);

                MediaObject item = new Image();
                item.setPath(path);

                mItems.add
            }
        }
    }
}
