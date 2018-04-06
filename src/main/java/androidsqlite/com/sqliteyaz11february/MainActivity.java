package androidsqlite.com.sqliteyaz11february;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listview_allContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview_allContacts=(ListView)findViewById(R.id.list_viewallcontact);

        listview_allContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(MainActivity.this, ContactDetailsActivity.class);

                Bundle b=new Bundle();

                b.putInt("position",i);

                intent.putExtras(b);
                startActivity(intent);

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_new_contact:

                startActivity(new Intent(MainActivity.this,ContactDetailsActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        DBHelper db=new DBHelper(this);

        ArrayList<String> names=new ArrayList<>();


            for (int i = 0; i < db.getAllContact().size(); i++)

                names.add(db.getAllContact().get(i).getName());


        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        listview_allContacts.setAdapter(adapter);
        super.onResume();
    }


}
