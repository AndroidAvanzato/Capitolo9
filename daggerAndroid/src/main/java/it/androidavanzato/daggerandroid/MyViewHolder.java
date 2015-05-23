package it.androidavanzato.daggerandroid;

import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

public class MyViewHolder {
    @Inject ItemsService itemsService;

    private TextView view;

    private String value;

    @Inject public MyViewHolder() {
        System.out.println("create MyViewHolder");
    }

    public void init(TextView view) {
        this.view = view;
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                itemsService.starItem(value);
            }
        });
    }

    public void populate(String value) {
        this.value = value;
        view.setText(value);
    }
}
