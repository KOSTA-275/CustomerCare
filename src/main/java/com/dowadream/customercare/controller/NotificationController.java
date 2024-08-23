package com.dowadream.customercare.controller;

import com.dowadream.customercare.entity.NotificationEntity;
import com.dowadream.customercare.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 공지사항 리스트 추가
     * @param notiVO
     * notiTitle, notiContent, userSeq
     */
    @PostMapping("/noti_insert")
    public void ctlNotificationInsert(@RequestBody NotificationEntity notiVO){
        notificationService.svcNotificationInsert(notiVO);
    }

    /**
     * 공지사항 리스트 조회
     */
    @GetMapping("/noti_list")
    public ResponseEntity<List<NotificationEntity>> ctlNotificationList(){
        List<NotificationEntity> notiList = notificationService.svcNotificationList();

        return new ResponseEntity<List<NotificationEntity>>(notiList, HttpStatus.OK);
    }

    /**
     * 공지사항 디테일 조회
     * @param notiVO
     * notiSeq
     */
    @GetMapping("/noti_detail")
    public ResponseEntity<NotificationEntity> ctlNotificationDetail(@RequestBody NotificationEntity notiVO){
        NotificationEntity notiEntity = notificationService.svcNotificationDetail(notiVO);

        return new ResponseEntity<NotificationEntity>(notiEntity, HttpStatus.OK);
    }

    /**
     * 공지사항 수정
     * @param notiVO
     * notiSeq, notiTitle, notiContent, userSeq
     */
    @PutMapping("/noti_update")
    public void ctlNotificationUpdate(@RequestBody NotificationEntity notiVO){
        notificationService.svcNotificationUpdate(notiVO);
    }

    /**
     * 공지사항 삭제
     * @param notiVO
     * notiSeq
     */
    @DeleteMapping("/noti_delete")
    public void ctlNotificationDelete(@RequestBody NotificationEntity notiVO){
        notificationService.svcNotificationDelete(notiVO);
    }
}
