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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by maurya on 1/12/17.
 */
public class ReportTab extends Fragment {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.report_tab, container, false);

        PieChart pieChart = (PieChart) rootView.findViewById(R.id.chart);

        double totalExpenditure = 0;
        for(double expense: MainActivity.expenseList) {
            totalExpenditure += expense;
        }

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry((float) MainActivity.income, 0));
        entries.add(new Entry((float) totalExpenditure, 0));

        PieDataSet dataset = new PieDataSet(entries, "Expenditure vs Income");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Income");
        labels.add("Expenditure");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setDescription("Description");
        pieChart.setData(data);

        pieChart.animateY(5000);

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
