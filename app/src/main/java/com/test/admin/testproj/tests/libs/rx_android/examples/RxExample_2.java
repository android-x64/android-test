package com.test.admin.testproj.tests.libs.rx_android.examples;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created on 15.08.2015.
 */
public class RxExample_2 implements RxExampleExecutor {

    private static final long TIMEOUT_IN_SECONDS = 5;
    private static final long RETRY_COUNT_FOR_REQUEST = 3;

    @Override
    public void execute() {
        Consumer<JSONObject> subscriber = jsonObject -> {

        };

        final Disposable subscription =
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

    private Consumer<JSONObject> createCacheOperation() {
        return jsonObject -> {
            //cache json
            //lruCache.cacheJson(jsonObject);
        };
    }

    private Function<Throwable, JSONObject> createJsonErrorHandler() {
        return throwable -> {
            return new JSONObject();//jsonObjectForErrors;
        };
    }

    private Function<String, JSONObject> createJsonMapOperator() {
        return s -> {
            return new JSONObject();//JsonUtils.parse(s);
        };
    }

    private Observable<String> createRequestErrorHandler() {
        return Observable.create(emitter -> {
            emitter.onError(new Throwable("Error")/*apiService.getError()*/);
        });
    }

    private Observable<String> createApiRequestObservable() {
        return Observable.create(emitter -> {
            emitter.onNext("Data"/*apiService.getData()*/);
            emitter.onComplete();
        });
    }
}
