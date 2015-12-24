package cn.com.nianyue.app.myapplication.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import cn.com.nianyue.app.myapplication.ImageListViewAdapter;
import cn.com.nianyue.app.myapplication.R;
import cn.com.nianyue.app.myapplication.listener.ItemClickListener;
import cn.com.nianyue.app.myapplication.listener.ItemLongClickListener;
import cn.com.nianyue.app.myapplication.model.Person;
import cn.com.nianyue.app.myapplication.request.Queue;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, ItemClickListener, ItemLongClickListener {

    private String className = getClass().getSimpleName();

    private ImageView imageView;
    private Button getString, getImage;
    private List<Person> persons = new ArrayList<>();
    private ImageListViewAdapter adapter;

    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getString = (Button) findViewById(R.id.getString);
        getImage = (Button) findViewById(R.id.getImage);
        imageView = (ImageView) findViewById(R.id.imageView);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        /**
        personList.add(new Person("Jack", "18 years old", R.mipmap.ic_launcher));
        personList.add(new Person("Tom", "22 years old", R.mipmap.ic_launcher));
        personList.add(new Person("LiLei", "24 years old", R.mipmap.ic_launcher));
        personList.add(new Person("HanMeiMei", "23 years old", R.mipmap.ic_launcher));
         */

        persons.add(new Person("Jack", "18 years old", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        persons.add(new Person("Jack", "18 years old", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        persons.add(new Person("Tom", "22 years old", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        persons.add(new Person("LiLei", "24 years old", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        persons.add(new Person("HanMeiMei", "23 years old", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));

        adapter = new ImageListViewAdapter(persons);
        rv.setAdapter(adapter);

        getString.setOnClickListener(this);
        getImage.setOnClickListener(this);

        adapter.setItemClickListener(this);
        adapter.setItemLongClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.getString:getString();break;
            case R.id.getImage:getImage(); break;
        }
    }

    private void getString () {
        getString.setEnabled(false);
        String url = "http://httpbin.org/get";
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(className, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(className, error.toString());
                getString.setEnabled(true);
            }
        });
        Queue.getInstance(this).addToRequestQueue(sr);
    }

    private void getImage() {
        getImage.setEnabled(false);
        final String url = "http://www.33lc.com/article/UploadPic/2012-9/201292811313286051.jpg";

        String fileName = md5(url);
        String path = "/Nianyue/cache/";
        File dirToList = new File(Environment.getExternalStorageDirectory(), path);
        File files[] = dirToList.listFiles();
        persons.clear();
        if(files != null ){
            for (File singleFile:files) {
                Log.i(className, singleFile.getAbsolutePath());
                persons.add(new Person(singleFile.getName(), singleFile.getAbsolutePath(), BitmapFactory.decodeFile(singleFile.getAbsolutePath())));
            }
        }

        if(persons.size() != 0 ) {
            adapter.notifyDataSetChanged();
        }

        /**
        for (int a = 0; a < files.length; a++) {
            Log.i(className, files[a].getAbsolutePath());
        }
         */
        if(null == fileName) fileName = "hello";
        File fileToSave = new File(Environment.getExternalStorageDirectory(), path + fileName);
        if(fileToSave.isFile()) {
            Log.i(className, "Load image from SDCard");
            Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + path + fileName);
            imageView.setImageBitmap(bm);
        } else {

            Log.i(className, "Load image from web");
            ImageRequest ir = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    String fileName = md5(url);
                    String path = "/Nianyue/cache/";
                    if(null == fileName) fileName = "hello";
                    File dirToSave = new File(Environment.getExternalStorageDirectory(), path);
                    if(!dirToSave.isDirectory()) {
                        dirToSave.mkdirs();
                    }
                    File fileToSave = new File(Environment.getExternalStorageDirectory(), path + fileName);
                    try {
                        OutputStream out = new FileOutputStream(fileToSave);
                        response.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(response);
                }
            }, getScreenWidth(), 300, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    getImage.setEnabled(true);
                }
            });
            Queue.getInstance(this).addToRequestQueue(ir);


        }

    }

    private String md5(String in) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(in.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getScreenWidth (){
        WindowManager wm = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    @Override
    public void onItemClick(View view, int position) {
        Person person = persons.get(position);
        if(person != null) Snackbar.make(view, person.name, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Person person = persons.get(position);
        if(person != null) Snackbar.make(view, person.age, Snackbar.LENGTH_LONG).show();
    }
}
