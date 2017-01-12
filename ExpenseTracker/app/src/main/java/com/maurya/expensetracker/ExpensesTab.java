package com.maurya.expensetracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * Created by maurya on 1/12/17.
 */
public class ExpensesTab extends Fragment {

    private ArrayAdapter expenseArrayAdapter;
    private EditText textBox;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.expenses_tab, container, false);

        submitButton = (Button) rootView.findViewById(R.id.submit_expense);

        submitButton
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerMoneySpent(rootView);
                    }
                });

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        expenseArrayAdapter = new ArrayAdapter<Double>(getContext(), R.layout.activity_list_view, MainActivity.expenseList);
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

        if(expenseStr.length() > 0) {
            double expense = Double.parseDouble(expenseStr);
            if (expense > 0) {
                MainActivity.expenseList.add(expense);
                expenseArrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
