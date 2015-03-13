package com.example.kaew.listofdream;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaew.listofdream.Standard.*;
import com.example.kaew.listofdream.Standard.Enum;

import java.sql.SQLException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    DatabaseHandler db;
    List<DreamEntity> lstDreamEntity;
    ListView lvDream;
    int indexSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDream = (ListView)findViewById(R.id.lvDream);
        db = new DatabaseHandler(getApplicationContext());
        try {
            lstDreamEntity = db.selectAllDream();
            if(lstDreamEntity.size()==0){
                initialData();
                lstDreamEntity = db.selectAllDream();
            }
        }catch(SQLException e)
        {
            Log.d("Connection", "Error connection : Cannot select data");
            Toast.makeText(getApplicationContext(),"Error connection : Cannot select data", Toast.LENGTH_SHORT).show();
        }

        super.registerForContextMenu(lvDream);
        lvDream.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indexSelected = position;
                Log.d("Test", "Pos : " + String.valueOf(position));
                return false;
            }
        });
        populate();
    }
    private void populate()
    {
        ArrayAdapter<DreamEntity> dreamEntityAdapter = new ListAdapter();
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
            Toast.makeText(getApplicationContext(),"Error connection : Cannot insert data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = super.getMenuInflater();
        inflater.inflate(R.menu.menu_list_view, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_edit :
                DreamEntity entity = lstDreamEntity.get(indexSelected);
                Toast.makeText(getApplicationContext(),entity.get_dream(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete :
                break;
        }
        return super.onContextItemSelected(item);
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
        switch (id)
        {
            case R.id.action_add :
                Intent intent = new Intent(this, InsertForm.class);
                DreamEntity entity = new DreamEntity();
                entity.set_formMode(Enum.FORM_MODE.ADD);
                intent.putExtra("Parameter", entity);
                startActivity(intent);
                break;
            case R.id.action_about:
                break;
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
                view = getLayoutInflater().inflate(R.layout.listview_tamplate, parent, false);
            }

            DreamEntity entity = super.getItem(position);
            if(entity!=null)
            {
                ((TextView)view.findViewById(R.id.tvDream)).setText(entity.get_dream());
                ((TextView)view.findViewById(R.id.tvComment)).setText(entity.get_comment());
//                ((CheckBox)view.findViewById(R.id.cbAchieve)).setChecked(entity.is_achieve());
                ((TextView)view.findViewById(R.id.tvNo)).setText(String.valueOf(position+1));

//                ((TextView)view.findViewById(R.id.txtName)).setText(entity.get_dream());
//                ((TextView)view.findViewById(R.id.txtEmail)).setText(entity.get_dream());
//                ((TextView)view.findViewById(R.id.txtPhoneNumber)).setText(entity.get_dream());
            }

            return view;
        }
    }
}
