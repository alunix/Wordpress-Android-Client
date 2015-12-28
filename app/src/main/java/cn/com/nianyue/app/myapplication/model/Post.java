package cn.com.nianyue.app.myapplication.model;

import android.graphics.Bitmap;

/**
 * Created by lu on 2015/12/24.
 */
public class Post {
    public String name, age;
    //public int photoId;
    public Bitmap photo;

    public Post(String name, String age, Bitmap photo) {
        this.name = name;
        this.age = age;
        //this.photoId = photoId;
        this.photo = photo;
    }
}
