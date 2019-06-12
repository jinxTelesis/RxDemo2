package com.example.myapplication;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private final static String TAG ="myApp";
    private String greeting = "Hello From RxJava";
    private Observable<String> myObserable;
    private DisposableObserver<String> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObserable = Observable.just(greeting);

        compositeDisposable.add(
                myObserable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver())
        );
    }

    private DisposableObserver getObserver(){
        myObserver=new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, "on Next invoked" + s);

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "on Next invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "on Next invoked");

            }
        };
        return myObserver;
    }
}
