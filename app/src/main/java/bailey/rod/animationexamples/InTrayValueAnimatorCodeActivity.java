package bailey.rod.animationexamples;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Illustrates one of the simplest ways to do animation. Use a ValueAnimator and then in the AnimationUpdateListener
 * apply the updated value to the view property yourself. The ValueAnimator is defined in code.
 */
public class InTrayValueAnimatorCodeActivity extends AppCompatActivity {

    private static final int DURATION_MS = 500;

    private static final String TAG = InTrayValueAnimatorCodeActivity.class.getSimpleName();

    private static final int IN_TRAY_OPEN_HEIGHT_PX = 0;

    private static final int IN_TRAY_CLOSED_HEIGHT_PX = 1000;

    private boolean animationDirection = false;

    private View inTrayView;

    private Button toggleInTrayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_tray);
        toggleInTrayButton = (Button) findViewById(R.id.in_tray_toggle_button);
        inTrayView = findViewById(R.id.in_tray_view);
        toggleInTrayButton.setOnClickListener(new ToggleButtonClickListener());
    }

    /** Kicks off animation when toggle button is clicked */
    private class ToggleButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Toggle button clicked");

            ValueAnimator animator = animationDirection ? //
                    ValueAnimator.ofInt(IN_TRAY_CLOSED_HEIGHT_PX, IN_TRAY_OPEN_HEIGHT_PX) : //
                    ValueAnimator.ofInt(IN_TRAY_OPEN_HEIGHT_PX,
                                        IN_TRAY_CLOSED_HEIGHT_PX);
            animator.setDuration(DURATION_MS); // milliseconds
            animator.addUpdateListener(new AnimatorUpdateListener());
            animator.setInterpolator(new DecelerateInterpolator());

            animator.start();

            animationDirection = !animationDirection;
        }
    }

    /** Listens for periodic updates from ValueAnimator and updates view apropos */
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
