package androidsqlite.com.sqliteyaz11february;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactDetailsActivity extends AppCompatActivity {

    private EditText editText_name;
    private EditText editText_Email;
    private EditText editText_phone;
    private Button button_save;
    private Button button_update;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        editText_name=(EditText)findViewById(R.id.editText);
        editText_Email=(EditText)findViewById(R.id.editText2);
        editText_phone=(EditText)findViewById(R.id.editText3);
        button_save=(Button)findViewById(R.id.button_SAVE);

        button_update=(Button)findViewById(R.id.btn_update);
        Bundle b2=getIntent().getExtras();

        if (b2!=null){

            int position=b2.getInt("position");
            DBHelper db=new DBHelper(this);
            contact=db.getAllContact().get(position);

            //editText_name.setText(db.getAllContact().get(position).getName());

            editText_name.setText(contact.getName());
            editText_Email.setText(contact.getEmail());
            editText_phone.setText(contact.getPhone());
            button_save.setVisibility(View.INVISIBLE);
            //button_update.setVisibility(View.INVISIBLE);
        }
        if (b2==null){
            button_update.setVisibility(View.INVISIBLE);
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    saveContact();

            }
        });


        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    updateContact();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Sorry!",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.new_delete_menu:
                deleteContact();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //for save contact

    private void saveContact(){

        DBHelper db=new DBHelper(this);
        Contact newcontact=new Contact();
        newcontact.setName(editText_name.getText().toString());
        newcontact.setEmail(editText_Email.getText().toString());
        newcontact.setPhone(editText_phone.getText().toString());

        if (editText_name.length()!=0 && editText_phone.length()!=0 && editText_Email.length()!=0){
            db.insertContact(newcontact);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Insert all field",Toast.LENGTH_SHORT).show();
        }



    }

    ///for update contact

    private void updateContact(){

        DBHelper db=new DBHelper(this);
        Contact newcontact=new Contact();
        newcontact.setId(contact.getId());
        newcontact.setName(editText_name.getText().toString());
        newcontact.setEmail(editText_Email.getText().toString());
        newcontact.setPhone(editText_phone.getText().toString());

        db.updateContact(newcontact);
        finish();


    }

    //for delete contact;

    private void deleteContact(){

        DBHelper db=new DBHelper(this);

        db.deleteContact(contact);

        finish();

    }





}
