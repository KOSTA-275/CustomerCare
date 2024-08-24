package com.dowadream.customercare.service;

import com.dowadream.customercare.entity.InquiryAnswerEntity;
import com.dowadream.customercare.entity.InquiryEntity;

import java.util.List;

public interface InquiryService {
    void svcInquiryInsert(InquiryEntity inqVO);                                     // 문의 추가
    List<InquiryEntity> svcInquiryList();                                           // 문의 리스트 조회
    InquiryEntity svcInquiryDetail(InquiryEntity inqVO);                            // 문의 디테일 조회
    void svcInquiryUpdate(InquiryEntity inqVO);                                     // 문의 수정
    void svcInquiryDelete(InquiryEntity inqVO);                                     // 문의 삭제

    String svcInquiryAnswerInsert(InquiryAnswerEntity ansVO, Long inqSeq);          // 문의 답변 추가
    List<InquiryEntity> svcInquiryNoAnswerList();                                   // 답변 안한 문의 리스트 조회
    List<InquiryEntity> svcInquiryYesAnswerList();                                  // 답변 된 모든 문의 리스트 조회
    List<InquiryEntity> svcInquiryMyAnswerList(InquiryAnswerEntity ansVO);          // 내가 답한 문의 리스트 조회
    String svcInquiryAnswerUpdate(InquiryAnswerEntity ansVO, Long inqSeq);          // 문의 답변 수정
}
