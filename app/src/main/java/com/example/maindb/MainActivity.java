package com.example.maindb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
Button btn1,btn2;
EditText usrname,age;
ListView viewdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrname = findViewById(R.id.username);
        age = findViewById(R.id.age);
        btn1 = findViewById(R.id.btnadd);
        btn2 = findViewById(R.id.btnview);
        viewdata = findViewById(R.id.showdata);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel1;
                try {
                     customerModel1 = new CustomerModel(usrname.getText().toString(),age.getText().toString());
                    Toast.makeText(getApplicationContext(), customerModel1.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error has been genrated", Toast.LENGTH_SHORT).show();
                    customerModel1 = new CustomerModel("****","***");
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
               boolean succ =  dataBaseHelper.addOne(customerModel1);
                Toast.makeText(MainActivity.this, "DONE ", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<CustomerModel> everyone = dataBaseHelper.getEveryone();
                ArrayAdapter customerArrayAdater = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1,everyone);
                viewdata.setAdapter(customerArrayAdater);
               // Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        viewdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel customerModel = (CustomerModel) parent.getItemAtPosition(position);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                dataBaseHelper.deleteOne(customerModel);
                Toast.makeText(getApplicationContext(),"DELETED",Toast.LENGTH_SHORT).show();
            }
        });
    }
}