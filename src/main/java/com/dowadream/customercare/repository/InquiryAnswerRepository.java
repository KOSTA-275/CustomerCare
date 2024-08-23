package com.dowadream.customercare.repository;

import com.dowadream.customercare.entity.InquiryAnswerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InquiryAnswerRepository extends CrudRepository<InquiryAnswerEntity, Long> {

}
