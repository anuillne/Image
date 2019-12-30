package club.anuil.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

/**
 * The quieter you become , the more you are able to hear
 *
 * @Author :AnuilLne
 * @Date :2019/12/27 14:13
 * @Description: 权限
 */

public class PermissionsUtil {

    /*
     * @Author: AnuilLne
     * @Date: 2019/12/27 14:17
     * @param：activity 上下文
     * @param：permission 要检查的权限
     * @Return: int 结果标识
     * @Description: 检查是否有对应权限
     */

    public static int verifyPermissions(Activity activity,java.lang.String permission) {
        int perm = ActivityCompat.checkSelfPermission(activity,permission);
        if (perm == PackageManager.PERMISSION_GRANTED) {
            return 1;
        }else{
            return 0;
        }
    }
}
