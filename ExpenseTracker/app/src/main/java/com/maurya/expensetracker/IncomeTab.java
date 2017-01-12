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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by maurya on 1/12/17.
 */
public class IncomeTab extends Fragment {

    private View rootView;
    private EditText textBox;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.income_tab, container, false);

        submitButton = (Button) rootView.findViewById(R.id.submit_income);

        submitButton
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerIncome(rootView);
                    }
                });

        textBox = (EditText) rootView.findViewById(R.id.edit_income);
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

    public void registerIncome(View view) {
        EditText editText = (EditText) view.findViewById(R.id.edit_income);
        String incomeStr = editText.getText().toString();
        editText.setText("");

        if(incomeStr.length() > 0) {
            double income = Double.parseDouble(incomeStr);
            if (income > 0) {
                MainActivity.income = income;
                TextView textView = (TextView) rootView.findViewById(R.id.text_income);
                textView.setText(incomeStr);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && rootView != null) {
            TextView textView = (TextView) rootView.findViewById(R.id.text_income);
            textView.setText(String.valueOf(MainActivity.income));
        }
    }
}
