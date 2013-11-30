package id.web.sinaryuda.android.tutorial.databasesqlite;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DatabaseHandler db = new DatabaseHandler(this);

		/**
		 * Operasi CRUD
		 **/

		// Menambahkan data buku
		Log.d("Insert: ", "Menambahkan data");
		db.addBuku(new Buku("Yosef Murya", "Pemrograman Android Black Box"));
		db.addBuku(new Buku("Wahana Komputer", "Android for Online Business"));
		db.addBuku(new Buku("Dodit Suprianto & Rini Agustina, S.Kom, M.Pd",
				"Pemrograman Aplikasi Android"));
		db.addBuku(new Buku("Tim EMS", "Android All In One"));

		// Baca Semua data buku
		Log.d("Reading: ", "Baca semua data buku");
		ArrayList<Buku> buku = db.getSemuaBuku();

		for (Buku bk : buku) {
			String logBuku = "Id: " + bk.getID() + " ,Penulis: "
					+ bk.getPenulis() + " , Judul: " + bk.getJudul();
			// tampilkan data dalam Log
			Log.d("DataBuku: ", logBuku);
		}
		
		// update data buku
		Log.d("Updating: ", "Update data buku");
		db.updateBuku(new Buku( 4, "Jasmadi", "Program Terhebat Android"));
		
		Log.d("Reading: ", "Baca satu data buku");
		Buku satubuku = db.getBuku(4);
		String logBuku = "Id: " + satubuku.getID() + " ,Penulis: "
		+ satubuku.getPenulis() + " , Judul: " + satubuku.getJudul();
		// tampilkan data dalam Log
		Log.d("DataBuku: ", logBuku);
		
		ArrayList<Buku> getbuku = db.getSemuaBuku();

		for (Buku bk : getbuku) {
			db.deleteContact(bk);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
