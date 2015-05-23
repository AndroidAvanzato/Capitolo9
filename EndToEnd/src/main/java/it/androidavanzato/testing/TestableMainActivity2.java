package it.androidavanzato.testing;

import java.util.List;

public class TestableMainActivity2 extends MainActivity2 {

    public static List<String> items;

    protected List<String> loadItems() {
        return items;
    }
}