package id.web.sinaryuda.android.tutorial.databasesqlite;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "koleksibuku";

	// Contacts table name
	private static final String TABLE_BUKU = "buku";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_JUDUL = "judul";
	private static final String KEY_PENULIS = "penulis";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Buat Tabel
	@Override
	public void onCreate(SQLiteDatabase db) {
		String BUAT_TABEL_BUKU = "CREATE TABLE " + TABLE_BUKU + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_JUDUL + " TEXT,"
				+ KEY_PENULIS + " TEXT" + ")";
		db.execSQL(BUAT_TABEL_BUKU);
	}

	// Perbarui database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop tabel lama jika sudah ada
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUKU);

		// Create tables again
		onCreate(db);
	}

	/**
	 * OPERASI CRUD(Create, Read, Update, Delete)
	 */

	// Menambahkan data buku
	public void addBuku(Buku buku) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PENULIS, buku.getJudul()); // Buku Penulis
		values.put(KEY_JUDUL, buku.getPenulis()); // Buku Judul

		// memasukkan data
		db.insert(TABLE_BUKU, null, values);
		db.close(); // Menutup koneksi database
	}

	// Mengambil satu data buku
	public Buku getBuku(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_BUKU, new String[] { KEY_ID,
				KEY_PENULIS, KEY_JUDUL }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Buku buku = new Buku(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return buku
		return buku;
	}

	// mengambil semua data buku
	public ArrayList<Buku> getSemuaBuku() {
		ArrayList<Buku> listBuku = new ArrayList<Buku>();
		// Select semua data
		String selectQuery = "SELECT  * FROM " + TABLE_BUKU;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// Perulangan semua data untuk dimasukkan kedalam list
		if (cursor.moveToFirst()) {
			do {
				Buku buku = new Buku();
				buku.setID(Integer.parseInt(cursor.getString(0)));
				buku.setPenulis(cursor.getString(1));
				buku.setJudul(cursor.getString(2));
				// Menambahkan data buku ke dalam list
				listBuku.add(buku);
			} while (cursor.moveToNext());
		}

		// return listBuku
		return listBuku;
	}

	// mengambil jumlah data buku
	public int getBukuCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BUKU;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

	// Mengubah data buku
	public int updateBuku(Buku buku) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PENULIS, buku.getPenulis());
		values.put(KEY_JUDUL, buku.getJudul());

		// mengubah data
		return db.update(TABLE_BUKU, values, KEY_ID + " = ?",
				new String[] { String.valueOf(buku.getID()) });
	}

	// Menghapus data buku
	public void deleteContact(Buku buku) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_BUKU, KEY_ID + " = ?",
				new String[] { String.valueOf(buku.getID()) });
		db.close();
	}
}