package com.example.inputdata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnDate, btnSubmit;
    EditText edtDate, edtFirstName, edtLastName, edtAddress, edtEmail;
    RadioButton btnMale, btnFemale;
    CheckBox btnCheck;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInfo();
            }
        });

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);



    }

    @SuppressLint("ResourceType")
    void checkInfo() {
        boolean check = true;

        String firstName = edtFirstName.getText().toString().trim();
        String lastName = edtLastName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (firstName.isEmpty()) {
            check = false;
            edtFirstName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
        }
        if (lastName.isEmpty()) {
            check = false;
            edtLastName.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
        }
        if (address.isEmpty()) {
            check = false;
            edtAddress.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
        }
        if (email.isEmpty()) {
            check = false;
            edtEmail.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
        }
        if (!btnMale.isSelected() && !btnFemale.isSelected()) {
            check = false;
            btnMale.setButtonTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_red_light)));
            btnFemale.setButtonTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_red_light)));
        }
        if (!btnCheck.isChecked()) {
            check = false;
            btnCheck.setButtonTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_red_light)));
        }

        if (check) {
            Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    void buttonSelectDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                edtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(this,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }

    void mapping() {
        btnDate = findViewById(R.id.btnDate);
        edtDate = findViewById(R.id.edtDate);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnMale = findViewById(R.id.btnMale);
        btnFemale = findViewById(R.id.btnFemale);
        btnCheck = findViewById(R.id.btnCheck);
    }
}