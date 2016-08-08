package bailey.rod.animationexamples;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Illustrates use of the ObjectAnimator to achieve the sliding "In tray". This is
 * meant to be slightly more "convenient" than using a ValueAnimator as it takes
 * care of not just time-varying of the value, but the application of the value to
 * a property of the obect being animated.
 */
public class InTrayObjectAnimatorInCodeActivity extends AppCompatActivity {

    private static final String TAG = InTrayObjectAnimatorInCodeActivity.class.getSimpleName();

    private View inTrayView;

    private Button toggleInTrayButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_tray);
        toggleInTrayButton = (Button) findViewById(R.id.in_tray_toggle_button);
        inTrayView = findViewById(R.id.in_tray_view);

        toggleInTrayButton.setOnClickListener(new ToggleButtonClickListener());
    }

    private class ToggleButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Toggle button clicked");

            // static ObjectAnimator	ofObject(Object target, String propertyName, TypeEvaluator evaluator,
            // Object... values)
            RelativeLayout.LayoutParams relativeLP = (RelativeLayout.LayoutParams) inTrayView.getLayoutParams();

            // The animation starts with a height of 0 px, but all other attributes are as specified in the
            // layout XML.
            RelativeLayout.LayoutParams startRLP = new RelativeLayout.LayoutParams(relativeLP);
            startRLP.height = 0;

            // Animation ends with a height of 1000px, but all other attributes of the RLP are the same.
            RelativeLayout.LayoutParams endRLP = new RelativeLayout.LayoutParams(relativeLP);
            endRLP.height = 1000;

            ObjectAnimator animator = ObjectAnimator.ofObject(//
                                                              inTrayView, // target
                                                              "layoutParams", // propertyName
                                                              new RelativeLayoutParamsEvaluator(), // type evaluator
                                                              startRLP, // "from" value
                                                              endRLP); // "to" value
            animator.setDuration(500);
            animator.setInterpolator(new DecelerateInterpolator());

            animator.start();
        }
    }

    private class RelativeLayoutParamsEvaluator implements TypeEvaluator<RelativeLayout.LayoutParams> {

        @Override
        public RelativeLayout.LayoutParams evaluate(float fraction, RelativeLayout.LayoutParams layoutParamsStart,
                                                    RelativeLayout.LayoutParams layoutParamsEnd) {

            RelativeLayout.LayoutParams result = new RelativeLayout.LayoutParams(layoutParamsStart);
            Log.d(TAG, "fraction=" + fraction);

            float fractionOfHeight = ((float)(layoutParamsEnd.height - layoutParamsStart.height)) * fraction;
            Log.d(TAG, "fractionOfHeight=" + fractionOfHeight);

            result.height = (int) fractionOfHeight;

            return result;
        }
    }
}
