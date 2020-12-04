package com.kazemieh.www.dictionary;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    DataBaseOpenHelper dataBaseOpenHelper;
    int id;
    boolean bfav = false;
    String ssfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dataBaseOpenHelper = new DataBaseOpenHelper(this);

        TextView tv_en = findViewById(R.id.tv_MainActivity2_en);
        TextView tv_fa = findViewById(R.id.tv_MainActivity2_fa);
        TextView tv_des = findViewById(R.id.tv_MainActivity2_des);
        final ImageView iv_fav = findViewById(R.id.iv_MainActivity2_fav);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
            if (id==-1){

                tv_en.setText("منابع");
                tv_fa.setText("اطلاعات از این سایت برداشته شده است" +
                        "bigdata-ir.com");
            }
            Cursor cursor = dataBaseOpenHelper.getWritableDatabase().rawQuery("select * from dictionary where id =" + id, null);
            while (cursor.moveToNext()) {
                String en = cursor.getString(cursor.getColumnIndex("en"));
                String fa = cursor.getString(cursor.getColumnIndex("fa"));
                String des = cursor.getString(cursor.getColumnIndex("des"));
                ssfav=cursor.getString(cursor.getColumnIndex("fav"));

                tv_en.setText(en);
                tv_fa.setText(fa);
          //      tv_des.setText(des);

              /*  if (ssfav.equals("1")) {
                    iv_fav.setImageResource(R.drawable.ic_round_star_24);
                }else {
                    iv_fav.setImageResource(R.drawable.ic_round_star_border_24);
                }

                iv_fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ssfav.equals("1")) {
                            updatebyjava("0");
                            ssfav="0";
                            iv_fav.setImageResource(R.drawable.ic_round_star_border_24);

                        } else {
                            updatebyjava("1");
                            ssfav="1";
                            iv_fav.setImageResource(R.drawable.ic_round_star_24);
                        }
                    }
                });
*/
            }
        }


    }

    public void updatebysql(String sfav) {
        //    update dictionary set fav = 'sfav' where id = 'id'
        dataBaseOpenHelper.getWritableDatabase().execSQL("update dictionary set fav = '" + sfav + "' where id ='" + id + "' ");
    }

    public void updatebyjava(String ssfav){
        ContentValues contentValues=new ContentValues();
        contentValues.put("fav",ssfav);
        String [] ss={id+""};
        dataBaseOpenHelper.getWritableDatabase().update("dictionary",contentValues,"id"+"=?",ss);
    }
}