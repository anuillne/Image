package club.anuil.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import club.anuil.MainActivity;
import club.anuil.R;
import club.anuil.server.ImageMain;
import club.anuil.util.PermissionsUtil;
import club.anuil.util.SavePicUtil;
import java.io.File;
import java.io.IOException;


/**
 * @author ANuiL
 * @description 取得图像的窗口 拍照/从相册中获得 图片传给ImageMain
 */
public class TakePicture extends AppCompatActivity {

    // 需要的权限数组 读/写/相机

    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        //跳转相机动态权限
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        //申请权限
        ActivityCompat.requestPermissions(TakePicture.this, PERMISSIONS_STORAGE, 4);

    }

    public void buttonCamera(View view){
        //检查是否已经获得相机的权限
        if(PermissionsUtil.verifyPermissions(TakePicture.this,PERMISSIONS_STORAGE[2]) == 0){
            ActivityCompat.requestPermissions(TakePicture.this, PERMISSIONS_STORAGE, 3);
        }else{
            //将处理结果设置为false
            ImageMain.resFlag=false;
            //已经有权限
            // 打开相机
            toCamera();
        }

    }

    public void buttonTakePicFrom(View view){
        //将处理结果设置为false
        ImageMain.resFlag=false;
        toPicture();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        File tempFile;
        //判断返回码不等于0
        if (requestCode != RESULT_CANCELED) {
            //读取返回码
            switch (requestCode) {
                //相册返回的数据（相册的返回码）
                case 100:
                    Uri uri01 = data.getData();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri01));
                        SavePicUtil.savePic(Environment.getExternalStorageDirectory()+"/Image/","fileImg.jpeg",bitmap);
                        Intent intent=new Intent(TakePicture.this,ImgResult.class);
                        ImgResult.setImgBitmap(bitmap);
                        //将图片的地址传给ImageMain 并设置百度百科的条数为 1 imageFlag为图片的处理方式
                        new ImageMain(
                            Environment.getExternalStorageDirectory()+"/Image/fileImg.jpeg",
                            MainActivity.imageFlag,
                            1);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                //相机返回的数据（相机的返回码）
                case 101:
                    try {
                        //相机取图片数据文件
                        tempFile = new File(Environment.getExternalStorageDirectory(), "/Image/fileImg.jpeg");
                        //图片文件
                        Uri uri02 = Uri.fromFile(tempFile);
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri02));
                        Intent intent=new Intent(TakePicture.this,ImgResult.class);
                        ImgResult.setImgBitmap(bitmap);
                        //将图片的地址传给ImageMain 并设置百度百科的条数为 1 imageFlag为图片的处理方式
                        new ImageMain(
                                Environment.getExternalStorageDirectory()+"/Image/fileImg.jpeg",
                                MainActivity.imageFlag,
                                1);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default: {
                }
            }

        }
    }

    //跳转相册

    private void toPicture() {
        //跳转到 ACTION_IMAGE_CAPTURE
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,100);
    }


    /**
     * @author： AnuilLne
     * @date：   2019/12/27 14:53
     * @param：
     * @return： void
     * @description： 打开相机
     */
    private void toCamera() {
        //跳转到 ACTION_IMAGE_CAPTURE
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话就进行存储
        //putExtra：取值，Uri.fromFile：传一个拍照所得到的文件，fileImg.jpg：文件名
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"/Image/fileImg.jpeg")));
        // 101: 相机的返回码参数（随便一个值就行，只要不冲突就好)
        startActivityForResult(intent,101);
    }

}
