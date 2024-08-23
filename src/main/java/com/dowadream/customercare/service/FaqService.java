package com.dowadream.customercare.service;

import com.dowadream.customercare.entity.FaqEntity;

import java.util.List;

public interface FaqService {
    void svcFaqInsert(FaqEntity faqVO);                                      // 자주 묻는 질문 추가
    List<FaqEntity> svcFaqList();                                            // 자주 묻는 질문 리스트 조회
    FaqEntity svcFaqDetail(FaqEntity faqVO);                                 // 자주 묻는 질문 디테일 조회
    void svcFaqUpdate(FaqEntity faqVO);                                      // 자주 묻는 질문 수정
    void svcFaqDelete(FaqEntity faqVO);                                      // 자주 묻는 질문 삭제
}
