package nhom17.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import nhom17.thuongmaidientu.model.QuanTriVien;

@Repository
public interface QuanTriVienRepository extends JpaRepository<QuanTriVien, Integer> {

	@Query(value = "select * from quantrivien where ten = ?1 and matkhau = ?2",nativeQuery = true)
	QuanTriVien findQuanTriVien(String ten,String matkhau);
	
}
