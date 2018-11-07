package com.unit;

import com.entity.User;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Core extends Thread{

    private static String timeJudge(){
        Date time = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH");
        String emotion ;
        int userTime = Integer.parseInt(ft.format(time));
        if ((userTime < 24 && userTime > 22) || (userTime > 0 && userTime < 8) ){
            emotion = "sadness";
            return emotion;
        }else if(userTime > 8 && userTime <12){
            emotion = "positive";
            return emotion;
        }else if(userTime >12 && userTime <17){
            emotion = "working";
            return emotion;
        }else {
            emotion = "night";
            return emotion;
        }
    }

    private static String positionJudge(User user){
        String emotion = "";
        if(true){
            emotion = "working";
            return emotion;
        }else {
            emotion = "relax";
            return emotion;
        }
    }

    public static String judge(User user){
        String emotionTime = timeJudge();
        String emotionPosition = positionJudge(user);
        String finalEmotion = "";
        //按照一种我也不知道什么鬼的规则来判断感情，代码可读性奇差无比，无法维护，强者麻烦重构一下
        if((emotionTime.equals("positive") || emotionTime.equals("working")) && emotionPosition.equals("working")){
            finalEmotion = "positive";
            return finalEmotion;
        }else if ((emotionTime.equals("positive") || emotionTime.equals("working")) && emotionPosition.equals("relax")){
            finalEmotion = "happy";
            return finalEmotion;
        }else if(emotionTime.equals("sadness")){
            finalEmotion = "sadness";
            return finalEmotion;
        }else if(emotionTime.equals("night")){
            finalEmotion = "night";
            return finalEmotion;
        }else {
            finalEmotion = "easy";
            return finalEmotion;
        }
    }
}
