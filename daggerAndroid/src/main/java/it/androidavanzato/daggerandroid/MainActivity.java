package it.androidavanzato.daggerandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationComponent component = ((MyApplication) getApplicationContext()).getComponent();
        DaggerMainActivityComponent.builder()
                .applicationComponent(component)
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        ListView listView = new ListView(this);
        setContentView(listView);
        listView.setAdapter(adapter);
    }
}
