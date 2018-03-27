package com.test.admin.testproj.tests.libs.dagger2.with_dagger2_android;

import com.test.admin.testproj.tests.libs.dagger2.Dagger2TestActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sergey on 2/23/18.
 */
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = Dagger2TestModule.class)
    abstract Dagger2TestActivity bindMainActivity();

    // Add other activities
//    @ContributesAndroidInjector(modules = {DetailActivityModule.class, DetailFragmentProvider.class})
//    abstract DetailActivity bindDetailActivity();
}
