package mate.vkmusic;

import java.util.HashMap;

/**
 * Created by Ilya_G on 26.03.2015.
 */
public class VKAudio {

    int id;
    String title;
    String url;

    public VKAudio(HashMap audio){
        this.id=Integer.parseInt(audio.get("id").toString());
        this.title=audio.get("title").toString();
        this.url=audio.get("url").toString();

    }
}
