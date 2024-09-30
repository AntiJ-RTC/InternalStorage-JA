package com.example.internalstorage;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textmsg = (EditText) findViewById(R.id.editText1);
    }

    public void WriteBtn(View v) {
        try {
            FileOutputStream fileOut = openFileOutput("mymessage.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);

            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ReadBtn(View v){
        try {
            FileInputStream fileIn = openFileInput("mymessage.txt");
            InputStreamReader inputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = inputRead.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
            }
            inputRead.close();

            textmsg.setText(s);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}