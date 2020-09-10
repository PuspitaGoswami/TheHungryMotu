package com.example.user.thehungrymotu;


import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import android.media.MediaPlayer;

import android.view.MotionEvent;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public class HungryMotuView extends View {

    private Bitmap motu, motu2;
    private Bitmap redapplesmall;
    private Bitmap strawberrysmall;
    private Bitmap p1cakesmall;
    private Bitmap orangesmall;
    private Bitmap burgersmall;
    private Bitmap dsamosa;
    private Bitmap dsamosa2;
    private Bitmap samosa2;
    private Bitmap pluslife;
    private Bitmap slowfood;
    private Bitmap star;

    private int motuX = 10, motu2X;
    private int motuY, motu2Y;

    private int level;

    private int motuSpeed, canvasWidth, canvasHeight;
    private boolean touch = false;

    private Bitmap backgroundImage[] = new Bitmap[20];
    private Paint scorePaint = new Paint();
    private Paint levelpaint = new Paint();

    private Bitmap life[] = new Bitmap[2];

    private int speed = 11;
    private int redapplesmallX, redapplesmallY, redapplesmallSpeed = speed, score;
    private int strawberrysmallX, strawberrysmallY, strawberrysmallSpeed = speed;
    private int p1cakesmallX, p1cakesmallY, p1cakesmallSpeed = speed;
    private int burgersmallX, burgersmallY, burgersmallSpeed = speed;
    private int orangesmallX, orangesmallY, orangesmallSpeed = speed;
    private int pluslifeX, pluslifeY, pluslifeSpeed = speed;
    private int imageshowX, imageshowY, imageshowSpeed = speed;
    private int dsamosaX, dsamosaY, dsamosaSpeed = speed;
    private int dsamosa2X, dsamosa2Y, dsamosa2Speed = speed;
    private int samosa2X, samosa2Y, samosa2Speed = speed;
    private int starX, starY, starSpeed = speed;
    private int slowfoodX, slowfoodY, slowfoodSpeed = speed;
    private int lifeCounter, lscore, prevlevel, plusscore;
    private SoundPlayer sound;
    private MediaPlayer mediaPlayer[] = new MediaPlayer[30];
    private Bitmap image[] = new Bitmap[30];
    private boolean hitSound, difficulty;

    public HungryMotuView(Context context, int score, int level, int speed, int lifeCounter, SoundPlayer sound, boolean hitSound, boolean difficulty) {
        super(context);
        this.level = level;
        this.score = score;
        this.speed = speed;
        this.lifeCounter = lifeCounter;
        this.sound = sound;
        this.hitSound = hitSound;
        this.difficulty = difficulty;

        motu = BitmapFactory.decodeResource(getResources(), R.drawable.motu);
        motu2 = BitmapFactory.decodeResource(getResources(), R.drawable.motu2);

        p1cakesmall = BitmapFactory.decodeResource(getResources(), R.drawable.p1cakesmall);
        redapplesmall = BitmapFactory.decodeResource(getResources(), R.drawable.redapplesmall);
        strawberrysmall = BitmapFactory.decodeResource(getResources(), R.drawable.strawberrysmall);
        burgersmall = BitmapFactory.decodeResource(getResources(), R.drawable.burgersmall);
        orangesmall = BitmapFactory.decodeResource(getResources(), R.drawable.orangesmall);
        pluslife = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        dsamosa = BitmapFactory.decodeResource(getResources(), R.drawable.dngr);
        dsamosa2 = BitmapFactory.decodeResource(getResources(), R.drawable.dngr);
        star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
        slowfood = BitmapFactory.decodeResource(getResources(), R.drawable.slow);
        samosa2 = BitmapFactory.decodeResource(getResources(), R.drawable.samosa);

        backgroundImage[0] = BitmapFactory.decodeResource(getResources(), R.drawable.b12);
        backgroundImage[1] = BitmapFactory.decodeResource(getResources(), R.drawable.b1);
        backgroundImage[2] = BitmapFactory.decodeResource(getResources(), R.drawable.b3);
        backgroundImage[3] = BitmapFactory.decodeResource(getResources(), R.drawable.b7);

        backgroundImage[4] = BitmapFactory.decodeResource(getResources(), R.drawable.b8);
        backgroundImage[5] = BitmapFactory.decodeResource(getResources(), R.drawable.b9);
        backgroundImage[6] = BitmapFactory.decodeResource(getResources(), R.drawable.b10);
        backgroundImage[7] = BitmapFactory.decodeResource(getResources(), R.drawable.b11);


        mediaPlayer[1] = MediaPlayer.create(context, R.raw.good);
        mediaPlayer[2] = MediaPlayer.create(context, R.raw.very);

        mediaPlayer[7] = MediaPlayer.create(context, R.raw.mindblowing);
        mediaPlayer[3] = MediaPlayer.create(context, R.raw.excellent);
        mediaPlayer[4] = MediaPlayer.create(context, R.raw.super_excellent);
        mediaPlayer[5] = MediaPlayer.create(context, R.raw.impressive);
        mediaPlayer[9] = MediaPlayer.create(context, R.raw.very);
        mediaPlayer[0] = MediaPlayer.create(context, R.raw.excellent);
        mediaPlayer[6] = MediaPlayer.create(context, R.raw.amazing);
        mediaPlayer[8] = MediaPlayer.create(context, R.raw.splendid);

        image[1] = BitmapFactory.decodeResource(getResources(), R.drawable.goody_l);
        image[2] = BitmapFactory.decodeResource(getResources(), R.drawable.very_good_l);
        image[3] = BitmapFactory.decodeResource(getResources(), R.drawable.excellent_l);
        image[4] = BitmapFactory.decodeResource(getResources(), R.drawable.super_excellent_l);
        image[5] = BitmapFactory.decodeResource(getResources(), R.drawable.impressive_l);
        image[6] = BitmapFactory.decodeResource(getResources(), R.drawable.aamazing_l);
        image[7] = BitmapFactory.decodeResource(getResources(), R.drawable.mind_blowing_l);
        image[8] = BitmapFactory.decodeResource(getResources(), R.drawable.splendid_l);
        image[9] = BitmapFactory.decodeResource(getResources(), R.drawable.very_good_l);
        image[0] = BitmapFactory.decodeResource(getResources(), R.drawable.excellent_l);


        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(40);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelpaint.setColor(Color.BLACK);
        levelpaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelpaint.setTextSize(40);
        levelpaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

        motuY = 550;
        motu2Y = 550;
        score = 0;
        level = 1;
        prevlevel = 1;
        if (difficulty) plusscore = 10;
        else plusscore = 5;
        lifeCounter = 3;
        lscore = 200;
    }

    public int getScore() {
        return this.score;
    }

    public int getLevel() {
        return this.level;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getLifeCounter() {
        return this.lifeCounter;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        canvas.drawBitmap(backgroundImage[level % 8],0,0, null);


        int minmotuY = motu.getHeight();
        int maxmotuY = canvasHeight - motu.getHeight() * 3;
        motuY = motuY + motuSpeed;
        if (motuY < minmotuY) {
            motuY = minmotuY;
        }
        if (motuY > maxmotuY) {
            motuY = maxmotuY;
        }

        motuSpeed = motuSpeed + 2;

        if (touch) {
            canvas.drawBitmap(motu, motuX, motuY, null);
            touch = false;
        } else {
            canvas.drawBitmap(motu, motuX, motuY, null);
        }


        if (prevlevel < level) {
            speed += 1;

            prevlevel = level;
            redapplesmallSpeed = speed;
            strawberrysmallSpeed = speed;
            p1cakesmallSpeed = speed;
            burgersmallSpeed = speed;
            orangesmallSpeed = speed;

            dsamosaSpeed = speed;
            dsamosa2Speed = speed;
            samosa2Speed = speed;
            starSpeed = speed;
            slowfoodSpeed = speed;
            pluslifeSpeed = speed;


        }

        redapplesmallX = redapplesmallX - redapplesmallSpeed;
        if (hitMotu(redapplesmallX, redapplesmallY)) {
            score = score + plusscore;
            if (hitSound) sound.playHitSound();
            level = score / lscore + 1;


            redapplesmallX = -100;
        }


        if (redapplesmallX < 0) {
            redapplesmallX = canvasWidth + 21;
            redapplesmallY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;

        }

        strawberrysmallX = strawberrysmallX - strawberrysmallSpeed;
        if (hitMotu(strawberrysmallX, strawberrysmallY)) {
            score = score + plusscore;
            if (hitSound) sound.playHitSound();
            level = score / lscore + 1;
            strawberrysmallX = -100;
        }


        if (strawberrysmallX < 0) {
            strawberrysmallX = canvasWidth + 21;
            strawberrysmallY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;
        }

        p1cakesmallX = p1cakesmallX - p1cakesmallSpeed;
        if (hitMotu(p1cakesmallX, p1cakesmallY)) {
            score = score + plusscore;
            level = score / lscore + 1;
            p1cakesmallX = -100;
            if (hitSound) sound.playHitSound();
        }


        if (p1cakesmallX < 0) {
            p1cakesmallX = canvasWidth + 21;
            p1cakesmallY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;

        }

        orangesmallX = orangesmallX - orangesmallSpeed;
        if (hitMotu(orangesmallX, orangesmallY)) {
            score = score + plusscore;
            level = score / lscore + 1;
            orangesmallX = -100;
            if (hitSound) sound.playHitSound();
        }


        if (orangesmallX < 0) {
            orangesmallX = canvasWidth + 21;
            orangesmallY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;

        }

        burgersmallX = burgersmallX - burgersmallSpeed;
        if (hitMotu(burgersmallX, burgersmallY)) {
            score = score + plusscore;
            if (hitSound) sound.playHitSound();
            level = score / lscore + 1;
            burgersmallX = -100;
        }


        if (burgersmallX < 0) {
            burgersmallX = canvasWidth + 21;
            burgersmallY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;
        }

        dsamosaX = dsamosaX - dsamosaSpeed;


        if (hitMotu(dsamosaX, dsamosaY)) {
            dsamosaX = -100;
            lifeCounter--;
            if (hitSound) sound.playDangerSound();
            if (lifeCounter == 0) {
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.putExtra("SCORE", score);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(gameOverIntent);
                if (hitSound) sound.playOverSound();
            }
        }

        if (dsamosaX < 0) {
            dsamosaX = canvasWidth + 21;
            dsamosaY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;

        }
        if (difficulty) {
            dsamosa2X = dsamosa2X - dsamosa2Speed;

            if (hitMotu(dsamosa2X, dsamosa2Y)) {
                dsamosa2X = -100;
                lifeCounter--;
                if (hitSound) sound.playDangerSound();
                if (lifeCounter == 0) {
                    Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                    gameOverIntent.putExtra("SCORE", score);
                    gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(gameOverIntent);
                    if (hitSound) sound.playOverSound();
                }
            }

            if (dsamosa2X < 0) {
                dsamosa2X = canvasWidth + 21;
                dsamosa2Y = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;

            }
            canvas.drawBitmap(dsamosa2, dsamosa2X, dsamosa2Y, null);
        }


        samosa2X = samosa2X - samosa2Speed;

        if (hitMotu(samosa2X, samosa2Y)) {
            score = score + 10;
            level = score / lscore + 1;
            //speed = 11;
            samosa2X = -100;
            if (hitSound) sound.playHitSound();
        }


        if (samosa2X < 0) {
            samosa2X = canvasWidth + 50;
            samosa2Y = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;

        }


        if (lifeCounter == 1) {

            pluslifeX = pluslifeX - pluslifeSpeed;


            if (hitMotu(pluslifeX, pluslifeY)) {
                lifeCounter++;
                pluslifeX = -100;
                if (hitSound) sound.playLifeSound();
            }


            if (pluslifeX < 0) {
                pluslifeX = canvasWidth + 10;
                pluslifeY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;


            }

            canvas.drawBitmap(pluslife, pluslifeX, pluslifeY, null);
        }
        if (speed == 17) {

            slowfoodX = slowfoodX - slowfoodSpeed;


            if (hitMotu(slowfoodX, slowfoodY)) {
                speed = 11;
                slowfoodX = -100;
                if (hitSound) sound.playLifeSound();
                redapplesmallSpeed = speed;
                strawberrysmallSpeed = speed;
                p1cakesmallSpeed = speed;
                burgersmallSpeed = speed;
                orangesmallSpeed = speed;

                dsamosaSpeed = speed;
                dsamosa2Speed = speed;
                samosa2Speed = speed;
                starSpeed = speed;
                slowfoodSpeed = speed;
                pluslifeSpeed = speed;
            }


            if (slowfoodX < 0) {
                slowfoodX = canvasWidth + 10;
                slowfoodY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;


            }

            canvas.drawBitmap(slowfood, slowfoodX, slowfoodY, null);
        }
        if (score % 300 >= 0 && score % 300 <= 50 && level != 1) {

            starX = starX - starSpeed;


            if (hitMotu(starX, starY)) {
                score += 100;
                starX = -100;
                if (hitSound) sound.playLifeSound();
            }


            if (starX < 0) {
                starX = canvasWidth + 10;
                starY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;


            }

            canvas.drawBitmap(star, starX, starY, null);
        }


        imageshowX = imageshowX - imageshowSpeed;


        if (imageshowX < 0) {
            imageshowX = canvasWidth + 10;
            imageshowY = (int) Math.floor(Math.random() * (maxmotuY - minmotuY)) + minmotuY;
        }

        if (score % lscore >= 0 && score % lscore <= 20 && level != 1) {
            canvas.drawBitmap(image[(level - 1) % 10], imageshowX, 50, null);
            if (hitSound) showSoundToast(mediaPlayer[(level - 1) % 10]);
        }


        canvas.drawBitmap(redapplesmall, redapplesmallX, redapplesmallY, null);
        canvas.drawBitmap(strawberrysmall, strawberrysmallX, strawberrysmallY, null);
        canvas.drawBitmap(p1cakesmall, p1cakesmallX, p1cakesmallY, null);
        canvas.drawBitmap(orangesmall, orangesmallX, orangesmallY, null);
        canvas.drawBitmap(burgersmall, burgersmallX, burgersmallY, null);

        canvas.drawBitmap(dsamosa, dsamosaX, dsamosaY, null);

        canvas.drawBitmap(samosa2, samosa2X, samosa2Y, null);


        canvas.drawText("SCORE: " + score, 20, 70, scorePaint);
        canvas.drawText("LEVEL: " + level, 300, 70, levelpaint);

        for (int i = 0; i < 3; i++) {
            int x = (int) (500 + life[0].getWidth() * 1.1 * i);
            int y = 20;

            if (i < lifeCounter) {

                canvas.drawBitmap(life[0], x, y, null);
            }
            /*else{
                canvas.drawBitmap(life[1], x, y, null);
            }*/

        }


    }

       private void showSoundToast(MediaPlayer mediaPlayer) {


//        TextView text = (TextView) layout.findViewById(R.id.text);
//        text.setText(nice);


        mediaPlayer.start();

        // if (color == 1)
        // layout.setBackgroundResource(R.drawable.success);

        //toast.show();
    }
    public boolean hitMotu(int x, int y) {
        {

            if (motuX < x && x < (motuX + motu.getWidth()) && motuY < y && y < (motuY + motu.getHeight()))
                return true;
        }
        return false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            motuSpeed = -22;
        }
        return true;
    }
}
