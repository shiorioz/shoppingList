package com.example.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar_main;
    private RecyclerView recyclerView;
    private ImageButton addButton, deleteButton;

    private ArrayList<Data> memoList = new ArrayList<>();   // 入力したメモを格納する配列

    private static final int REQUEST_CODE = 1;      // MainActivityに遷移するとき使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_main = findViewById(R.id.toolbar_main);
        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);

        setSupportActionBar(toolbar_main);
        getSupportActionBar().setTitle("　買い物リスト");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter mainAdapter = new MainAdapter(memoList);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration
                (this, DividerItemDecoration.VERTICAL));    // 区切り線設定

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // ＋ボタンクリック時、詳細入力画面に移行する
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        // 削除ボタンクリック時
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainAdapter ma = (MainAdapter) recyclerView.getAdapter();
                ArrayList<Data> dataList = null;
                if (ma != null) { dataList = ma.getDataList(); }

                for (int i = memoList.size() - 1; i >= 0; i--) {
                    boolean delflg = dataList.get(i).isDelflg();
                    if (delflg) {
                        memoList.remove(i);
                    }
                }

                RecyclerView.Adapter mainAdapter = new MainAdapter(memoList);
                recyclerView.setAdapter(mainAdapter);
            }
        });
    }

    // ソートボタンの設定
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // RecyclerViewを分類別でソートする
        memoList = sortArray(memoList);
        RecyclerView.Adapter mainAdapter = new MainAdapter(memoList);
        recyclerView.setAdapter(mainAdapter);

        return true;
    }


    // ほかのActivityから戻ってきたとき
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            // DetailActivityから戻ってきた場合
            case (REQUEST_CODE): {
                if (resultCode == RESULT_OK) {
                    Data memoData = new Data();
                    memoData.setTextMemo(data.getStringExtra("textStr"));
                    String genreStr = data.getStringExtra("genreStr");
                    memoData.setGenre(genreStr);
                    memoList.add(memoData);

                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));

                    RecyclerView.Adapter mainAdapter = new MainAdapter(memoList);
                    recyclerView.setAdapter(mainAdapter);
                }
            }
        }
    }


    private ArrayList<Data> sortArray(ArrayList<Data> memoList) {
        Comparator<Data> comparator = new Comparator<Data>() {
            @Override
            public int compare(Data t1, Data t2) {
                String[] genreStr = {"野菜", "肉類", "魚介類", "乳製品", "調味料", "日用品", "飲み物",
                        "お菓子", "冷凍食品"};
                int o1 = 0, o2 = 0;
                for (int i = 0; i < genreStr.length; i++) {
                    if (t1.getGenre().equals(genreStr[i])) {
                        o1 = i;
                    }
                    if (t2.getGenre().equals(genreStr[i])) {
                        o2 = i;
                    }
                }

                if (o1 == o2) {
                    return 0;
                } else if (o1 < o2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Collections.sort(memoList, comparator);
        return memoList;
    }

}

