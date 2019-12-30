package club.anuil.server;

import com.baidu.aip.imageclassify.AipImageClassify;

/**
 * The quieter you become , the more you are able to hear
 *
 * @author： AnuilLne
 * @date： 2019/12/27 18:08
 * @description：
 */
public class BaiduImgClient extends AipImageClassify{

    //设置APPID/AK/SK

    public static final String APP_ID = "18115827";
    public static final String API_KEY = "UILGNHCX8UFjEbUYkZeVZRdK";
    public static final String SECRET_KEY = "iFVut6qopN9N0GoeiHfFkwvWH3gBoFq5";

    public BaiduImgClient(){
        super(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        this.setConnectionTimeoutInMillis(2000);
        this.setSocketTimeoutInMillis(60000);
    }
}
