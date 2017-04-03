package com.example.rr.mydatabase;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button save,view,delete,update;
    EditText what,id;
    MySqlLite mySqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save=(Button)findViewById(R.id.Save);
        view=(Button)findViewById(R.id.View);
        delete=(Button)findViewById(R.id.Delete);
        update=(Button)findViewById(R.id.Update);
        what=(EditText)findViewById(R.id.what);
        id=(EditText)findViewById(R.id.Id);
        mySqlLite=new MySqlLite(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean chk=mySqlLite.addToTable(id.getText().toString(),what.getText().toString());
                if(chk==true)
                Toast.makeText(MainActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Inserted Unsuccessfull", Toast.LENGTH_SHORT).show();

            } 
        });


    }

    public void viewData (View v){
        Cursor result = mySqlLite.displayData();
        if(result.getCount()==0){
            showMsg("Error Found","No data found");
            return;

        }
        StringBuffer buffer= new StringBuffer();
        result.moveToFirst();
        do{
            buffer.append("ID: "+result.getString(0)+"\n");
            buffer.append("WORK: "+result.getString(1)+"\n");
        }while (result.moveToNext());

        showMsg("Data",buffer.toString());
    }



    public void showMsg(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.show();
    }
}
