package com.dowadream.customercare.service;

import com.dowadream.customercare.entity.InquiryAnswerEntity;
import com.dowadream.customercare.entity.InquiryEntity;

import java.util.List;

public interface InquiryService {
    void svcInquiryInsert(InquiryEntity inqVO);                                      // 문의 추가
    List<InquiryEntity> svcInquiryList();                                            // 문의 리스트 조회
    InquiryEntity svcInquiryDetail(InquiryEntity inqVO);                             // 문의 디테일 조회
    void svcInquiryUpdate(InquiryEntity inqVO);                                      // 문의 수정
    void svcInquiryDelete(InquiryEntity inqVO);                                      // 문의 삭제

    String svcInquiryAnswerInsert(InquiryAnswerEntity ansVO, Long inqSeq);           // 문의 답변 추가
}
