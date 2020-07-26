package nhom17.thuongmaidientu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.NhanVien;
import nhom17.thuongmaidientu.model.SanPham;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

	@Query(value = "select * from nhanvien",nativeQuery = true)
	List<NhanVien> TimTatCaNhanVien();
	
	@Query(value = "select * from nhanvien where manv = ?1",nativeQuery = true)
	NhanVien TimNhanVienTheoMaNhanVien(int manv);
	
	@Query(value = "select * from nhanvien where sodienthoai = ?1 and matkhau = ?2",nativeQuery = true)
	NhanVien TimNhanVienDeDangNhap(String sodienthoai,String matkhau);
	
	@Transactional
	@Modifying
	@Query(value = "delete from nhanvien where manv = ?1",nativeQuery = true)
	void XoaNhanVien(int manv);
	
	@Transactional
	@Modifying
	@Query(value = "insert into nhanvien(tennv,ngaysinh,sodienthoai,diachi,hesoluong) values(?1,?2,?3,?4,?5)",nativeQuery = true)
	void ThemNhanVien(String tennv,Date ngaysinh,String sodienthoai,String diachi,float hesoluong);

	@Transactional
	@Modifying
	@Query(value = "update nhanvien set hesoluong = ?2 where manv = ?1",nativeQuery = true)
	void CapNhatNhanVien(int manv,float hesoluong);
	
	@Transactional
	@Modifying
	@Query(value = "update nhanvien set matkhau = ?2 where manv = ?1",nativeQuery = true)
	void Thaydoimatkhau(int manv,String matkhau);
	
	@Query(value = "select * from nhanvien where tennv like %?1%",nativeQuery = true)
	List<NhanVien> findByNameContaining(String tennhanvien);
	
}
