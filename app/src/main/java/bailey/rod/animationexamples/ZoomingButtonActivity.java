package bailey.rod.animationexamples;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ZoomingButtonActivity extends AppCompatActivity {

    private static final String TAG = ZoomingButtonActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zooming_button);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new FABClickListener());

        // Animate the scaleX and scaleY properties of 'fab' to create the impression that is
        // popping into existence.

    }

    private static class FABClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(TAG, "FAB button clicked");
        }
    }
}
