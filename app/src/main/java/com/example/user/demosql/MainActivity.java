package com.example.user.demosql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_AddEmployee;
    EditText username,mobile,address;
    String Username,Mobile,Address;
    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext(),"EmployeeDb",null,1);

        Temp.setDatabaseHandler(databaseHandler);
        databaseHandler = Temp.getDatabaseHandler();
        getId();
        final DatabaseHandler finalDatabaseHandler = databaseHandler;
        btn_AddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                 Mobile = mobile.getText().toString();
                Address = address.getText().toString();

                if (Validate())
                {
                    EmployeeModel employeeModel = new EmployeeModel();
                    employeeModel.setUsername(Username);
                    employeeModel.setMobile(Mobile);
                    employeeModel.setAddress(Address);

                    int i = finalDatabaseHandler.insertdata(employeeModel);
                    if (i==1)
                    {
                        Toast.makeText(getApplicationContext(),"Employee Added Successfully",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }

    public void getId()
    {
        btn_AddEmployee = (Button)findViewById(R.id.btnAdduser);
        username = (EditText)findViewById(R.id.editTextUsername);
        mobile = (EditText)findViewById(R.id.editTextMobile);
        address = (EditText)findViewById(R.id.editTextAddress);
    }
    public Boolean Validate()
    {
        if (Username.isEmpty())
        {
            Toast.makeText(this,"Enter Username",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (Mobile.isEmpty())
        {
            Toast.makeText(this,"Enter Mobile",Toast.LENGTH_LONG).show();
            return false;
        }
        else if (Address.isEmpty())
        {
            Toast.makeText(this,"Enter Address",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
