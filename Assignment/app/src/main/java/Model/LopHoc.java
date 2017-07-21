package Model;

public class LopHoc {
	int id;
	String MaLop,TenLop;
	
	public LopHoc(){}
	public LopHoc(String MaLop,String TenLop){
		this.MaLop = MaLop;
		this.TenLop=TenLop;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
	public String getTenLop() {
		return TenLop;
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	@Override
	public String toString() {
		return "LopHoc [MaLop=" + MaLop + ", TenLop=" + TenLop + "]";
	}
	

}
