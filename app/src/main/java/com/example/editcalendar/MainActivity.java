package com.example.editcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText title, location, description;
    Button addEvent;
    Switch checkAllDay;
    Boolean allDay;


    // Set listener for all day button
    class allDaySwitchListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                allDay = true;
            } else {
                allDay = false;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        title = findViewById(R.id.inputTitle);
        location = findViewById(R.id.inputLocation);
        description = findViewById(R.id.inputDesc);
        addEvent = findViewById(R.id.addEvent);
        checkAllDay = findViewById(R.id.checkAllDay);


        checkAllDay.setOnCheckedChangeListener(new allDaySwitchListener());


        // Add a new event by click addEvent button
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If every field is filled
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !description.getText().toString().isEmpty()) {

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, description.getText().toString());
                    // intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    intent.putExtra(CalendarContract.Events.ALL_DAY, allDay);
                    intent.putExtra(Intent.EXTRA_EMAIL, "test@gmail.com, test2@gmail.com, test3@gmail.com");

                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "There is no app that can support this action", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // If there is an empty field
                    Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}