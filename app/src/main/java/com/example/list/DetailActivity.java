package com.example.list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar_detail;
    EditText editTextMemo;
    RadioGroup genreRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar_detail = findViewById(R.id.toolbar_detail);
        editTextMemo = findViewById(R.id.editTextMemo);
        genreRadioGroup = findViewById(R.id.genreRadioGroup);

        // ツールバー
        setSupportActionBar(toolbar_detail);
        getSupportActionBar().setTitle("入力");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 保存ボタン
        Button keepButton = findViewById(R.id.keep_button);
        keepButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String genreStr;

                // 入力テキスト
                String textMemo = editTextMemo.getText().toString();
                boolean textFlg = TextUtils.isEmpty(textMemo);

                // ジャンルボタン入力
                int checkedId = DetailActivity.this.genreRadioGroup.getCheckedRadioButtonId();

                if (textFlg){
                    Toast.makeText(DetailActivity.this,
                            "入力してください", Toast.LENGTH_SHORT).show();
                }else if(checkedId == -1){
                    Toast.makeText(DetailActivity.this,
                            "分類を選択してください", Toast.LENGTH_SHORT).show();
                }else{
                    RadioButton genreButton
                            = (RadioButton)findViewById(genreRadioGroup.getCheckedRadioButtonId());
                    genreStr = (String) genreButton.getText();

                    Intent intent = getIntent();
                    intent.putExtra("textStr", textMemo);
                    intent.putExtra("genreStr", genreStr);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    // 戻るボタン
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
