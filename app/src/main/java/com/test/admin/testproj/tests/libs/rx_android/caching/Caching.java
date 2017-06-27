package com.test.admin.testproj.tests.libs.rx_android.caching;

import android.util.Log;

import com.test.admin.testproj.tests.libs.rx_android.models.Beer;
import com.test.admin.testproj.tests.libs.rx_android.utils.Network;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * ReplaySubject - re-emits all. It emits all the items of the source Observable, regardless of when the subscriber
 * subscribes. ReplaySubject remembers all everything that you emitted to it. Example: Here, if a student entered late
 * into the classroom, he wants to listen from the beginning. So, here we will use Replay to achieve this.
 *
 * BehaviorSubject - re-emits last item. It emits the most recently emitted item and all the subsequent items of the source
 * Observable when an observer subscribes to it. Example: Here, if a student entered late into the classroom, he wants
 * to listen the most recent things(not from the beginning) being taught by the professor so that he gets the idea
 * of the context. So, here we will use Behavior.
 *
 * PublishSubject - re-emits none. It emits all the subsequent items of the source Observable at the time of subscription.
 * PublishSubject is more like your traditional event bus. It remembers nothing,so every time you send something to it,
 * it’s forgotten and of course, all your current Subscribers get this event, but if something else in the future you
 * subscribe to it, there is no information yet. Example: Here, if a student entered late into the classroom, he just
 * wants to listen from that point of time when he entered the classroom. So, Publish will be the best for this use-case.
 *
 * Async Subject - It only emits the last value of the source Observable(and only the last value). It doesn't emit anything
 * until the sequence completes. Its use is to emit a single value and immediately complete. Example: Here, if
 * a student entered at any point of time into the classroom, and he wants to listen only about the last thing
 * (and only the last thing) being taught. So, here we will use Async.
 *
 * LIFECYCLE MANAGEMENT
 * Keep Subject or cache()'s stream in
 * - singleton (Repository)
 * - retained fragment
 * - nonConfigurationInstance
 *
 */
public class Caching {

    /**
     * Here we have two Subscriptions, normally without the cache, this would make two network calls,
     * but since we applied cache operator (replay), we only have one network call and the same value will be sent
     * to both Subscriptions.
     * Cache is implemented in Rx as the "replay" operator plus an auto connect. Replays accept arguments that can say,
     * “how long do we want to cache this?” A cache is there indefinitely, and here we may want a 10-minute cache.
     * And after 10 minutes, it will lose this value, so you would have a new Subscription to the same Observable.
     * It will say, “well I no longer have a value I have to make a new network request.”
     */
    public static void cachExample_1() {
        Observable<Beer> cached = Network.getBeerAsObservable(8)
                .replay(10, TimeUnit.MINUTES).autoConnect();

        cached.subscribe(beer -> Log.e("WWW", "WWW beer=" + beer));
        cached.subscribe(beer -> Log.e("WWW", "WWW beer=" + beer));
    }
}
