package it.androidavanzato.dependencyinjection;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.TestScheduler;

public class TeeestScheduler {
    public static void main(String[] args) {
        TestScheduler testScheduler = new TestScheduler();
        Observable.interval(1, TimeUnit.SECONDS, testScheduler).limit(10).subscribe(new Action1<Long>() {
            @Override public void call(Long l) {
                System.out.println(l);
            }
        });
        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS);
        System.out.println("------");
        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS);
    }
}
