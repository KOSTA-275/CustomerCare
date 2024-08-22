package com.dowadream.customercare.service;

import com.dowadream.customercare.entity.NotificationEntity;

import java.util.List;

public interface NotificationService {
    public void svcNotificationInsert(NotificationEntity notiVO);                   // 공지사항 추가
    public List<NotificationEntity> svcNotificationList();                          // 공지사항 리스트 조회
    public NotificationEntity svcNotificationDetail(NotificationEntity notiVO);     // 공지사항 디테일 조회
    public void svcNotificationUpdate(NotificationEntity notiVO);                   // 공지사항 수정
    public void svcNotificationDelete(NotificationEntity notiVO);                   // 공지사항 삭제
}
