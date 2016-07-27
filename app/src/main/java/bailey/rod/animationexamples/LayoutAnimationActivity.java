package bailey.rod.animationexamples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class LayoutAnimationActivity extends AppCompatActivity {

    private static final int VIEW_1_MAX_WEIGHT = 20;

    private static final int VIEW_1_DEFAULT_WEIGHT = 1;

    private static final int VIEW_2_DEFAULT_WEIGHT = 1;

    private Button maxWeightButton;

    private Button defaultWeightButton;

    private TextView view1;

    private TextView view2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);

        maxWeightButton = (Button) findViewById(R.id.add_weight_button);
        maxWeightButton.setOnClickListener(new MaxWeightButtonClickListener());

        defaultWeightButton = (Button) findViewById(R.id.remove_weight_button);
        defaultWeightButton.setOnClickListener(new DefaultWeightButtonClickListener());

        view1 = (TextView) findViewById(R.id.layout_animation_text_view_1);
        view2 = (TextView) findViewById(R.id.layout_animation_text_view_2);

        setView1Weight(VIEW_1_DEFAULT_WEIGHT);
        setView2Weight(VIEW_2_DEFAULT_WEIGHT);
    }

    private void setView1Weight(int newView1Weight) {
        LinearLayout.LayoutParams llParams = (LinearLayout.LayoutParams) view1.getLayoutParams();
        llParams.weight = newView1Weight;
        view1.setText(getString(R.string.label_text_view_1, llParams.weight));
        view1.setLayoutParams(llParams);
    }

    private void setView2Weight(int newView1Weight) {
        LinearLayout.LayoutParams llParams = (LinearLayout.LayoutParams) view2.getLayoutParams();
        llParams.weight = newView1Weight;
        view2.setText(getString(R.string.label_text_view_2, llParams.weight));
        view2.setLayoutParams(llParams);
    }

    private class MaxWeightButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setView1Weight(VIEW_1_MAX_WEIGHT);
        }
    }

    private class DefaultWeightButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            setView1Weight(VIEW_1_DEFAULT_WEIGHT);
        }
    }
}
