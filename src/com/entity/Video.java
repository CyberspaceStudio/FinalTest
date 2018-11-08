package com.entity;

import com.entity.base.File;

public class Video extends File {
    private String emotion;

    public String getEmotion() {
        return emotion;
    }

    private void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public void decodeEmotion(){
        StringBuilder temp = new StringBuilder();
        int wordLength = 0;
        while(wordLength < url.length() && url.charAt(wordLength) != '/'){
            temp.append(url.charAt(wordLength));
            wordLength++;
        }
        this.setEmotion(temp.toString());
    }
}
