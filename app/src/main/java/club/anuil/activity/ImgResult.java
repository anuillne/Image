package club.anuil.activity;

import android.graphics.Bitmap;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import club.anuil.MainActivity;
import club.anuil.R;
import club.anuil.server.ImageMain;

/**
 * 展示图片的识别结果
 * @author ANuiL
 */
public class ImgResult extends AppCompatActivity {
    /**
     * @author： AnuilLne
     * imgBitmap 需要展示的图片Bitmap
     * result 图片的识别结果
     */
    private ImageView imageView;
    private TextView textView;
    private static Bitmap imgBitmap;
    private static String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_result);
        imageView=findViewById(R.id.ImageView);
        textView=findViewById(R.id.ResultTextView);
        //设置TextView可滑动
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        showImg();
        textView.setText("识别中ing……");
        upDataUi();
    }

    public static void setImgBitmap(Bitmap imgBitmap) {
        ImgResult.imgBitmap = imgBitmap;
    }


    public static void setResult(String result) {
        ImgResult.result = result;
    }

    private void showImg(){
        imageView.setImageBitmap(imgBitmap);
    }

    private void showText(){
        textView.setText(result);
    }

    /**
     * @author： AnuilLne
     * @date：   2020/1/1 16:05
     * @param：
     * @return： void
     * @description： 创建子线程 从ImageMain类中获取识别结果 并更新UI
     */
    private void upDataUi(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                //创建子线程获取图片识别结果
                do {
                    setResult(ImageMain.res);
                }while (!ImageMain.resFlag);
                //主线程更新UI
                ImgResult.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showText();
                    }
                });
            }
        };
        //加入到线程池
        MainActivity.threadPoolExecutor.execute(runnable);
    }
}
