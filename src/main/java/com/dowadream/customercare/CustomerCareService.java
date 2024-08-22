package com.dowadream.customercare;

import com.dowadream.customercare.Entity.FaqEntity;
import com.dowadream.customercare.Entity.NotificationEntity;

import java.util.List;

public interface CustomerCareService {
    public void svcNotificationInsert(NotificationEntity notiVO);                   // 공지사항 추가
    public List<NotificationEntity> svcNotificationList();                          // 공지사항 리스트 조회
    public NotificationEntity svcNotificationDetail(NotificationEntity notiVO);     // 공지사항 디테일 조회
    public void svcNotificationUpdate(NotificationEntity notiVO);                   // 공지사항 수정
    public void svcNotificationDelete(NotificationEntity notiVO);                   // 공지사항 삭제

    public void svcFaqInsert(FaqEntity faqVO);                                      // 자주 묻는 질문 추가
    public List<FaqEntity> svcFaqList();                                            // 자주 묻는 질문 리스트 조회
    public FaqEntity svcFaqDetail(FaqEntity faqVO);                                 // 자주 묻는 질문 디테일 조회
    public void svcFaqUpdate(FaqEntity faqVO);                                      // 자주 묻는 질문 수정
    public void svcFaqDelete(FaqEntity faqVO);                                      // 자주 묻는 질문 삭제

}
