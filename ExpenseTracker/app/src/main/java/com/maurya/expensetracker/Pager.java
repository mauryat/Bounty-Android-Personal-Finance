package com.maurya.expensetracker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by maurya on 1/12/17.
 */
public class Pager extends FragmentPagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                IncomeTab incomeTab = new IncomeTab();
                return incomeTab;
            case 1:
                ExpensesTab expensesTab = new ExpensesTab();
                return expensesTab;
            case 2:
                ReportTab reportTab = new ReportTab();
                return reportTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Income";
            case 1: return "Expenses";
            case 2: return "Report";
            default:
                return null;
        }
    }
}
