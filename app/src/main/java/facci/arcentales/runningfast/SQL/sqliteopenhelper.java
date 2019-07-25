package facci.arcentales.runningfast.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import facci.arcentales.runningfast.modelo.TiempoDistacia;

public class sqliteopenhelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BD="correr.bd";
    public static final int VERSION_BD=1;
    public static final String TABLA_TD="CREATE TABLE TIEMPO_DISTANCIA( TIEMPO TEXT, DISTANCIA TEXT)";

    public sqliteopenhelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_TD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS'"+TABLA_TD+"'");
        sqLiteDatabase.execSQL(TABLA_TD);

    }

    public void agregarTD (String tiempo, String distancia){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO TIEMPO_DISTANCIA VALUES ('"+tiempo+"','"+distancia+"')");
            bd.close();
        }
    }

    public List<TiempoDistacia> mostrarTD(){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM TIEMPO_DISTANCIA",null);
        List<TiempoDistacia>TD = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                TD.add(new TiempoDistacia(cursor.getString(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return TD;
    }

   /* public void buscarProductos (modelo_productos producto, String codigo ){
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM PRODUCTOS WHERE CODIGO = '"+codigo+"'",null);
        if (cursor.moveToFirst()){
            do {
                producto.setNombre(cursor.getString(1));
                producto.setPrecio(cursor.getString(2));
            }while (cursor.moveToNext());
        }
    }

    public void actualizarProductos (String codigo, String nombre, String precio){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("UPDATE PRODUCTOS SET NOMBRE = '"+nombre+"', PRECIO = '"+precio+"' WHERE CODIGO = '"+codigo+"'");
            bd.close();
        }
    }*/

    public void eliminarHistorial (){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd!=null){
            bd.execSQL("DELETE FROM TIEMPO_DISTANCIA");
            bd.close();
        }
    }

}
