package com.Ashutosh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ashutosh.entity.coNoticeEntity;

public interface noticeRepo extends JpaRepository<coNoticeEntity,Integer> {
	
	@Query(value = "select * from co_notices where notice_status = :status",nativeQuery = true)
	public List<coNoticeEntity> findNoticesByStatus(@Param("status") String status);
	
	@Query(value ="select * from co_notices where case_num= :caseNum",nativeQuery = true)
	public coNoticeEntity findBycaseNum(Integer caseNum);

}
