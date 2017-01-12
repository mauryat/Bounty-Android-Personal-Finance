package com.maurya.expensetracker;

//import android.databinding.BaseObservable;
//import android.databinding.Bindable;
//
import java.util.ArrayList;

/**
 * Created by maurya on 1/13/17.
 */
public class Data /*extends BaseObservable*/ {
    public ArrayList<Double> expenseList;
    public double income;

    public Data() {
        expenseList = new ArrayList<Double>();
    }

    void setIncome(double income) {
        this.income = income;
//        notifyPropertyChanged(BR.income);
    }

//    @Bindable
    public String getIncome() {
        return String.valueOf(income);
    }
}
