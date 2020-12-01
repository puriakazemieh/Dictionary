package com.kazemieh.www.dictionary;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataBaseOpenHelper dataBaseOpenHelper;
    List<DataModel> dataModels;





    /////////
/*


                  توی  کلاس مین اکتیویتی 2 من چند تا کد رو کامنت کردم که باید از کامنت در بیاد
                  همینطور تو لایه همون اکتیویتی ایمیج ویو برای نمایش ستاره رو کامنت کردم که باز باید در بیارمش
                  باید ی اکتیوتیی هم بسازم برای نمایش ستاره دار ها که اصلا نساختمش هنوز



 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et_serch = findViewById(R.id.et_MainActivity_serch);

        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        dataBaseOpenHelper.createDataBase();

        RecyclerView recyclerView = findViewById(R.id.rv_MainActivity_showlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        dataModels = showlistbysql();
        final Adapter adapter = new Adapter(this, dataModels);
        recyclerView.setAdapter(adapter);

        et_serch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataModels.clear();
                dataModels.addAll(searchlistbyjava(s.toString()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public List<DataModel> showlistbysql() {
        List<DataModel> dataModels = new ArrayList<>();
        //  select *from dictionary where fav = '1'
        Cursor cursor = dataBaseOpenHelper.getWritableDatabase().rawQuery("select * from dictionary ", null);
        while (cursor.moveToNext()) {
            String en = cursor.getString(cursor.getColumnIndex("en"));
            String fa = cursor.getString(cursor.getColumnIndex("fa"));
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            dataModels.add(new DataModel(en, fa,id));
        }
        return dataModels;
    }

    public List<DataModel> showlistbyjava() {
        List<DataModel> dataModels = new ArrayList<>();
        Cursor cursor = dataBaseOpenHelper.getWritableDatabase().query("dictionary", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String en = cursor.getString(cursor.getColumnIndex("en"));
            String fa = cursor.getString(cursor.getColumnIndex("fa"));

            int id=cursor.getInt(cursor.getColumnIndex("id"));
            dataModels.add(new DataModel(en, fa,id));
        }
        return dataModels;
    }

    public List<DataModel> searchlistbysql(String search) {
        List<DataModel> dataModels = new ArrayList<>();
        Cursor cursor = dataBaseOpenHelper.getWritableDatabase().rawQuery("select * from dictionary where en like'%" + search + "%' or fa like '%" + search + "%' ", null);
        while (cursor.moveToNext()) {
            String en = cursor.getString(cursor.getColumnIndex("en"));
            String fa = cursor.getString(cursor.getColumnIndex("fa"));
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            dataModels.add(new DataModel(en, fa,id));
        }
        return dataModels;
    }

    public List<DataModel>  searchlistbyjava(String  search){
        List<DataModel> dataModels=new ArrayList<>();
        Cursor cursor=dataBaseOpenHelper.getWritableDatabase().query("dictionary",null," en like? or fa like?",new String[]{"%"+search+"%","%"+search+"%"},
                null,null,null,null);
        while (cursor.moveToNext()){
            String en = cursor.getString(cursor.getColumnIndex("en"));
            String fa = cursor.getString(cursor.getColumnIndex("fa"));
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            dataModels.add(new DataModel(en, fa,id));
        }
        return dataModels;
    }


}