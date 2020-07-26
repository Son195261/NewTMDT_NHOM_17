package nhom17.thuongmaidientu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.QuanLyUuDai;

@Repository
public interface QuanLyUuDaiRepository extends JpaRepository<QuanLyUuDai, Integer> {

	@Query(value = "select * from quanlyuudai where makh = ?1",nativeQuery = true)
	List<QuanLyUuDai> findAllUudai_KH(int makh);
	
	@Transactional
	@Modifying
	@Query(value = "insert into quanlyuudai(makh,mauudai,ngaybatdau,ngayketthuc) values(?1,?2,?3,?4)",nativeQuery = true)
	void ChenPhanPhoiUuDai(int makh,int mauudai,Date ngaybatdau,Date ngayketthuc);
	
}
