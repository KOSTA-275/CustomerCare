package com.dowadream.customercare.repository;

import com.dowadream.customercare.entity.InquiryAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InquiryAnswerRepository extends JpaRepository<InquiryAnswerEntity, Long> {

    List<InquiryAnswerEntity> findAllByUserSeq(Long userSeq);
    // 현재 userSeq가 유저와 직원 똑같은 컬럼을 사용하고 있다. 그렇기 때문에 inquiry_answer 있는 직원의 userSeq를 가지고
    // inqSeq를 뽑아내는 작업이 필요하다. 그것을 위한 쿼리.
}
