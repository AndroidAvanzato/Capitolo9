package it.androidavanzato.testing;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView resultText = (TextView) findViewById(R.id.result);
        findViewById(R.id.update_button_1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                System.out.println(1 / 0);
            }
        });
        findViewById(R.id.update_button).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                        return null;
                    }

                    @Override protected void onPostExecute(Void aVoid) {
                        resultText.setText("Result");
                    }
                }.execute();
            }
        });
    }
}
