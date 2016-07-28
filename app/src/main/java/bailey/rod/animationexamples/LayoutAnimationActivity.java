package bailey.rod.animationexamples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

    private static final int LEFT_COLUMN_DEFAULT_WEIGHT = 1;

    private static final int RIGHT_COLUMN_DEFAULT_WEIGHT = 1;

    private static final int RIGHT_COLUMN_MAX_WEIGHT = 20;

    private static final String TAG = LayoutAnimationActivity.class.getSimpleName();

    private Button toggleHorizButton;

    private Button scheduleVertButton;

    private ViewGroup leftPrimaryColumn;

    private Button toggleVertButton;

    private ViewGroup rightPrimaryColumn;

    private Button scheduleHorizButton;

    private LinearLayout twoColumnsLinearLayout;

    private TextView view1;

    private View1State view1State;

    private TextView view2;

    private RightColumnState rightColumnState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);

        toggleVertButton = (Button) findViewById(R.id.change_layout_button);
        toggleVertButton.setOnClickListener(new ToggleVertButtonClickListener());

        toggleHorizButton = (Button) findViewById(R.id.change_layout_horizontal_button);
        toggleHorizButton.setOnClickListener(new ToggleHorizButtonClickListener());

        scheduleVertButton = (Button) findViewById(R.id.schedule_layout_button);
        scheduleVertButton.setOnClickListener(new ScheduleVertButtonClickListener());

        scheduleHorizButton = (Button) findViewById(R.id.schedule_layout_button_horizontal);
        scheduleHorizButton.setOnClickListener(new ScheduleHorizButtonClickListener());

        view1 = (TextView) findViewById(R.id.layout_animation_text_view_1);
        view2 = (TextView) findViewById(R.id.layout_animation_text_view_2);

        twoColumnsLinearLayout = (LinearLayout) findViewById(R.id.two_columns_linear_layout);
        leftPrimaryColumn = (ViewGroup) findViewById(R.id.left_primary_column);
        rightPrimaryColumn = (ViewGroup) findViewById(R.id.right_primary_column);

        setView1Weight(VIEW_1_DEFAULT_WEIGHT);
        setView2Weight(VIEW_2_DEFAULT_WEIGHT);
        view1State = View1State.VIEW_1_STATE_DEFAULT;


        setLeftColumnWeight(LEFT_COLUMN_DEFAULT_WEIGHT);
        setRightColumnWeight(RIGHT_COLUMN_DEFAULT_WEIGHT);
        rightColumnState = RightColumnState.RIGHT_COLUMN_STATE_DEFAULT;
    }

    private void setView1Weight(int newView1Weight) {
        LinearLayout.LayoutParams llParams = (LinearLayout.LayoutParams) view1.getLayoutParams();
        llParams.weight = newView1Weight;
//        view1.setText(getString(R.string.label_text_view_1, llParams.weight));
        view1.setLayoutParams(llParams);
    }

    private void setView2Weight(int newView1Weight) {
        LinearLayout.LayoutParams llParams = (LinearLayout.LayoutParams) view2.getLayoutParams();
        llParams.weight = newView1Weight;
//        view2.setText(getString(R.string.label_text_view_2, llParams.weight));
        view2.setLayoutParams(llParams);
    }

    private void setLeftColumnWeight(int weight) {
        LinearLayout.LayoutParams llParams = (LinearLayout.LayoutParams) leftPrimaryColumn.getLayoutParams();
        llParams.weight = weight;
        leftPrimaryColumn.setLayoutParams(llParams);
    }

    private void setRightColumnWeight(int weight) {
        LinearLayout.LayoutParams llParams = (LinearLayout.LayoutParams) rightPrimaryColumn.getLayoutParams();
        llParams.weight= weight;
        rightPrimaryColumn.setLayoutParams(llParams);
    }

    enum RightColumnState {
        RIGHT_COLUMN_STATE_DEFAULT, RIGHT_COLUMN_STATE_WIDE;
    }

    enum View1State {
        VIEW_1_STATE_DEFAULT, VIEW_1_STATE_TALL;
    }

    private class ToggleHorizButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Toggle horizontal");
            switch (rightColumnState) {
                case RIGHT_COLUMN_STATE_DEFAULT:
                    setRightColumnWeight(RIGHT_COLUMN_MAX_WEIGHT);
                    rightColumnState = RightColumnState.RIGHT_COLUMN_STATE_WIDE;
                    break;

                case RIGHT_COLUMN_STATE_WIDE:
                    setRightColumnWeight(RIGHT_COLUMN_DEFAULT_WEIGHT);
                    rightColumnState = RightColumnState.RIGHT_COLUMN_STATE_DEFAULT;
                    break;
            }
        }
    }

    private class ToggleVertButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Toggle Vert");
            switch (view1State) {
                case VIEW_1_STATE_DEFAULT:
                    setView1Weight(VIEW_1_MAX_WEIGHT);
                    view1State = View1State.VIEW_1_STATE_TALL;
                    break;

                case VIEW_1_STATE_TALL:
                    setView1Weight(VIEW_1_DEFAULT_WEIGHT);
                    view1State = View1State.VIEW_1_STATE_DEFAULT;
                    break;
            }
        }
    }

    private class ScheduleVertButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Schedule Vert");
            rightPrimaryColumn.scheduleLayoutAnimation();
        }
    }

    private class ScheduleHorizButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.d(TAG, "Schedule Horiz");
            rightPrimaryColumn.scheduleLayoutAnimation();
        }
    }
}
