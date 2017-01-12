package com.maurya.expensetracker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.maurya.expensetracker.MESSAGE";
    private ArrayList<Double> expenseList;
    private ArrayAdapter expenseArrayAdapter;
    private EditText textBox;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseList = new ArrayList<Double>();
        ListView listView = (ListView) findViewById(R.id.list_view);
        expenseArrayAdapter = new ArrayAdapter<Double>(this, R.layout.activity_list_view, expenseList);
        listView.setAdapter(expenseArrayAdapter);

        textBox = (EditText) findViewById(R.id.edit_money_spent);
        submitButton = (Button) findViewById(R.id.submit_expense);
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
    }

    private void enableSubmitIfReady() {
        boolean isReady = textBox.getText().toString().length() > 0;
        submitButton.setEnabled(isReady);
    }

    public void registerMoneySpent(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_money_spent);
        String expenseStr = editText.getText().toString();
        editText.setText("");

        double expense = Double.parseDouble(expenseStr);
        if(expense > 0) {
            expenseList.add(expense);
            expenseArrayAdapter.notifyDataSetChanged();
        }
    }

    public void viewReport(View view) {
        Intent intent = new Intent(this, ViewReportActivity.class);

        double totalExpenditure = 0;
        for(double expense : expenseList) {
            totalExpenditure += expense;
        }
        String message = Double.toString(totalExpenditure);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
