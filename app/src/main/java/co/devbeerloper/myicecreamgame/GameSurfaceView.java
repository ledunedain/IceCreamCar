package co.devbeerloper.myicecreamgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements Runnable {

    private boolean isPlaying;
    private IceCreamCar icecreamCar;
    private Children children;
    private Rock rock;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;
    private Thread gameplayThread = null;
    private int Score=0;
    private int level=0;

    /**
     * Contructor
     * @param context
     */
    public GameSurfaceView(Context context, float screenWith, float screenHeight) {
        super(context);
        icecreamCar = new IceCreamCar(context, screenWith, screenHeight);
        children = new Children(context,screenWith,screenHeight);
        rock = new Rock(context,screenWith,screenHeight);
        paint = new Paint();
        holder = getHolder();
        isPlaying = true;
    }

    /**
     * Method implemented from runnable interface
     */
    @Override
    public void run() {
        while (isPlaying) {
            updateInfo();
            paintFrame();

        }

    }

    private void updateInfo() {
        icecreamCar.updateInfo ();
        children.updateInfo();
        rock.updateInfo();

        if(
                !(icecreamCar.getPositionX() > children.getPositionX() + children.getSpriteSizeWidth())&&
                !(icecreamCar.getPositionX() + icecreamCar.getSpriteSizeWidth()< children.getPositionX()) &&
                !(icecreamCar.getPositionY() > children.getPositionY() + children.getSpriteSizeHeigth()) &&
                !(icecreamCar.getPositionY() + icecreamCar.getSpriteSizeWidth()<children.getPositionY() )
        )
        {
            children.setPositionX(children.getInitX());
            children.setPositionY((int) (Math.random() * 800) + 1);
            Score++;
            if((Score%8)==0){
                level++;
                children.velocityUpdate();
                rock.velocityUpdate();
                Score=0;
            }
        }

        if(
                !(icecreamCar.getPositionX() > rock.getPositionX() + rock.getSpriteSizeWidth())&&
                !(icecreamCar.getPositionX() + icecreamCar.getSpriteSizeWidth()< rock.getPositionX()) &&
                !(icecreamCar.getPositionY() > rock.getPositionY() + rock.getSpriteSizeHeigth()) &&
                !(icecreamCar.getPositionY() + icecreamCar.getSpriteSizeWidth()<rock.getPositionY() )
        )
        {
            children.setPositionX(children.getInitX());
            children.setPositionY((int) (Math.random() * 800) + 1);
            rock.setPositionX(rock.getInitX());
            rock.setPositionY((int) (Math.random() * 800) + 1);
            Score=0;
            level=0;
            rock.reset();
            children.reset();

        }
    }

    private void paintFrame() {
        if (holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            Paint pincel1 = new Paint();
            pincel1.setTextSize(30);
            canvas.drawColor(Color.LTGRAY);
            canvas.drawBitmap(icecreamCar.getSpriteIcecreamCar(),icecreamCar.getPositionX(),icecreamCar.getPositionY(),paint);
            canvas.drawBitmap(children.getSpriteChildren(),children.getPositionX(),children.getPositionY(),paint);
            canvas.drawBitmap(rock.getSpriteRock(),rock.getPositionX(),rock.getPositionY(),paint);
            canvas.drawText("PUNTAJE: " + Score + "    NIVEL: " + level, 1600, 200,pincel1);
            holder.unlockCanvasAndPost(canvas);
        }

    }


    public void pause() {
        isPlaying = false;
        try {
            gameplayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void resume() {

        isPlaying = true;
        gameplayThread = new Thread(this);
        gameplayThread.start();
    }

    /**
     * Detect the action of the touch event
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                System.out.println("TOUCH UP - STOP JUMPING");
                icecreamCar.setJumping(false);
                break;
            case MotionEvent.ACTION_DOWN:
                System.out.println("TOUCH DOWN - JUMP");
                icecreamCar.setJumping(true);
                break;
        }
        return true;
    }

}
