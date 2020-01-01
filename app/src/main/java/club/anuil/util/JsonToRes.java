package club.anuil.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The quieter you become , the more you are able to hear
 *
 * @author： AnuilLne
 * @date： 2019/12/29 20:32
 * @description: 将Json提取成Result
 */
public class JsonToRes {

    /**
     * @author： AnuilLne
     * @date：   2020/1/1 16:41
     * @param： jsonObject
     * @return： java.lang.String
     * @description：  通用物体识别 默认返回识别物体个数为5
     */
    public static String generalImageRes(JSONObject jsonObject) throws JSONException {

        //res 识别结果
        StringBuilder res=new StringBuilder();
        ArrayList<String> resArrrayTemp=new ArrayList<>();
        //通用识别结果的root键 表示物体所属的上层
        String root;
        //keyword键 表示识别的结果
        String keyword;
        //相关结果描述
        String description;
        //通用识别有个数 默认为5个 不支持改变
        int result_num;

        //解析Json

        result_num=jsonObject.getInt("result_num");

        JSONArray jsonArray=jsonObject.getJSONArray("result");

        for (int i=0;i<result_num;i++){
            StringBuilder resTemp=new StringBuilder();
            root=jsonArray.getJSONObject(i).getString("root");
            resTemp.append("所属上层：").append(root).append("\n");
            keyword=jsonArray.getJSONObject(i).getString("keyword");
            resTemp.append("名称：").append(keyword).append("\n");
            //判断baike_info是否存在
            if (!jsonArray.getJSONObject(i).isNull("baike_info")){
                JSONObject temp=jsonArray.getJSONObject(i).getJSONObject("baike_info");
                if (!temp.isNull("description")) {
                    description = temp.getString("description");
                    resTemp.append("描述：").append(description).append("\n");
                }
            }

            //添加到结果数组中
            resArrrayTemp.add(resTemp.toString());
        }
        //构造图像的识别结果字符串
        res.append("识别物体个数：").append(resArrrayTemp.size()).append("\n\n");
        for (String s : resArrrayTemp) {
            res.append(s);
            res.append("\n\n");
        }
        return res.toString();
    }

    /**
     * @author： AnuilLne
     * @date：   2020/1/1 16:42
     * @param： jsonObject
     * @return： java.lang.String
     * @description： 动植物识别 默认返回识别物体个数为5
     */
    public static String animalAndPlantImageRes(JSONObject jsonObject) throws JSONException{
        StringBuilder resTemp=new StringBuilder();
        String name;
        String description;

        name=jsonObject.getJSONArray("result").getJSONObject(0).getString("name");
        resTemp.append("名称：").append(name).append("\n");

        JSONObject jsonObject1=jsonObject.getJSONArray("result").getJSONObject(0);
        if(!jsonObject1.isNull("baike_info")) {
            if (!jsonObject1.getJSONObject("baike_info").isNull("description")) {
                description = jsonObject.getJSONArray("result").getJSONObject(0).getJSONObject("baike_info").getString("description");
                resTemp.append("描述：").append(description).append("\n");
            }
        }

        return resTemp.toString();
    }

}
