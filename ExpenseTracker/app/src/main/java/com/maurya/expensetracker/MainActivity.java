package com.maurya.expensetracker;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    public final static String EXTRA_MESSAGE = "com.maurya.expensetracker.MESSAGE";
    private static ArrayList<Double> expenseList;
//    private ArrayAdapter expenseArrayAdapter;

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseList = new ArrayList<Double>();

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
    }

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int i) {
            if (i == 0) {
                return new ExpensesSectionFragment();
            } else {
                return new ReportSectionFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Expenses";
            } else {
                return "Report";
            }
        }
    }

    public static class ExpensesSectionFragment extends android.support.v4.app.Fragment {

//        private ArrayList<Double> expenseList;
        private ArrayAdapter expenseArrayAdapter;
        private EditText textBox;
        private Button submitButton;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_section_expenses, container, false);
            submitButton = (Button) rootView.findViewById(R.id.submit_expense);

            submitButton
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            registerMoneySpent(rootView);
                        }
                    });

            ListView listView = (ListView) rootView.findViewById(R.id.list_view);
            expenseArrayAdapter = new ArrayAdapter<Double>(getContext(), R.layout.activity_list_view, expenseList);
            listView.setAdapter(expenseArrayAdapter);

            textBox = (EditText) rootView.findViewById(R.id.edit_money_spent);
            textBox.setOnEditorActionListener(new EditText.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        submitButton.performClick();
                        return true;
                    }
                    return false;
                }
            });

            textBox.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable arg0) {
                    enableSubmitIfReady();
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
            return rootView;
        }

        private void enableSubmitIfReady() {
            boolean isReady = textBox.getText().toString().length() > 0;
            submitButton.setEnabled(isReady);
        }

        public void registerMoneySpent(View view) {
            EditText editText = (EditText) view.findViewById(R.id.edit_money_spent);
            String expenseStr = editText.getText().toString();
            editText.setText("");

            double expense = Double.parseDouble(expenseStr);
            if(expense > 0) {
                expenseList.add(expense);
                expenseArrayAdapter.notifyDataSetChanged();
            }
        }

//        public void viewReport(View view) {
//            Intent intent = new Intent(getContext(), ViewReportActivity.class);
//
//            double totalExpenditure = 0;
//            for (double expense : expenseList) {
//                totalExpenditure += expense;
//            }
//            String message = Double.toString(totalExpenditure);
//
//            intent.putExtra(EXTRA_MESSAGE, message);
//            startActivity(intent);
//        }
    }

    public static class ReportSectionFragment extends android.support.v4.app.Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_section_report, container, false);

            double totalExpenditure = 0;
            for (double expense : expenseList) {
                totalExpenditure += expense;
            }
            String message = Double.toString(totalExpenditure);
            TextView textView = new TextView(getContext());
            textView.setTextSize(40);
            textView.setText(message);

            ViewGroup layout = (ViewGroup) rootView.findViewById(R.id.fragment_section_report);
            layout.addView(textView);

            return rootView;
        }
    }
}
