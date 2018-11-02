package com.unit;

import com.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Core extends Thread{

    public static String timeJudge(){
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

    public void positionJudge(User user){

    }
}
