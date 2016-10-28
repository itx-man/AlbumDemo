package com.vivi.albumdemo;

/**
 * Created by 11041730 on 2016/10/28.
 */


//单个多媒体对象
public class MediaItem extends MediaObject {

    protected  int _id;
    protected String filePath;


    public int getId(){
        return _id;
    }

    public String getFilePath(){
        return filePath;
    }
}
