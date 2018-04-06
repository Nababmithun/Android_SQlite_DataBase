package androidsqlite.com.sqliteyaz11february;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Yazdani on 2/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE="my_db";
    private static final String TABLE="contact";
    private static final String NAME="name";
    private static final String EMAIL="email";
    private static final String PHONE="phone";
    private static final String TAG="DBHelper";



    DBHelper(Context context){

        super(context,DATABASE,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String s=("create table if not exists "+TABLE+" (id integer primary key, "+NAME+" text, "+EMAIL+" text, "+PHONE+" text)");
        db.execSQL(s);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //insert data

    public boolean insertContact(Contact contact){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        try {

            contentValues.put(NAME,contact.getName());
            contentValues.put(EMAIL,contact.getEmail());
            contentValues.put(PHONE,contact.getPhone());
            db.insert(TABLE,null,contentValues);

            return true;
        }catch (Exception e){

            Log.e(TAG,"insertContact"+e.getMessage());
            return false;
        }

    }


    //for update data.

    public boolean updateContact(Contact contact){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        try {

            contentValues.put(NAME,contact.getName());
            contentValues.put(EMAIL,contact.getEmail());
            contentValues.put(PHONE,contact.getPhone());

            db.update(TABLE, contentValues, "id = ?",new String[] {String.valueOf(contact.getId())});


            return true;
        }catch (Exception e){

            Log.e(TAG,"updateContact"+e.getMessage());
            return false;
        }


    }

    //for delete contact

    public boolean deleteContact(Contact contact){

        SQLiteDatabase db=this.getWritableDatabase();
        try {

            db.delete(TABLE,"id = ?",new String[] {String.valueOf(contact.getId())});

            return true;
        }catch (Exception e){

            Log.e(TAG,"deleteContact"+e.getMessage());

            return false;
        }


    }



//for show data in list view

    public ArrayList<Contact>getAllContact(){

        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Contact> contacts=new ArrayList<Contact>();

        try {
            Cursor cur=db.rawQuery("select * from "+TABLE, null);
            cur.moveToFirst();

            while (cur.isAfterLast()==false){

                Contact c=new Contact();

                c.setId(cur.getInt(cur.getColumnIndex("id")));
                c.setName(cur.getString(cur.getColumnIndex(NAME)));
                c.setEmail(cur.getString(cur.getColumnIndex(EMAIL)));
                c.setPhone(cur.getString(cur.getColumnIndex(PHONE)));

                contacts.add(c);
                cur.moveToNext();
            }

            return contacts;

        }catch (Exception e){

            return null;
        }

    }

}
