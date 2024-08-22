package com.dowadream.customercare.serviceImpl;

import com.dowadream.customercare.entity.NotificationEntity;
import com.dowadream.customercare.repository.NotificationRepository;
import com.dowadream.customercare.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

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

}
