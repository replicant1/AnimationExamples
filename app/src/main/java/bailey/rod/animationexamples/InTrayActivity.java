package bailey.rod.animationexamples;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

public class InTrayActivity extends AppCompatActivity {

    private View inTrayView;

    private Button toggleInTrayButton;

    enum InTrayState {
        ANIMATING_OPEN, OPEN, ANIMATING_CLOSE, CLOSED;
    }

    private static final String TAG = InTrayActivity.class.getSimpleName();

    private boolean animationDirection = true;

    private static final float IN_TRAY_OPEN_HEIGHT_PX = 0F;

    private static final float IN_TRAY_CLOSED_HEIGHT_PX = 1F;

    private InTrayState inTrayState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_tray);
        toggleInTrayButton = (Button) findViewById(R.id.in_tray_toggle_button);
        inTrayView = findViewById(R.id.in_tray_view);
        toggleInTrayButton.setOnClickListener(new ToggleButtonClickListener());
        inTrayState = InTrayState.CLOSED;
    }

    private class ToggleButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Toggle button clicked");

            ObjectAnimator animator = ObjectAnimator.ofFloat(inTrayView, "alpha", animationDirection ? 0.1f : 1.0f);
            animator.setDuration(2800); // milliseconds
            animator.addUpdateListener(new AnimatorUpdateListener());
            animator.setInterpolator(new BounceInterpolator());
            animator.start();

            animationDirection = !animationDirection;
        }

        private class AnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.d(TAG, "onAnimationupdated. animatedValue=" + valueAnimator.getAnimatedValue() + " animated " +
                        "Fraction=" + valueAnimator.getAnimatedFraction()
                        + " currentPlayTime=" + valueAnimator.getCurrentPlayTime());
            }
        }
    }
}
