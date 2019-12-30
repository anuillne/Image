package club.anuil.server;

import club.anuil.MainActivity;
import club.anuil.util.JsonToRes;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * The quieter you become , the more you are able to hear
 *
 * @author： AnuilLne
 * @date： 2019/12/27 19:30
 * @description：
 */
public class ImageMain {
    private String imagePath;
    private int imageFlag;
    private Integer baike_num;
    public static String res;

    //是否有处理结果标志

    public  static Boolean resFlag;
    private JSONObject jsonObject;


    public ImageMain(String imagePath, int imageFlag, Integer baike_num){
        ImageMain.res="";
        this.imagePath=imagePath;
        this.imageFlag=imageFlag;
        this.baike_num=baike_num;
        this.jsonObject=new JSONObject();
        runner();
    }


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
        MainActivity.threadPoolExecutor.execute(runnable);
    }


    private void getInfoRunner()throws JSONException{

        HashMap<String, String> options = new HashMap<>();
        options.put("baike_num",baike_num.toString());
        BaiduImgClient client=new BaiduImgClient();

        switch (imageFlag) {
            case 1: {
                this.jsonObject=client.advancedGeneral(imagePath,options);
                res=JsonToRes.generalImageRes(this.jsonObject);
                ImageMain.resFlag=true;
                break;
            }
            case 2: {
                this.jsonObject=client.plantDetect(imagePath,options);
                System.out.println(this.jsonObject.toString());
                res=JsonToRes.animalAndPlantImageRes(this.jsonObject);
                ImageMain.resFlag=true;
                break;
            }
            case 3: {
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
