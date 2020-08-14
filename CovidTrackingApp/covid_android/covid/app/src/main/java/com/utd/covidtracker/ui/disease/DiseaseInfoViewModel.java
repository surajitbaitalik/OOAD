package com.utd.covidtracker.ui.disease;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiseaseInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DiseaseInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}