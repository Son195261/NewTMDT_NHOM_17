package nhom17.thuongmaidientu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.QuanLyNhanVien;

@Repository
public interface QuanLyNhanVienRepository extends JpaRepository<QuanLyNhanVien, Integer> {

	@Query(value = "select * from quanlynhanvien where manv = ?1",nativeQuery = true)
	List<QuanLyNhanVien> DanhSachDonHangCuaNhanVien(int manv);

	@Transactional
	@Modifying
	@Query(value = "insert into quanlynhanvien(manv,madonhang,ngaytiepnhan) values(?1,?2,?3)",nativeQuery = true)
	void ThemQuanLyNhanVien(int manv,int madonhang,Date ngaytiepnhan);
	
	
	
}
