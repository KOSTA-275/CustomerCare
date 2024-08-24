package com.dowadream.customercare.serviceImpl;

import com.dowadream.customercare.entity.InquiryAnswerEntity;
import com.dowadream.customercare.entity.InquiryEntity;
import com.dowadream.customercare.repository.InquiryAnswerRepository;
import com.dowadream.customercare.repository.InquiryRepository;
import com.dowadream.customercare.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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
    @Override   // 모든 문의 리스트 조회
    public List<InquiryEntity> svcInquiryList() {
        return inquiryRepository.findAllByOrderByInqSeq();
    }

    @Transactional
    @Override   // 문의 디테일 조회
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
    @Override // 문의 답변 추가
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


    @Transactional
    @Override   // 답변 안한 문의 리스트 조회
    public List<InquiryEntity> svcInquiryNoAnswerList() {
        return inquiryRepository.findAllByInqAnswerIsNullOrderByInqSeq();
    }

    @Transactional
    @Override   // 답변 된 모든 문의 리스트 조회
    public List<InquiryEntity> svcInquiryYesAnswerList() {
        return inquiryRepository.findAllByInqAnswerIsNotNullOrderByInqSeq();
    }

    @Transactional
    @Override   // 내가 답한 문의 리스트 조회
    public List<InquiryEntity> svcInquiryMyAnswerList(InquiryAnswerEntity ansVO) {
        List<InquiryAnswerEntity> ansList = inquiryAnswerRepository.findAllByUserSeq(ansVO.getUserSeq());
        //직원 userSeq를 가지고 inquiry_answer로 가서 그 직원이 작성한 답변VO List를 가져옴.

        List<InquiryEntity> inqList = new ArrayList<InquiryEntity>();
        // 직원이 작성한 InquiryEntity담을 List 선언

        for(InquiryAnswerEntity answerEntity : ansList){
            inqList.add(inquiryRepository.findById(answerEntity.getInquiry().getInqSeq()).get());
        }
        // ansList안에 담겨있는 inqSeq 하나마다 InqVO하나씩 찾아서 inqList에 담아준다.

        inqList.sort(Comparator.comparing(InquiryEntity::getInqSeq));
        // inqSeq로 orderby 해주는 코드

        return inqList;
    }

}
