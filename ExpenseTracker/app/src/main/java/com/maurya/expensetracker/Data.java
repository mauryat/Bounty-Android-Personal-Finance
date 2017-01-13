package com.maurya.expensetracker;

//import android.databinding.BaseObservable;
//import android.databinding.Bindable;
//
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by maurya on 1/13/17.
 */
public class Data extends Observable {
    private ArrayList<Double> expenseList;
    private double income;

    public Data() {
        expenseList = new ArrayList<Double>();
    }

    public void addExpense(double expense) {
        expenseList.add(expense);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Double> getExpenseList() {
        return expenseList;
    }

    public void setIncome(double income) {
        this.income = income;
        setChanged();
        notifyObservers();
//        notifyPropertyChanged(BR.income);
    }

//    @Bindable
    public double getIncome() {
        return income;
    }

    public double totalExpenditure() {
        double totalExpenditure = 0;
        for(double expense: expenseList) {
            totalExpenditure += expense;
        }
        return totalExpenditure;
    }
}
