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

    public static String generalImageRes(JSONObject jsonObject) throws JSONException {
        StringBuilder res=new StringBuilder();
        ArrayList<String> resArrrayTemp=new ArrayList<>();
        String root;
        String keyword;
        String description;
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

        res.append("识别物体个数：").append(resArrrayTemp.size()).append("\n\n");
        for (int i=0;i<resArrrayTemp.size();i++){
            res.append(resArrrayTemp.get(i));
            res.append("\n\n");
        }
        System.out.println(res.toString());

        return res.toString();
    }

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
        System.out.println(resTemp.toString());

        return resTemp.toString();
    }

}
