package it.androidavanzato.testing;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;
    private View updateButton;
    private TextView resultText;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        updateButton = activity.findViewById(R.id.update_button);
        resultText = (TextView) activity.findViewById(R.id.result);
    }

    public void testClickOnButton() {
        updateButton.requestFocus();
        activity.runOnUiThread(new Runnable() {
            @Override public void run() {
                updateButton.requestFocus();
            }
        });
        sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
        for (int i = 0; i < 50; i++) {
            if (resultText.getText().equals("Result")) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        assertEquals("Result", resultText.getText());
    }
}
