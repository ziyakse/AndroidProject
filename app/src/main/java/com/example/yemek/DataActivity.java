package com.example.yemek;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class DataActivity extends AppCompatActivity {
    private DBManager dbManager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        dbManager =new DBManager(this);

    }

    public void buSave(View view){
        EditText etUserName=(EditText) findViewById(R.id.etUserName);
        EditText etComment=(EditText) findViewById(R.id.etComment);


        ContentValues values= new ContentValues();
        values.put(DBManager.ColUserName,etUserName.getText().toString());
        values.put(DBManager.ColComment,etComment.getText().toString());

        long id= dbManager.Insert(values);
        if (id>0)
            Toast.makeText(getApplicationContext(), "Data is added and user id:"+id,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"connot insert",Toast.LENGTH_LONG).show();
    }

    ArrayList<AdapterItems> listnewsData = new ArrayList<AdapterItems>();
    MyCustomAdapter myadapter;
    public void buLoad(View view){

        listnewsData.clear();

        Cursor cursor=dbManager.query(null,null,null,DBManager.ColUserName);

        if (cursor.moveToFirst()){
            String tableData="";
            do {
                /*tableData+=cursor.getString(cursor.getColumnIndex(DBManager.ColUserName))+","+ cursor.getString(cursor.getColumnIndex(DBManager.ColComment)) + "::";*/

                listnewsData.add(new AdapterItems("1",cursor.getString(cursor.getColumnIndex(DBManager.ColUserName)),cursor.getString(cursor.getColumnIndex(DBManager.ColComment))));
            }while (cursor.moveToNext());

            Toast.makeText(getApplicationContext(),tableData,Toast.LENGTH_LONG).show();
        }

        myadapter=new MyCustomAdapter(listnewsData);

        ListView lsNews=(ListView) findViewById(R.id.LVNews);
        lsNews.setAdapter(myadapter);
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdapter;

        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdapter) {
            this.listnewsDataAdapter=listnewsDataAdapter;
        }

        @Override
        public int getCount(){
            return listnewsDataAdapter.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_ticket, null);

            final AdapterItems s = listnewsDataAdapter.get(position);

            TextView tvID=(TextView) myView.findViewById(R.id.tvID);
            tvID.setText(s.ID);

            TextView tvUserName=(TextView) myView.findViewById(R.id.tvUserName);
            tvUserName.setText(s.UserName);

            TextView tvComment=(TextView) myView.findViewById(R.id.tvComment);
            tvComment.setText(s.Comment);
            return myView;
        }
    }
}
