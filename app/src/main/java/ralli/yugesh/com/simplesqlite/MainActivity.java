package ralli.yugesh.com.simplesqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String mName, mNumber;
    DataHandler store, retrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_store = (Button) findViewById(R.id.store);
        Button btn_retrieve = (Button) findViewById(R.id.retrieve);

        final EditText editName = (EditText) findViewById(R.id.name);
        final EditText editNumber = (EditText) findViewById(R.id.number);

        btn_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mName = editName.getText().toString();
                mNumber = editNumber.getText().toString();
                store = new DataHandler(getApplicationContext());
                store.insertData(mName,mNumber);
                Toast.makeText(getApplicationContext(),"Data stored in SQliteDB",Toast.LENGTH_SHORT).show();
                editName.setText("");
                editNumber.setText("");
            }
        });

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieve = new DataHandler(getApplicationContext());
                String rName="";
                String rNumber="";
                Cursor c1 = retrieve.send();
                if(c1.moveToFirst()){
                    while(c1.moveToNext()){
                        rName = c1.getString(0);
                        rNumber = c1.getString(1);
                    }
                }
                editName.setText(rName);
                editNumber.setText(rNumber);
                Toast.makeText(getApplicationContext(), "Data retrieved from SQliteDB", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
