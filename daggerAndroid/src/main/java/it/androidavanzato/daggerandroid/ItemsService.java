package it.androidavanzato.daggerandroid;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ItemsService {

    @Inject public ItemsService() {
    }

    public List<String> loadItems() {
        return Arrays.asList("A", "B", "C", "D", "E", "F");
    }

    public void starItem(String value) {
        System.out.println("star item");
    }
}
