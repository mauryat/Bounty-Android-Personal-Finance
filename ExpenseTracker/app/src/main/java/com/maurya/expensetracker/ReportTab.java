package com.maurya.expensetracker;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by maurya on 1/12/17.
 */
public class ReportTab extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.report_tab, container, false);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser) {
            double totalExpenditure = 0;
            for (double expense : MainActivity.expenseList) {
                totalExpenditure += expense;
            }
            String message = Double.toString(totalExpenditure);
            TextView textView = (TextView) rootView.findViewById(R.id.total_expenditure);
            textView.setTextSize(40);
            textView.setText(message);
        }
    }
}
