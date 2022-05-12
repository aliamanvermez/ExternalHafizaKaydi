package com.example.externalhafizakaydi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonWrite, buttonDelete, buttonRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = (EditText) findViewById(R.id.editTextInput);

        buttonWrite = (Button) findViewById(R.id.buttonWrite);
        buttonRead = (Button) findViewById(R.id.buttonRead);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                externalWrite();



            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                externalRead();

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                externalDelete();

            }
        });




        }

        public void externalWrite () {

        try {

                File filePath = Environment.getExternalStorageDirectory();
                File file = new File(filePath,"myFile.txt");
                if (!file.exists()){
                    file.createNewFile(); }

            FileWriter fw = new FileWriter(file);
            BufferedWriter printer = new BufferedWriter(fw);
            printer.write(editTextInput.getText().toString());

            printer.flush(); //hata olması durumunda son anda bir veriyi çekmek için
            printer.close(); //printer close
            fw.close();    //fileWriter close

            editTextInput.setText(""); // metin yazdırıldığında plain texti boşaltır

        }

        catch (IOException e) {
                e.printStackTrace();
            }


    }

    public void externalRead () {

        try {

            File filePath = Environment.getExternalStorageDirectory();
            File file = new File(filePath,"myFile.txt");


            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();

            String line = "";
            while ((line=reader.readLine()) != null){
                sb.append(line+"\n");
            }

            reader.close();
            editTextInput.setText(sb.toString());
        }

        catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void externalDelete () {


        File filePath = Environment.getExternalStorageDirectory();
        File file = new File(filePath, "myFile.txt");

        file.delete();


    }

}