package com.example.kaew.listofdream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kaew.listofdream.Standard.*;
import com.example.kaew.listofdream.Standard.Enum;

import java.sql.SQLException;

/**
 * Created by Kaew on 3/13/2015.
 */
public class InsertForm extends Activity {
    private DatabaseHandler db;
    private Context context;
    private CheckBox cbAchieve;
    private EditText etDream, etComment;
    private TextView tvNo;
    private static final int MODE_ADD = 0, MODE_EDIT = 1;
    private int id;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        super.setContentView(R.layout.insert_page);

        context = getApplicationContext();
        db = new DatabaseHandler(context);

        tvNo = (TextView)findViewById(R.id.tvNo);
        cbAchieve = (CheckBox)findViewById(R.id.cbAchieve);
        etDream = (EditText)findViewById(R.id.etDream);
        etComment = (EditText)findViewById(R.id.etComment);
        final Button btnSave = (Button)findViewById(R.id.btnSave);
        final Button btnCancel = (Button)findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DreamEntity entity = new DreamEntity(0, cbAchieve.isChecked(), etDream.getText().toString(), etComment.getText().toString());
                entity.set_formMode(Enum.FORM_MODE.ADD);
                try {
                    db.insertDream(entity);
                }catch (SQLException e)
                {

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                startNextMatchingActivity(intent);
            }
        });

        etDream.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               btnSave.setEnabled(etDream.getText().toString().trim()!="");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
