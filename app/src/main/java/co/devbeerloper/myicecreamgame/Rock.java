package co.devbeerloper.myicecreamgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Rock {
    public static final float INIT_X =2000;
    public static final float INIT_Y =850;
    public static final int SPRITE_SIZE_WIDTH =100;
    public static final int SPRITE_SIZE_HEIGTH=100;
    public static final float GRAVITY_FORCE=10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spriteRock;
    private boolean isJumping;
    private float velocity=5;


    public Rock (Context context, float screenWidth, float screenHeigth){

        speed = 1;
        positionX = this.INIT_X;
        positionY = this.INIT_Y;
        isJumping = false;
        //Getting bitmap from resource
        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.roca);
        spriteRock  = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);

        this.maxX = screenWidth - (spriteRock.getWidth()/2);
        this.maxY = screenHeigth - spriteRock.getHeight();
    }

    public Rock (Context context, float initialX, float initialY, float screenWidth, float screenHeigth){

        speed = 1;
        positionX = initialX;
        positionY = initialY;

        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.roca);
        spriteRock  = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);

        this.maxX = screenWidth - (spriteRock.getWidth()/2);
        this.maxY = screenHeigth - spriteRock.getHeight();

    }

    public static float getInitX() {
        return INIT_X;
    }

    public static float getInitY() {
        return INIT_Y;
    }

    public static int getSpriteSizeWidth() {
        return SPRITE_SIZE_WIDTH;
    }

    public static int getSpriteSizeHeigth() {
        return SPRITE_SIZE_HEIGTH;
    }

    public static float getGravityForce() {
        return GRAVITY_FORCE;
    }

    public int getMIN_SPEED() {
        return MIN_SPEED;
    }

    public int getMAX_SPEED() {
        return MAX_SPEED;
    }

    public float getMaxY() {
        return maxY;
    }

    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }

    public float getMaxX() {
        return maxX;
    }

    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public Bitmap getSpriteRock() {
        return spriteRock;
    }

    public void setSpriteRock(Bitmap spriteRock) {
        this.spriteRock = spriteRock;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    /**
     * Control the position and behaviour of the icecream car
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void updateInfo () {

        positionX -=velocity;
        if(positionX<=0)
        {
            positionX=getInitX();
            positionY= ThreadLocalRandom.current().nextInt(20, 1200 + 1);
        }
    }

    public void velocityUpdate(){
        velocity+=1;
    }

    public void reset() {
        velocity=5;
    }
}
