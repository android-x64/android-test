package com.test.admin.testproj.tests.libs.rx_android_examples;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.animation.LayoutAnimationController;

import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.libs.rx_android_examples.example1.User;
import com.test.admin.testproj.tests.libs.rx_android_examples.example1.UserManager;
import com.test.admin.testproj.tests.libs.rx_android_examples.example1.UserManagerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created on 14.08.2015.
 */
public class RxExamplesTestActivity extends Activity {

    private CompositeDisposable mDisposables = new CompositeDisposable();
    private UserManager mUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_examples_test);

        mUserManager = new UserManagerImpl();
        mDisposables.add(mUserManager.getUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        // do some stuff with user in UI thread
                    }

                    @Override
                    public void onError(Throwable e) { /* crash or show */ }

                    @Override
                    public void onComplete() { /* ignored */ }
                }));


        mDisposables.add(mUserManager.setName("Joe")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        // success! re-enable editing
                    }

                    @Override
                    public void onError(Throwable e) { /* retry or show */ }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Unsubscribe to free references.
        if (mDisposables != null) {
            mDisposables.clear();
            mDisposables = null;
        }
    }

    public static Observable<Intent> from(final IntentFilter intentFilter, final Context ctx) {
        final Context appContext = ctx.getApplicationContext();
        return Observable.create(emitter -> {
            BroadcastReceiver receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    emitter.onNext(intent);
                }
            };
            emitter.setDisposable(new Disposable() {
                boolean isDisposed;
                @Override
                public void dispose() {
                    if (!isDisposed) {
                        appContext.unregisterReceiver(receiver);
                        isDisposed = true;
                    }
                }

                @Override
                public boolean isDisposed() {
                    return isDisposed;
                }
            });
            appContext.registerReceiver(receiver, intentFilter);
        });

    }
}
