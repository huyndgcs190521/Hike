package com.example.cw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button savebtn = (Button) findViewById(R.id.button);
        savebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { saveDetails(); }
        });
    }

    private void saveDetails(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        EditText nohTxt = (EditText) findViewById(R.id.editTextText);
        EditText locationTxt = (EditText) findViewById(R.id.editTextText2);
        EditText dothTxt = (EditText) findViewById(R.id.editTextText3);
        EditText paTxt = (EditText) findViewById(R.id.editTextText4);
        EditText lthTxt = (EditText) findViewById(R.id.editTextText5);
        EditText lodTxt = (EditText) findViewById(R.id.editTextText6);
        EditText dTxt = (EditText) findViewById(R.id.editTextText7);

        String noh = nohTxt.getText().toString();
        String location = locationTxt.getText().toString();
        String doth = dothTxt.getText().toString();
        String pa = paTxt.getText().toString();
        String lth = lthTxt.getText().toString();
        String lod = lodTxt.getText().toString();
        String d = dTxt.getText().toString();

        long personId = dbHelper.insertDetails(noh,location,doth,pa,lth,lod,d);
        Toast.makeText(this, "Person has been created whit id: " + personId,
                Toast.LENGTH_LONG).show();

        Intent i = new Intent(this,DetailsActivity.class);
        startActivity(i);
    }
}