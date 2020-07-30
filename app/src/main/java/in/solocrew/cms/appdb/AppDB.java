package in.solocrew.cms.appdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "app_db";
    private final static int version = 1;

    public AppDB(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE shop_tb(id INTEGER PRIMARY KEY, name TEXT,address TEXT, " +
                "location TEXT, contact_number TEXT)");

        db.execSQL("CREATE TABLE details_tb(id INTEGER PRIMARY KEY, shop_id INTEGER NOT NULL," +
                        "gross_weight REAL, no_of_boxes INTEGER,weight_of_empty_box REAL," +
                "no_of_empty_box_returned INTEGER,no_of_box_yet_to_return INTEGER, rate REAL," +
                " date TEXT, CONSTRAINT fk_shop_tb FOREIGN KEY(shop_id) REFERENCES shop_tb(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public long insertShops(Shops shops){
        ContentValues values = new ContentValues();
        values.put("name",shops.getName());
        values.put("address",shops.getAddress());
        values.put("location",shops.getLocation());
        values.put("contact_number",shops.getContact_number());

        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert("shop_tb",null, values);
    }

    public long updateShops(Shops shops){
        ContentValues values = new ContentValues();
        values.put("name",shops.getName());
        values.put("address",shops.getAddress());
        values.put("location",shops.getLocation());
        values.put("contact_number",shops.getContact_number());

        SQLiteDatabase db = this.getWritableDatabase();
        return db.update("shop_tb",values,"id=?",new String[]{shops.getId()+""});
    }

    public List<Shops> getAllShops(){
        String Q = "SELECT * FROM shop_tb ORDER BY name ASC";

        List<Shops>list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Q, null);
        if(cursor.moveToFirst()){
            do {
                Shops shops = new Shops();
                shops.setId(cursor.getInt(0));
                shops.setName(cursor.getString(1));
                shops.setAddress(cursor.getString(2));
                shops.setLocation(cursor.getString(3));
                shops.setContact_number(cursor.getString(4));

                list.add(shops);
            }while (cursor.moveToNext());
        }

        return list;
    }

    public List<String> getAllShopNames(){
        String Q = "SELECT name FROM shop_tb ORDER BY name ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Q, null);

        List<String> names = new ArrayList<>();
        names.add("Select");
        if(cursor.moveToFirst()){
            do {
                names.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        return names;
    }

    public Shops getShopById(int id){
        String Q = "SELECT * FROM shop_tb WHERE id = ?";

        SQLiteDatabase db = this.getReadableDatabase();

        Shops shops = new Shops();
        Cursor cursor = db.rawQuery(Q, new String[]{id+""});
        if(cursor.moveToFirst()){
            shops.setId(cursor.getInt(0));
            shops.setName(cursor.getString(1));
            shops.setAddress(cursor.getString(2));
            shops.setLocation(cursor.getString(3));
            shops.setContact_number(cursor.getString(4));
        }

        return shops;
    }

    //DETAILS
    public long insertDetails(Details details){
        ContentValues values = new ContentValues();
        values.put("shop_id",details.getShop_id());
        values.put("gross_weight",details.getGross_weight());
        values.put("no_of_boxes",details.getNo_of_boxes());
        values.put("weight_of_empty_box",details.getWeight_of_empty_box());
        values.put("no_of_empty_box_returned",details.getNo_of_empty_box_returned());
        values.put("no_of_box_yet_to_return",details.getNo_of_box_yet_to_return());
        values.put("rate",details.getRate());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        values.put("date",formatter.format(date));

        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert("details_tb",null, values);
    }

    public long updateDetails(Details details){
        ContentValues values = new ContentValues();
        values.put("shop_id",details.getShop_id());
        values.put("gross_weight",details.getGross_weight());
        values.put("no_of_boxes",details.getNo_of_boxes());
        values.put("weight_of_empty_box",details.getWeight_of_empty_box());
        values.put("no_of_empty_box_returned",details.getNo_of_empty_box_returned());
        values.put("no_of_box_yet_to_return",details.getNo_of_box_yet_to_return());
        values.put("rate",details.getRate());

        SQLiteDatabase db = this.getWritableDatabase();
        return db.update("details_tb", values,"id=?",new String[]{details.getId()+""});
    }

    public List<Details> getAllDetails(){
        String Q = "SELECT d.*,s.name from details_tb as d INNER JOIN shop_tb as s on d.shop_id " +
                "= s.id ORDER BY d.date DESC";

        List<Details>list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Q, null);
        if(cursor.moveToFirst()){
            do {
                Details details = new Details();
                details.setId(cursor.getInt(0));
                details.setShop_id(cursor.getInt(1));
                details.setGross_weight(cursor.getDouble(2));
                details.setNo_of_boxes(cursor.getInt(3));
                details.setWeight_of_empty_box(cursor.getInt(4));
                details.setNo_of_empty_box_returned(cursor.getInt(5));
                details.setNo_of_box_yet_to_return(cursor.getInt(6));
                details.setRate(cursor.getDouble(7));
                details.setDate(cursor.getString(8));
                details.setShop_name(cursor.getString(9));

                list.add(details);
            }while (cursor.moveToNext());
        }

        return list;
    }

    public Details getDetailById(int id){
        String Q = "SELECT d.*,s.name from details_tb as d INNER JOIN shop_tb as s on " +
                "d.shop_id = s.id where d.id = ?";

        Details details = new Details();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Q, new String[]{id+""});
        if(cursor.moveToFirst()){
            details.setId(cursor.getInt(0));
            details.setShop_id(cursor.getInt(1));
            details.setGross_weight(cursor.getDouble(2));
            details.setNo_of_boxes(cursor.getInt(3));
            details.setWeight_of_empty_box(cursor.getInt(4));
            details.setNo_of_empty_box_returned(cursor.getInt(5));
            details.setNo_of_box_yet_to_return(cursor.getInt(6));
            details.setRate(cursor.getDouble(7));
            details.setShop_name(cursor.getString(8));
        }

        return details;
    }

}

