package com.test.admin.testproj.tests.libs.rx_android_examples.example1;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.Observable;

/**
 * Created by sergey on 2/23/18.
 */

public class UserManagerImpl implements UserManager {
    @Override
    public Observable<User> getUser() {
        return Observable.create(emitter -> emitter.onNext(new User()));
    }

    @Override
    public Completable setName(String name) {
        return Completable.create(CompletableEmitter::onComplete);
    }

    @Override
    public Completable setAge(int age) {
        return Completable.create(CompletableEmitter::onComplete);
    }
}
