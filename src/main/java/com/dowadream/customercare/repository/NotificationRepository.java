package com.dowadream.customercare.repository;

import com.dowadream.customercare.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity>  findAllByOrderByNotiSeq();
    // orderBy 사용하기 위해서 임의 지정 findBy~였다면 바로 OrderBy칼럼명 하면 되지만 findAll이기 때문에 ByOrderBy ~ 헤줘야됨.
}
