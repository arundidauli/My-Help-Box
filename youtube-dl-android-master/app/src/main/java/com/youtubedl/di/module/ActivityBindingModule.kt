package com.youtubedl.di.module

import com.youtubedl.di.ActivityScoped
import com.youtubedl.ui.main.home.MainActivity
import com.youtubedl.di.module.activity.MainModule
import com.youtubedl.ui.main.player.VideoPlayerActivity
import com.youtubedl.di.module.activity.VideoPlayerModule
import com.youtubedl.ui.main.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by cuongpm on 12/6/18.
 */

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [VideoPlayerModule::class])
    internal abstract fun bindVideoPlayerActivity(): VideoPlayerActivity
}