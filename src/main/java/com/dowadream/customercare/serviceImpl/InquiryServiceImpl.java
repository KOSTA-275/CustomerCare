package com.dowadream.customercare.serviceImpl;

import com.dowadream.customercare.entity.InquiryAnswerEntity;
import com.dowadream.customercare.entity.InquiryEntity;
import com.dowadream.customercare.repository.InquiryAnswerRepository;
import com.dowadream.customercare.repository.InquiryRepository;
import com.dowadream.customercare.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;
    @Autowired
    private InquiryAnswerRepository inquiryAnswerRepository;

    @Transactional
    @Override   // 문의 추가
    public void svcInquiryInsert(InquiryEntity inqVO) {
        inquiryRepository.save(inqVO);
    }

    @Transactional
    @Override   // 문의 리스트 조회
    public List<InquiryEntity> svcInquiryList() {
        return (List<InquiryEntity>) inquiryRepository.findAllByOrderByInqSeq();
    }

    @Transactional
    @Override   // 문의 리스트 조회
    public InquiryEntity svcInquiryDetail(InquiryEntity inqVO) {
        return inquiryRepository.findById(inqVO.getInqSeq()).get();
    }

    @Transactional
    @Override   // 문의 수정
    public void svcInquiryUpdate(InquiryEntity inqVO) {
        inquiryRepository.save(inqVO);
    }

    @Transactional
    @Override   // 문의 삭제
    public void svcInquiryDelete(InquiryEntity inqVO) {
        inquiryRepository.deleteById(inqVO.getInqSeq());
    }




    @Transactional
    @Override
    public String svcInquiryAnswerInsert(InquiryAnswerEntity ansVO, Long inqSeq) {
        // inquiryRepository에서 inqSeq를 사용해 InquiryEntity 조회
        Optional<InquiryEntity> optionalInqVO = inquiryRepository.findById(inqSeq);

        // InquiryEntity가 존재하지 않을 경우 "notFoundError" 반환
        // Optional은 조회결과가 없는 예외를 처리해줌.

        if (optionalInqVO.isEmpty()) {
            return "notFoundError";
        }

        InquiryEntity inqVO = optionalInqVO.get();

        // inqAnswer가 null인 경우에만 답변을 추가
        if (inqVO.getInqAnswer() == null) {
            ansVO.setInquiry(inqVO);
            inquiryAnswerRepository.save(ansVO);

            return "ok";
        } else {
            // 이미 답변이 존재하는 경우 처리 (필요에 따라 로그를 남기거나 예외를 던질 수 있음)
            return "alreadyExistError";
        }
    }

}
