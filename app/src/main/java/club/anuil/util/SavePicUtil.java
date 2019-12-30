package club.anuil.util;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The quieter you become , the more you are able to hear
 *
 * @author： AnuilLne
 * @date： 2019/12/27 16:26
 * @description：压缩图片
 */
public class SavePicUtil {
    public static Boolean savePic(String newPath,String fileName, Bitmap bitmap) throws Exception{

        File file=new File(newPath);
        File image=new File(newPath+fileName);


        if (!file.exists()) {
            file.mkdirs();
            image.createNewFile();
        }

        try {
            FileOutputStream out = new FileOutputStream(newPath + fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
