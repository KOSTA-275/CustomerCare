package com.dowadream.customercare.repository;

import com.dowadream.customercare.entity.InqFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InqFileRepository extends JpaRepository<InqFileEntity, Long> {

}