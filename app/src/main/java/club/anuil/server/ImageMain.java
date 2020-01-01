package club.anuil.server;

import club.anuil.MainActivity;
import club.anuil.util.JsonToRes;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;


/**
 * The quieter you become , the more you are able to hear
 *
 * @author： AnuilLne
 * @date： 2019/12/27 19:30
 * @description： 图像识别的主要类
 */
public class ImageMain {
    /**
     * @author： AnuilLne
     * @date：   2020/1/1 16:23
     * imagePath 图片的完整路径
     * imageFlag 图片的处理方式
     * baike_num 百科的数量
     * res 识别结果
     * resFlag 是否有处理结果标志
     * jsonObject 调用百度Api识别的返回结果
     */
    private String imagePath;
    private int imageFlag;
    private Integer baike_num;
    public static String res;
    public static Boolean resFlag;
    private JSONObject jsonObject;


    public ImageMain(String imagePath, int imageFlag, Integer baike_num){
        ImageMain.res="";
        this.imagePath=imagePath;
        this.imageFlag=imageFlag;
        this.baike_num=baike_num;
        this.jsonObject=new JSONObject();
        runner();
    }


    /**
     * @author： AnuilLne
     * @date：   2020/1/1 16:26
     * @param：
     * @return： void
     * @description：  创建子线程 用于网络请求 main线程无法请求网络
     */
    private void runner(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    getInfoRunner();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        //加入到线程池
        MainActivity.threadPoolExecutor.execute(runnable);
    }


    /**
     * @author： AnuilLne
     * @date：   2020/1/1 16:28
     * @param：
     * @return： void
     * @description：  根据图像的处理标志决定识别图像的方式
     */
    private void getInfoRunner()throws JSONException{

        HashMap<String, String> options = new HashMap<>();
        options.put("baike_num",baike_num.toString());
        BaiduImgClient client=new BaiduImgClient();

        switch (imageFlag) {
            case 1: {
                //通用识别
                this.jsonObject=client.advancedGeneral(imagePath,options);
                res=JsonToRes.generalImageRes(this.jsonObject);
                ImageMain.resFlag=true;
                break;
            }
            case 2: {
                //植物识别
                this.jsonObject=client.plantDetect(imagePath,options);
                System.out.println(this.jsonObject.toString());
                res=JsonToRes.animalAndPlantImageRes(this.jsonObject);
                ImageMain.resFlag=true;
                break;
            }
            case 3: {
                //动物识别
                this.jsonObject=client.animalDetect(imagePath,options);
                System.out.println(this.jsonObject.toString());
                res=JsonToRes.animalAndPlantImageRes(this.jsonObject);
                ImageMain.resFlag=true;
                break;
            }
            default: {
                res="出错啦！";
            }
        }
    }

}
