package com.dowadream.customercare.controller;

import com.dowadream.customercare.service.FaqService;
import com.dowadream.customercare.entity.FaqEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customercare")
public class FaqController {

    @Autowired
    private FaqService faqService;

    /**
     * 자주 묻는 질문 리스트 추가
     * @param faqVO
     * faqTitle, faqContent, userSeq
     */
    @PostMapping("/faq_insert")
    public void ctlFaqInsert(@RequestBody FaqEntity faqVO){
        faqService.svcFaqInsert(faqVO);
    }

    /**
     * 자주 묻는 질문 리스트 조회
     */
    @GetMapping("/faq_list")
    public ResponseEntity<List<FaqEntity>> ctlFaqList(){
        List<FaqEntity> faqList = faqService.svcFaqList();

        return new ResponseEntity<> (faqList, HttpStatus.OK);
    }

    /**
     * 자주 묻는 질문 디테일 조회
     * @param faqVO
     * faqSeq
     */
    @GetMapping("/faq_detail")
    public ResponseEntity<FaqEntity> ctlFaqDetail(@RequestBody FaqEntity faqVO){
        FaqEntity faqEntity = faqService.svcFaqDetail(faqVO);

        return new ResponseEntity<> (faqEntity, HttpStatus.OK);
    }

    /**
     * 자주 묻는 질문 수정
     * @param faqVO
     * faqSeq, faqTitle, faqContent, userSeq
     */
    @PutMapping("/faq_update")
    public void ctlFaqUpdate(@RequestBody FaqEntity faqVO){
        faqService.svcFaqUpdate(faqVO);
    }

    /**
     * 자주 묻는 질문 삭제
     * @param faqVO
     * faqSeq
     */
    @DeleteMapping("/faq_delete")
    public void ctlFaqDelete(@RequestBody FaqEntity faqVO){
        faqService.svcFaqDelete(faqVO);
    }
}
