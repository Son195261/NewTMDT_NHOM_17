package nhom17.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nhom17.thuongmaidientu.model.SanPham;
import nhom17.thuongmaidientu.model.UuDai;

@Repository
public interface UuDaiRepository extends JpaRepository<UuDai, Integer> {

	@Query(value = "select * from uudai",nativeQuery = true)
	List<UuDai> findAllUuDai();
	
	@Query(value = "select * from uudai where mauudai = ?1",nativeQuery = true)
	UuDai TimKiemUuDaiTheoMa(int mauudai);
	
	@Transactional
	@Modifying
	@Query(value = "delete from uudai where mauudai = ?1",nativeQuery = true)
	void XoaUuDai(int mauudai);

	@Transactional
	@Modifying
	@Query(value = "update uudai set tenuudai = ?2, sotiengiam = ?3 where mauudai = ?1",nativeQuery = true)
	void CapNhatUuDai(int mauudai, String tenuudai, float sotiengiam);
	
	@Query(value = "select * from uudai where tenuudai like %?1%",nativeQuery = true)
	List<UuDai> findByNameContaining(String tenuudai);
	
}
