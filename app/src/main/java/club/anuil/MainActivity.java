package club.anuil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import club.anuil.activity.About;
import club.anuil.activity.TakePicture;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * The quieter you become , the more you are able to hear
 *
 * @Author :AnuilLne
 * @Date :2019/12/26 22:39
 * @Description: 主入口
 */
public class MainActivity extends Activity {

    /**
    * 1 通用图像识别
    * 2 植物识别
    * 3 动物识别
    **/

    public static int imageFlag;
    public static ThreadPoolExecutor threadPoolExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.imageFlag =0;
        //线程池
        MainActivity.threadPoolExecutor=new ThreadPoolExecutor(
                3, 5, 2,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public void buttonOne(View view) {
        //界面跳转
        MainActivity.imageFlag =1;
        Intent intent=new Intent(MainActivity.this, TakePicture.class);
        startActivity(intent);
    }

    public void buttonTwo(View view) {
        MainActivity.imageFlag =2;
        Intent intent=new Intent(MainActivity.this, TakePicture.class);
        startActivity(intent);
    }

    public void buttonThree(View view) {
        MainActivity.imageFlag =3;
        Intent intent=new Intent(MainActivity.this, TakePicture.class);
        startActivity(intent);
    }

    public void buttonFour(View view) {
        Intent intent=new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }
}
