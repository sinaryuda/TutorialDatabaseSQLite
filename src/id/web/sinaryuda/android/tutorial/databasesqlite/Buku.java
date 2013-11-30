package id.web.sinaryuda.android.tutorial.databasesqlite;

public class Buku {
	// variabel privat
	int _id;
	String _penulis;
	String _judul;

	// Empty constructor
	public Buku() {
	}

	// constructor
	public Buku(int id, String penulis, String judul) {
		this._id = id;
		this._penulis = penulis;
		this._judul = judul;
	}

	// constructor
	public Buku(String penulis, String judul) {
		this._penulis = penulis;
		this._judul = judul;
	}

	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int id) {
		this._id = id;
	}

	// getting penulis
	public String getPenulis() {
		return this._penulis;
	}

	// setting penulis
	public void setPenulis(String penulis) {
		this._penulis = penulis;
	}

	// getting judul
	public String getJudul() {
		return this._judul;
	}

	// setting judul
	public void setJudul(String judul) {
		this._judul = judul;
	}
}
