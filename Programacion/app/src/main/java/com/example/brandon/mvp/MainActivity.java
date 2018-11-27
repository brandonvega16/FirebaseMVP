package com.example.brandon.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.brandon.mvp.Presenter.PresenterConsult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    DatabaseReference mDatabase;
    PresenterConsult consult = new PresenterConsult(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Device");

        Button btnMostrar = (Button)findViewById(R.id.Consultar);

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consult.getDatosFirebase(mDatabase);

            }
        });

    }
}
