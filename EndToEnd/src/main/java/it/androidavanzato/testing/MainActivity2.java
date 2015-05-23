package it.androidavanzato.testing;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.util.List;


public class MainActivity2 extends ActionBarActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        TextView textView = new TextView(this);
        List<String> items = loadItems();
        //...
        textView.setText(calculateMax(items));
    }

    private List<String> loadItems() {
        //...
        return null;
    }

    private String calculateMax(List<String> items) {
        //...
        return items.get(0);
    }
}
