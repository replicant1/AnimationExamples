package bailey.rod.animationexamples;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Illustrates one of the simplest ways to do animation. Use a ValueAnimator and then in the ANimationUpdateListener
 * apply the updated value to the view property yourself. The ValueAnimator is defined in XML.
 */
public class InTrayValueAnimatorXmlActivity extends AppCompatActivity {


    private static final String TAG = InTrayValueAnimatorXmlActivity.class.getSimpleName();


    private boolean animationDirection = false;

    private View inTrayView;

    private ValueAnimator slideInAnimator;

    private ValueAnimator slideOutAnimator;

    private Button toggleInTrayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_in_tray);
        toggleInTrayButton = (Button) findViewById(R.id.in_tray_toggle_button);
        inTrayView = findViewById(R.id.in_tray_view);
        toggleInTrayButton.setOnClickListener(new ToggleButtonClickListener());

        slideInAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.animator_in_tray_slide_in);
        slideInAnimator.setTarget(inTrayView);

        Log.d(TAG, "slideInAnimator=" + slideInAnimator);

        slideOutAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.animator_in_tray_slide_out);
        slideOutAnimator.setTarget(inTrayView);

        Log.d(TAG, "slideOutAnimator=" + slideOutAnimator);
    }


    /**
     * Kicks off animation when toggle button is clicked
     */
    private class ToggleButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Toggle button clicked");
            Log.d(TAG, "Hello Mr Gumby");
            Log.d(TAG, "slideInAnimator=" + slideInAnimator);
            Log.d(TAG, "slideOutAnimator=" + slideOutAnimator);

            ValueAnimator animator = animationDirection ? slideOutAnimator : slideInAnimator;
            animator.addUpdateListener(new AnimatorUpdateListener());
            animator.start();

            Log.d(TAG, "animator=" + animator);

            animationDirection = !animationDirection;
        }
    }

    /**
     * Listens for periodic updates from ValueAnimator and updates view apropos
     */
    private class AnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Log.d(TAG, "onAnimationUpdated. animatedValue=" + valueAnimator.getAnimatedValue() + " animated " +
                    "Fraction=" + valueAnimator.getAnimatedFraction()
                    + " currentPlayTime=" + valueAnimator.getCurrentPlayTime());

            RelativeLayout.LayoutParams relLP = (RelativeLayout.LayoutParams) inTrayView.getLayoutParams();
            relLP.height = (Integer) valueAnimator.getAnimatedValue();
            inTrayView.setLayoutParams(relLP);
        }
    }
}
