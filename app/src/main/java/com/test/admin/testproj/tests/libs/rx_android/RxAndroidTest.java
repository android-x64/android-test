package com.test.admin.testproj.tests.libs.rx_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.test.admin.testproj.App;
import com.test.admin.testproj.R;
import com.test.admin.testproj.tests.libs.rx_android.examples.RxExampleExecutor;
import com.test.admin.testproj.tests.libs.rx_android.models.Beer;
import com.test.admin.testproj.tests.libs.rx_android.utils.Network;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created on 14.08.2015.
 */
public class RxAndroidTest extends Activity {

    @Inject RxExampleExecutor executor;
    private CompositeSubscription mSubscriptions;
    private Observable<List<Beer>> beerCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        ((App)getApplication()).getRxExampleExecutor().inject(this);
        executor.execute();

        setupRxBinding();
        setupBeerCall();
    }

    /**
     * Cache observable.
     * We could use a simple wrapper class that contains multiple directs (observables).
     * Android will contain this during the reconfiguration the activity and we can get it out again
     * in onCreate (method setupBeerCall()).
     */
    @Override
    public Object onRetainNonConfigurationInstance() {
        return beerCall;
    }

    /**
     * Example of caching
     */
    private void setupBeerCall() {
        beerCall = (Observable<List<Beer>>) getLastNonConfigurationInstance();

        if (beerCall == null) {
            beerCall = Network.searchBeer("Piwoteka").cache();
        }

        beerCall.subscribeOn(io())
                .observeOn(mainThread())
                .subscribe(this::showBeers);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Unsubscribe to free references.
        if (mSubscriptions != null) {
            mSubscriptions.clear();
            mSubscriptions = null;
        }
    }

    private void setupRxBinding() {
        Subscription s1 = RxView.clicks(findViewById(R.id.btn_rx_binding_test))
                .scan(0, (integer, click) -> integer + 1) // running count
                .map(i -> Integer.toString(i)) // int to string
                .subscribe(RxTextView.text((TextView) findViewById(R.id.txt_rx_binding_test)));

        //we have a text field, which is used as search input, do we want to launch a new query every time
        // the user enters some text or removes some text? No, typically we don’t care about every individual character,
        // so we want to wait a little bit (1 second in this example) before this user has eventually stopped typing
        // and now we want to you know to do the actual query.
        Subscription s2 = RxTextView.textChanges((TextView) findViewById(R.id.et_rx_binding_test))
                .subscribeOn(mainThread()) // on ui
                .debounce(1, TimeUnit.SECONDS, mainThread()) // delay emission;
                .flatMap(query -> Network.searchBeer(query.toString()))
                .observeOn(mainThread()) // to ui
                .subscribe(this::showBeers);

        mSubscriptions = new CompositeSubscription(s1, s2);
    }

    private void showBeers(List<Beer> beers) {}

    /**
     * Combining UI and Rx
     */
    private void setupSubmitButton() {
        Observable.combineLatest(
                RxCompoundButton.checkedChanges((CompoundButton) findViewById(R.id.switch_agree_rx_binding_test)),
                RxCompoundButton.checkedChanges((CompoundButton) findViewById(R.id.switch_no_comment_rx_binding_test)),
                RxTextView.textChanges((TextView) findViewById(R.id.et_review_rx_binding_test))
                                .map(s -> s.length() > 80),
                        (agreeTerms, noComment, hasReview) -> agreeTerms && (noComment || hasReview))
                        .subscribe(RxView.enabled(findViewById(R.id.btn_submit_rx_binding_test)));
    }
}
