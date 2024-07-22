package com.putri.exploremalang.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Wisata implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("gambar_url")
    private String gambarUrl;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("jam_buka")
    private String jamBuka;

    @SerializedName("harga")
    private String harga;

    @SerializedName("lokasi")
    private String lokasi;

    // Constructor
    public Wisata() {
        // Default constructor
    }

    // Constructor to create from Parcel
    protected Wisata(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        gambarUrl = in.readString();
        kategori = in.readString();
        deskripsi = in.readString();
        jamBuka = in.readString();
        harga = in.readString();
        lokasi = in.readString();
    }

    // Parcelable.Creator
    public static final Creator<Wisata> CREATOR = new Creator<Wisata>() {
        @Override
        public Wisata createFromParcel(Parcel in) {
            return new Wisata(in);
        }

        @Override
        public Wisata[] newArray(int size) {
            return new Wisata[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(gambarUrl);
        dest.writeString(kategori);
        dest.writeString(deskripsi);
        dest.writeString(jamBuka);
        dest.writeString(harga);
        dest.writeString(lokasi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJamBuka() {
        return jamBuka;
    }

    public void setJamBuka(String jamBuka) {
        this.jamBuka = jamBuka;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
