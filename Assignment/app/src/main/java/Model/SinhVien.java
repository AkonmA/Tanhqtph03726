package Model;

public class SinhVien {
	long id;
	String MaSinhVien,MaLop,TenSinhVien;
	
	
	public SinhVien(){}
	public SinhVien(String Malop,String MaSinhVien,String TenSinhVien){
		this.MaLop=Malop;
		this.MaSinhVien = MaSinhVien;
		this.TenSinhVien=TenSinhVien;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMaSinhVien() {
		return MaSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		MaSinhVien = maSinhVien;
	}

	public String getMaLop() {
		return MaLop;
	}

	public void setMaLop(String maLop) {
		MaLop = maLop;
	}

	public String getTenSinhVien() {
		return TenSinhVien;
	}

	public void setTenSinhVien(String tenSinhVien) {
		TenSinhVien = tenSinhVien;
	}

	
	@Override
	public String toString() {
		return "SinhVien [MaSinhVien=" + MaSinhVien + ", MaLop=" + MaLop
				+ ", TenSinhVien=" + TenSinhVien + "]";
	}
	
}
