package mate.vkmusic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Ilya_G on 26.03.2015.
 */
public class VKAudio {

    int id;
    int owner_id;
    String title;
    String artist;
    int duration;
    int size;
    String url;

    public VKAudio(HashMap audio){
        this.id=Integer.parseInt(audio.get("id").toString());
        this.owner_id=Integer.parseInt(audio.get("owner_id").toString());
        this.title=audio.get("title").toString();
        this.artist=audio.get("artist").toString();
        this.duration=Integer.parseInt(audio.get("duration").toString());
        this.url=audio.get("url").toString();
        //this.size=getFileSize(this.url);
    }

    private int getFileSize(String strUrl) {
        URL url=null;
        try{
            url=new URL(strUrl);
        }
        catch (Exception e){

        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            return -1;
        } finally {
            conn.disconnect();
        }
    }
}
