package com.test.admin.testproj.tests.libs.rx_android.utils;


import com.test.admin.testproj.tests.libs.rx_android.models.Beer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;

public class Network {
    public static Beer getBeer(Integer id) {
        //some network request
        return new Beer(new Random().nextFloat());
    }

    public static Observable<Beer> getBeerAsObservable(int id) {
        //some network request
        return Observable.create(subscriber -> {
            subscriber.onNext(new Beer(new Random().nextFloat()));
            subscriber.onCompleted();
        });
    }

    public static Observable<List<Beer>> searchBeer(String name) {
        //some network request
        return Observable.create(subscriber -> {
            subscriber.onNext(new ArrayList<Beer>());
            subscriber.onCompleted();
        });
    }
}
