package com.dowadream.customercare;

import com.dowadream.customercare.Entity.FaqEntity;
import com.dowadream.customercare.Entity.NotificationEntity;
import com.dowadream.customercare.Repository.FaqRepository;
import com.dowadream.customercare.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerCareServiceImpl implements CustomerCareService{

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private FaqRepository faqRepository;

    @Transactional
    @Override   // 공지사항 추가
    public void svcNotificationInsert(NotificationEntity notiVO) {
        notificationRepository.save(notiVO);
    }

    @Transactional
    @Override   // 공지사항 리스트 조회
    public List<NotificationEntity> svcNotificationList() {
        return (List<NotificationEntity>) notificationRepository.findAllByOrderByNotiSeq();
    }

    @Transactional
    @Override   // 공지사항 리스트 조회
    public NotificationEntity svcNotificationDetail(NotificationEntity notiVO) {
        return notificationRepository.findById(notiVO.getNotiSeq()).get();
    }

    @Transactional
    @Override   // 공지사항 수정
    public void svcNotificationUpdate(NotificationEntity notiVO) {
        notificationRepository.save(notiVO);
    }

    @Transactional
    @Override   // 공지사항 삭제
    public void svcNotificationDelete(NotificationEntity notiVO) {
        notificationRepository.deleteById(notiVO.getNotiSeq());
    }
    // 공지사항 끝



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
