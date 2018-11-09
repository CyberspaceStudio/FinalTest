import com.dao.Dao;
import com.dao.VideoImpl;
import com.entity.Video;
import com.util.JdbcUtils;
import org.json.JSONObject;

import java.sql.ResultSet;

public class TTTT {
    public static void main(String[] args){
        ResultSet rs = null ;
        try{
            rs = JdbcUtils.query("SELECT * FROM video WHERE id=1");
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject object = new JSONObject();
        try {
            if (rs.next()) {
                object.put("videourl",rs.getString("videourl"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Video video = new Video();
        Dao videoDao = new VideoImpl();
        try {
            video = ((VideoImpl) videoDao).query("emotion","night");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(video.getEmotion());
        System.out.println(object.toString());
    }
}
