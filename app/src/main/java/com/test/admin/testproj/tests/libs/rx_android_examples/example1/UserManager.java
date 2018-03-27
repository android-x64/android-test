package com.test.admin.testproj.tests.libs.rx_android_examples.example1;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by sergey on 2/23/18.
 */

public interface UserManager {
    Observable<User> getUser();
    Completable setName(String name);
    Completable setAge(int age);
}
