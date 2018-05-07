package maheryhaja.mg.simplerotationgesture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import maheryhaja.mg.simplerotationgesture.data.SimplePoint;

/***
 * it is important to set pivotX and pivot Y in XML file
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.linear_hand)
    protected LinearLayout linearHand;

    @ViewById(R.id.root_layout)
    protected RelativeLayout rootLayout;

    private int generalMargin;
    private int rayon;
    private SimplePoint centerPoint;

    boolean isMoving = false;


    @AfterViews
    protected void initAfterViews() {

        initCenterPoint();

        linearHand.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    int[] location = new int[2];
                    rootLayout.getLocationOnScreen(location);
                    int rootLayoutLocationY = location[1];

                    //since getRawY return y position including phone header we need to make it from rootLayout
                    float rotationAngle = getRotationAngle(new SimplePoint(event.getRawX(), event.getRawY()-rootLayoutLocationY), centerPoint);
                    rotate(linearHand, rotationAngle);
                    break;
            }
            return true;
        });

    }


    private void initCenterPoint() {
        generalMargin = getResources().getDimensionPixelSize(R.dimen.general_margin);
        rayon = getResources().getDimensionPixelSize(R.dimen.hand_length);
        centerPoint = new SimplePoint(generalMargin + rayon, generalMargin + rayon);
        Log.d("log", "the program works perfectly " + adjustToCenter(new SimplePoint(), centerPoint));

    }


    /***
     * transform point into mathematical point
     * @param point the point to be translated
     * @param center the center of the circle
     * @return
     */
    public static SimplePoint adjustToCenter(SimplePoint point, SimplePoint center) {
        SimplePoint ans = new SimplePoint();
        ans.setX(point.getX() - center.getX());
        ans.setY((point.getY() - center.getY()) * -1);
        return ans;
    }

    /***
     *
     * @param point the point
     * @param centerPoint the center
     * @return angle of rotation
     */
    public static float getRotationAngle(SimplePoint point, SimplePoint centerPoint) {
        SimplePoint realPoint = adjustToCenter(point, centerPoint);
        return (float) Math.toDegrees(Math.atan2(realPoint.getY(), realPoint.getX()));
    }

    public static void rotate(View v, float angle) {
        // make some adjustments for anti clockwise rotation
        v.setRotation((angle-90f) * -1);
    }


}
