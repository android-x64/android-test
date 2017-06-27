package com.test.admin.testproj.tests.libs.rx_android.examples;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * Created on 15.08.2015.
 */
public class RxExample_2 implements RxExampleExecutor {

    private static final long TIMEOUT_IN_SECONDS = 5;
    private static final long RETRY_COUNT_FOR_REQUEST = 3;

    @Override
    public void execute() {
        Subscriber<JSONObject> subscriber = Subscribers.create(new Action1<JSONObject>() {
            @Override
            public void call(JSONObject jsonObject) {

            }
        });

        final Subscription subscription =
                createApiRequestObservable()
                        .timeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                        .retry(RETRY_COUNT_FOR_REQUEST)
                        .onErrorResumeNext(createRequestErrorHandler())
                        .map(createJsonMapOperator())
                        .onErrorReturn(createJsonErrorHandler())
                        .doOnNext(createCacheOperation())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
    }

    private Action1<JSONObject> createCacheOperation() {
        return new Action1<JSONObject>() {
            @Override
            public void call(JSONObject jsonObject) {
                //cache json
                //lruCache.cacheJson(jsonObject);
            }
        };
    }

    private Func1<Throwable, JSONObject> createJsonErrorHandler() {
        return new Func1<Throwable, JSONObject>() {
            @Override
            public JSONObject call(Throwable throwable) {
                return new JSONObject();//jsonObjectForErrors;
            }
        };
    }

    private Func1<String, JSONObject> createJsonMapOperator() {
        return new Func1<String, JSONObject>() {
            @Override
            public JSONObject call(String s) {
                return new JSONObject();//JsonUtils.parse(s);
            }
        };
    }

    private Observable<String> createRequestErrorHandler() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onError(new Throwable("Error")/*apiService.getError()*/);
            }
        });
    }

    private Observable<String> createApiRequestObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Data"/*apiService.getData()*/);
                subscriber.onCompleted();
            }
        });
    }
}
