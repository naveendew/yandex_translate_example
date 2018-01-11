package com.autoinfini.stackoverflowproblems;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    TextView tv_hello;
    EditText et_hello;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_hello = findViewById(R.id.et_hello);
        tv_hello = findViewById(R.id.tv_hello);

        dialog = new ProgressDialog(this);

        findViewById(R.id.getText).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                dialog.setMessage("Translating ...");
                dialog.show();
                // TODO: Implement this method
//                String texttotranslate = "hello";
                String texttotranslate = et_hello.getText().toString();
                Log.d("text:", "" + texttotranslate);

                OkhttpHandler myTask = new OkhttpHandler(new AsyncResponse() {

                    @Override
                    public void processFinish(String output) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Log.d("Response from asynctask", (String) output);
                        tv_hello.setText((String) output);
                    }


                });
                myTask.execute(texttotranslate, "ru-hi");

            }
        });
    }


}
