package vodaassistant.haikaroselab.com.vodaassistant.contentStore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import vodaassistant.haikaroselab.com.vodaassistant.Pojos.Group;
import vodaassistant.haikaroselab.com.vodaassistant.Pojos.MemberItem;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME ="Infopasser_db";
    private static final int DATABASE_VERSION =5;
    private static final String TABLE_NAME="Members";



    public DatabaseHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROUPS_TABLE = "CREATE TABLE IF NOT EXISTS Groups" +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT)";
        String CREATE_MEMBERS_TABLE = "CREATE TABLE IF NOT EXISTS Members" +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Phone TEXT, Groups TEXT)";
        String CREATE_NOTIFICATION_TABLE = "CREATE TABLE IF NOT EXISTS Notifications" +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, SMS TEXT, Group TEXT, Time DATETIME" +
                " DEFAULT CURRENT_TIMESTAMP)";
        String CREATE_PAYINFO_TABLE = "CREATE TABLE IF NOT EXISTS PayInfo" +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, Payer TEXT, " +
                "Amount TEXT, Date TEXT, Receipt TEXT, Operator TEXT)";

        db.execSQL(CREATE_MEMBERS_TABLE);
        db.execSQL(CREATE_GROUPS_TABLE);
        db.execSQL(CREATE_PAYINFO_TABLE);
//       db.execSQL(CREATE_NOTIFICATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql="DROP TABLE IF EXISTS Members";
        String sql1="DROP TABLE IF EXISTS PayInfo";
        String sql2="DROP TABLE IF EXISTS Groups";
        String sql3="DROP TABLE IF EXISTS Notifications";
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        onCreate(db);
    }

    public void addMember(MemberItem item){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("Name", item.getName().toLowerCase());
        values.put("Phone", item.getPhone());
        values.put("Groups", item.getGroup().toLowerCase());

        db.insert("Members", null, values);

    }
    public void addGroup(Group item){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("Name", item.getName().toLowerCase());

        db.insert("Groups", null, values);

    }



    public ArrayList<MemberItem> getAllMembers(){

        String querry="SELECT * FROM Members";
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<MemberItem> list=new ArrayList<>();

        Cursor cursor=db.rawQuery(querry,null);

        while(cursor.moveToNext()){

            MemberItem member=new MemberItem();
            member.setId(cursor.getInt(0));
            member.setName(cursor.getString(1));
            member.setPhone(cursor.getString(2));
            member.setGroup(cursor.getString(3));

            list.add(member);
        }

        return list;
    }

    public ArrayList<Group> getAllGroupList(){

        String querry="SELECT * FROM Groups";
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<Group> list=new ArrayList<>();

        Cursor cursor=db.rawQuery(querry,null);

        while(cursor.moveToNext()){

            Group group=new Group();
            group.setId(cursor.getInt(0));
            group.setName(cursor.getString(1));

            list.add(group);
        }

        return list;
    }

    public String[] getAllGroup(){

        String querry="SELECT * FROM Members";
        SQLiteDatabase db=getWritableDatabase();
        ArrayList<String> list=new ArrayList<>();

        Cursor cursor=db.rawQuery(querry,null);

        list.add("All");
        while(cursor.moveToNext()){

            String value=null;

            value=cursor.getString(3);

            list.add(value);
        }

        String array[]=new String[list.size()];
        array=list.toArray(array);

        Set<String> set=new LinkedHashSet<String>(Arrays.asList(array));
        String strings[]=set.toArray(new String[set.size()]);

        return strings;
    }
    public ArrayList<MemberItem> getGroupMember(String group){

        String query="SELECT * FROM Members where Groups ='"+group+"'";
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<MemberItem> list=new ArrayList<>();

        Cursor cursor=db.rawQuery(query,null);
        MemberItem member=null;

       if(cursor!=null){
           while(cursor.moveToNext()){

               if(cursor.getString(3).equalsIgnoreCase(group)){
                   member=new MemberItem();
                   member.setId(cursor.getInt(0));
                   member.setName(cursor.getString(1));
                   member.setPhone(cursor.getString(2));
                   member.setGroup(cursor.getString(3));

               }

               list.add(member);
           }
       }

        return list;
    }

    public void RemoveMember(MemberItem item){
         SQLiteDatabase db=this.getWritableDatabase();
         String sql="DELETE FROM Members where id="+item.getId()+"";
         db.execSQL(sql);
    }

    public void RemoveAllMembers(){
        SQLiteDatabase db=getWritableDatabase();
        String sql="DELETE * FROM Members";
        db.execSQL(sql);
    }

    public void removeSingleMember(String phone){
        SQLiteDatabase db=getWritableDatabase();
        String sql="DELETE FROM Members where Phone='"+phone+"'";
        db.execSQL(sql);
    }

    public void removeSingleGroup(String name){
        SQLiteDatabase db=getWritableDatabase();
        String sql="DELETE FROM Groups where name='"+name+"'";
        db.execSQL(sql);
    }
    public int countRows(){

        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM Members";

        Cursor cursor=db.rawQuery(sql, null);
        int count=cursor.getCount();
        cursor.close();

        return count;

    }

    public boolean findPayer(String phone,Context context){
        SQLiteDatabase db=getWritableDatabase();
        String sql="SELECT * FROM PayInfo where  Payer=?";

        Cursor cursor=db.rawQuery(sql,new String[]{phone});
        if(cursor.getCount()>0){

            return true;

        }else{
            return false;
        }

    }


}
