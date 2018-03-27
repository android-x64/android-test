package com.test.admin.testproj.tests.libs.rx_android.utils;


import com.test.admin.testproj.tests.libs.rx_android.models.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Single;


public class Network {
    public static Beer getBeer(Integer id) {
        //some network request
        return new Beer(new Random().nextFloat());
    }

    public static Observable<Beer> getBeerAsObservable(int id) {
        //some network request
        return Observable.create(subscriber -> {
            subscriber.onNext(new Beer(new Random().nextFloat()));
            subscriber.onComplete();
        });
    }

    public static Observable<List<Beer>> searchBeer(String name) {
        //some network request
        return Observable.create(subscriber -> {
            subscriber.onNext(new ArrayList<Beer>());
            subscriber.onComplete();
        });
    }

    public static Single<List<Beer>> getBeers() {
        //some network request
        return Single.create(emitter -> emitter.onSuccess(new ArrayList<Beer>()));
    }
}
