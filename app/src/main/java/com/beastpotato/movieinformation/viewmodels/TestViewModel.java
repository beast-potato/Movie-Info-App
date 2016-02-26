package com.beastpotato.movieinformation.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.beastpotato.movieinformation.BR;

/**
 * Created by Oleksiy on 2/25/2016.
 */
public class TestViewModel extends BaseObservable {
    private String testString;

    public TestViewModel(String testString) {
        this.testString = testString;
    }

    @Bindable
    public String getTestString() {
        return testString;
    }

    public void setTestString(String string) {
        this.testString = string;
        notifyPropertyChanged(BR.testModel);
    }
}
