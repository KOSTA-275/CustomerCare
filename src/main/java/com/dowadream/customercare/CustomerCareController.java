package com.dowadream.customercare;

import com.dowadream.customercare.Entity.FaqEntity;
import com.dowadream.customercare.Entity.NotificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerCare")
public class CustomerCareController {

    @Autowired
    private CustomerCareService customerCareService;

    /**
     * 공지사항 리스트 추가
     * @param notiVO
     * notiTitle, notiContent, userSeq
     */
    @PostMapping("/noti_insert")
    public void ctlNotificationInsert(@RequestBody NotificationEntity notiVO){
        customerCareService.svcNotificationInsert(notiVO);
    }

    /**
     * 공지사항 리스트 조회
     */
    @GetMapping("/noti_list")
    public ResponseEntity<List<NotificationEntity>> ctlNotificationList(){
        List<NotificationEntity> notiList = customerCareService.svcNotificationList();

        return new ResponseEntity<List<NotificationEntity>>(notiList, HttpStatus.OK);
    }

    /**
     * 공지사항 디테일 조회
     * @param notiVO
     * notiSeq
     */
    @GetMapping("/noti_detail")
    public ResponseEntity<NotificationEntity> ctlNotificationDetail(@RequestBody NotificationEntity notiVO){
        NotificationEntity notiEntity = customerCareService.svcNotificationDetail(notiVO);

        return new ResponseEntity<NotificationEntity>(notiEntity, HttpStatus.OK);
    }

    /**
     * 공지사항 수정
     * @param notiVO
     * notiSeq, notiTitle, notiContent, userSeq
     */
    @PostMapping("/noti_update")
    public void ctlNotificationUpdate(@RequestBody NotificationEntity notiVO){
        customerCareService.svcNotificationUpdate(notiVO);
    }

    /**
     * 공지사항 삭제
     * @param notiVO
     * notiSeq
     */
    @GetMapping("/noti_delete")
    public void ctlNotificationDelete(@RequestBody NotificationEntity notiVO){
        customerCareService.svcNotificationDelete(notiVO);
    }
    // 공지사항 끝




    /**
     * 자주 묻는 질문 리스트 추가
     * @param faqVO
     * faqTitle, faqContent, userSeq
     */
    @PostMapping("/faq_insert")
    public void ctlFaqInsert(@RequestBody FaqEntity faqVO){
        customerCareService.svcFaqInsert(faqVO);
    }

    /**
     * 자주 묻는 질문 리스트 조회
     */
    @GetMapping("/faq_list")
    public ResponseEntity<List<FaqEntity>> ctlFaqList(){
        List<FaqEntity> faqList = customerCareService.svcFaqList();

        return new ResponseEntity<List<FaqEntity>>(faqList, HttpStatus.OK);
    }

    /**
     * 자주 묻는 질문 디테일 조회
     * @param faqVO
     * faqSeq
     */
    @GetMapping("/faq_detail")
    public ResponseEntity<FaqEntity> ctlFaqDetail(@RequestBody FaqEntity faqVO){
        FaqEntity faqEntity = customerCareService.svcFaqDetail(faqVO);

        return new ResponseEntity<FaqEntity>(faqEntity, HttpStatus.OK);
    }

    /**
     * 자주 묻는 질문 수정
     * @param faqVO
     * faqSeq, faqTitle, faqContent, userSeq
     */
    @PostMapping("/faq_update")
    public void ctlFaqUpdate(@RequestBody FaqEntity faqVO){
        customerCareService.svcFaqUpdate(faqVO);
    }

    /**
     * 자주 묻는 질문 삭제
     * @param faqVO
     * faqSeq
     */
    @GetMapping("/faq_delete")
    public void ctlFaqDelete(@RequestBody FaqEntity faqVO){
        customerCareService.svcFaqDelete(faqVO);
    }
}
