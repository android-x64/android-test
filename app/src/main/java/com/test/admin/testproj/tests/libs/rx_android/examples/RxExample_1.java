package com.test.admin.testproj.tests.libs.rx_android.examples;

import android.util.Log;

import com.test.admin.testproj.tests.libs.rx_android.utils.Network;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;


/**
 * Created on 15.08.2015.
 */
public class RxExample_1 implements RxExampleExecutor {
    @Override
    public void execute() {
        example_5();
    }

    private void example_1() {
        final Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5)
                .subscribe(integers -> {/* onNext() implementation*/}, e -> e.printStackTrace());

        // Dispose the subscription when not interested in the emitted data any more
        disposable.dispose();
    }

    private void example_1_1() {
        final DisposableObserver<Integer> disposableObserver = Observable.fromArray(1, 2, 3, 4, 5)
                .subscribeWith(new  DisposableObserver<Integer>() {

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("WWW", "WWW integer=" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void example_2() {
        final Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5)
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .subscribe(integer -> Log.e("WWW", "WWW integer=" + integer));
    }

    private void example_3() {
        final Disposable disposable = Observable.fromArray(1, 2, 3, 4, 5)
                .subscribeOn(Schedulers.newThread())
                .observeOn(mainThread())
                .filter(integer -> integer % 2 == 0)
                .subscribe(integer -> {
                    Log.e("WWW", "WWW integer=" + integer);
                });
    }

    private void example_4() {
        List<Integer> ids = Arrays.asList(5, 3, 4, 1, 2);
        Observable.fromArray(ids.toArray(new Integer[]{}))
                .subscribeOn(Schedulers.io())
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
