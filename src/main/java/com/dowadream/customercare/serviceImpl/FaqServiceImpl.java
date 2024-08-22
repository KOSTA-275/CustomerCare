package com.dowadream.customercare.serviceImpl;

import com.dowadream.customercare.entity.FaqEntity;
import com.dowadream.customercare.repository.FaqRepository;
import com.dowadream.customercare.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaqServiceImpl implements FaqService {


    @Autowired
    private FaqRepository faqRepository;

    @Transactional
    @Override   // 자주 묻는 질문 추가
    public void svcFaqInsert(FaqEntity faqVO) {
        faqRepository.save(faqVO);
    }

    @Transactional
    @Override   // 자주 묻는 질문 리스트 조회
    public List<FaqEntity> svcFaqList() {
        return (List<FaqEntity>) faqRepository.findAllByOrderByFaqSeq();
    }

    @Transactional
    @Override   // 자주 묻는 질문 리스트 조회
    public FaqEntity svcFaqDetail(FaqEntity faqVO) {
        return faqRepository.findById(faqVO.getFaqSeq()).get();
    }

    @Transactional
    @Override   // 자주 묻는 질문 수정
    public void svcFaqUpdate(FaqEntity faqVO) {
        faqRepository.save(faqVO);
    }

    @Transactional
    @Override   // 자주 묻는 질문 삭제
    public void svcFaqDelete(FaqEntity faqVO) {
        faqRepository.deleteById(faqVO.getFaqSeq());
    }
}
