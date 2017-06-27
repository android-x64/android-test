package com.test.admin.testproj.tests.libs.rx_android.examples;

import android.util.Log;

import com.test.admin.testproj.tests.libs.rx_android.utils.Network;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created on 15.08.2015.
 */
public class RxExample_1 implements RxExampleExecutor {
    @Override
    public void execute() {
        example_5();
    }

    private void example_1() {
        final Subscription s = Observable.from(Arrays.asList(1, 2, 3, 4, 5))
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onNext(Integer integer) {
                        // this can be called multiple times
                    }

                    @Override
                    public void onError(Throwable e) {
                        // no more events, and automatically unsubscribed
                    }

                    @Override
                    public void onCompleted() {
                        // no more events, and automatically unsubscribed
                    }
                });
    }

    private void example_2() {
        final Subscription s = Observable.from(Arrays.asList(1, 2, 3, 4, 5))
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .subscribe(integer -> Log.e("WWW", "WWW integer=" + integer));
    }

    private void example_3() {
        final Subscription s = Observable.from(Arrays.asList(1, 2, 3, 4, 5))
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .filter(integer -> integer % 2 == 0)
                .subscribe(integer -> {
                    Log.e("WWW", "WWW integer=" + integer);
                });
    }

    private void example_4() {
        List<Integer> ids = Arrays.asList(5, 3, 4, 1, 2);
        Observable.from(ids)
                .subscribeOn(io())
                .map(Network::getBeer)
                .filter(beer -> beer.rating > 0.8F)
                // we only need one item that we want to show in our interface: the first beer in
                // this list of five IDs that has a rating higher than 80%
                .take(1)  // only request 1;
                .observeOn(mainThread())
                .subscribe(beer -> Log.e("WWW", "WWW beer=" + beer));
    }

    private void example_5() {
        Executor executor1 = Executors.newSingleThreadExecutor(r -> new Thread(r, "child of executor one"));
        Executor executor2 = Executors.newSingleThreadExecutor(r -> new Thread(r, "child of executor two"));
        Observable.just(Thread.currentThread().getName())
                .subscribeOn(Schedulers.from(executor1))
                .observeOn(Schedulers.from(executor2))
                .subscribe(threadName -> Log.e("WWW", "WWW s=" + threadName)); //main
    }
}
