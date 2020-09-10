package com.example.user.thehungrymotu;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private  static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private static int lifeSound;
    private static int dangerSound;
    private static int music;




    public  SoundPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        hitSound = soundPool.load(context, R.raw.food, 1);
        overSound = soundPool.load(context, R.raw.over2, 1);
        lifeSound = soundPool.load(context, R.raw.coin, 1);
        dangerSound = soundPool.load(context, R.raw.danger, 1);
        //music = soundPool.load(context, R.raw.music, 1);


    }



    public  void playHitSound(){
        soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public  void playOverSound(){
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public  void playDangerSound(){
        soundPool.play(dangerSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public  void playLifeSound(){
        soundPool.play(lifeSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

}
