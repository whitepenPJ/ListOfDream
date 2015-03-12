package com.example.kaew.listofdream;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    DatabaseHandler db;
    List<DreamEntity> lstDreamEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(getApplicationContext());
        try {
            lstDreamEntity = db.selectAllDream();
            if(lstDreamEntity.size()==0){
                initialData();
                lstDreamEntity = db.selectAllDream();
            }
            populate();
        }catch(SQLException e)
        {
            Log.d("Connection", "Error connection : Cannot select data");
            Toast.makeText(getApplicationContext(),"Error connection : Cannot select data", Toast.LENGTH_SHORT);
        }


    }
    private void populate()
    {
        ArrayAdapter<DreamEntity> dreamEntityAdapter = new ListAdapter();
        ListView lvDream = (ListView)findViewById(R.id.lvDream);
        lvDream.setAdapter(dreamEntityAdapter);
    }
    private void initialData()
    {
        DreamEntity entity1 = new DreamEntity(0, false, "Dream 1", "");
        DreamEntity entity2 = new DreamEntity(0, false, "Dream 2", "");
        DreamEntity entity3 = new DreamEntity(0, false, "Dream 3", "");
        DreamEntity entity4 = new DreamEntity(0, false, "Dream 4", "");
        try {
            db.insertDream(entity1);
            db.insertDream(entity2);
            db.insertDream(entity3);
            db.insertDream(entity4);
        }catch(SQLException e)
        {
            Log.d("Connection", "Error connection : Cannot insert data");
            Toast.makeText(getApplicationContext(),"Error connection : Cannot insert data", Toast.LENGTH_SHORT);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ListAdapter extends ArrayAdapter<DreamEntity>
    {
        public ListAdapter()
        {
            super(getApplicationContext(),R.layout.listview_tamplate, lstDreamEntity);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = convertView;
            if(view==null)
            {
                LayoutInflater vi = LayoutInflater.from(super.getContext());
                view = vi.inflate(R.layout.listview_tamplate, null);
            }

            DreamEntity entity = super.getItem(position);
            if(entity!=null)
            {
                ((TextView)view.findViewById(R.id.tvDream)).setText(entity.getDream());
                ((TextView)view.findViewById(R.id.tvComment)).setText(entity.getComment());
                ((CheckBox)view.findViewById(R.id.cbAchieve)).setChecked(entity.getAchieve());
                ((TextView)view.findViewById(R.id.tvNo)).setText(String.valueOf(position+1));
            }

            return view;
        }
    }
}
