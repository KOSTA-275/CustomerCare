package com.dowadream.customercare.controller;

import com.dowadream.customercare.entity.InquiryAnswerEntity;
import com.dowadream.customercare.entity.InquiryEntity;
import com.dowadream.customercare.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customercare")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    /**
     * 문의 추가
     * @param inqVO
     * inqTitle, inqContent, userSeq
     * @param files
     * MultipartFile files
     */
    @PostMapping("/inquiry_insert")
    public String ctlInquiryInsert(@ModelAttribute InquiryEntity inqVO, @RequestParam(value = "ufiles", required = false) List<MultipartFile> files){
        return inquiryService.svcInquiryInsert(inqVO, files);
    }

    /**
     * 모든 문의 리스트 조회
     */
    @GetMapping("/inquiry_list")
    public ResponseEntity<List<InquiryEntity>> ctlInquiryList() {
        List<InquiryEntity> inqList = inquiryService.svcInquiryList();

        return new ResponseEntity<> (inqList, HttpStatus.OK);
    }

    /**
     * 특정 유저 문의 리스트 조회
     *
     */
    @GetMapping("/inquiry_userlist")
    public ResponseEntity<List<InquiryEntity>> ctlInquiryList(@RequestBody InquiryEntity inqVO){
        List<InquiryEntity> inqList = inquiryService.svcInquiryUserList(inqVO);

        return new ResponseEntity<>(inqList, HttpStatus.OK);
    }

    /**
     * 문의 디테일 조회
     * @param inqVO
     * inqSeq
     */
    @GetMapping("/inquiry_detail")
    public ResponseEntity<InquiryEntity> ctlInquiryDetail(@RequestBody InquiryEntity inqVO){
        InquiryEntity inqEntity = inquiryService.svcInquiryDetail(inqVO);

        return new ResponseEntity<> (inqEntity, HttpStatus.OK);
    }

//    /**
//     * 문의 수정
//     * @param inqVO
//     * inqSeq, inqTitle, inqContent, userSeq
//     */
//    @PutMapping("/inquiry_update")
//    public void ctlInquiryUpdate(@RequestBody InquiryEntity inqVO){
//        inquiryService.svcInquiryUpdate(inqVO);
//    }

    /**
     * 문의 삭제
     * @param inqVO
     * inqSeq
     */
    @DeleteMapping("/inquiry_delete")
    public void ctlInquiryDelete(@RequestBody InquiryEntity inqVO) throws IOException {
        inquiryService.svcInquiryDelete(inqVO);
    }

    /**
     * 문의 답변 추가
     * @param ansVO
     * inqAnswer, userSeq
     * @param inqSeq
     * @param files
     */
    @PostMapping("/answer_insert")
    public String ctlInquiryAnswerInsert(@ModelAttribute InquiryAnswerEntity ansVO,
                                         @RequestParam("inqSeq") Long inqSeq,
                                         @RequestParam(value = "ufiles", required = false) List<MultipartFile> files){

        return inquiryService.svcInquiryAnswerInsert(ansVO, inqSeq, files);
        // 정상적 insert -> "created",
        // 없는 문의에 추가하고 있는 경우 -> "notFoundError",
        // 이미 문의 답변이 있는데 추가하는 경우 -> "alreadyExistError"

    }

    /**
     * 답변 안한 문의 리스트 조회
     */
    @GetMapping("/inquiry_nolist")
    public ResponseEntity<List<InquiryEntity>> ctlInquiryNoAnswerList(){
        List<InquiryEntity> inqList = inquiryService.svcInquiryNoAnswerList();

        return new ResponseEntity<> (inqList, HttpStatus.OK);
    }

    /**
     * 답변 된 모든 문의 리스트 조회
     */
    @GetMapping("/inquiry_yeslist")
    public ResponseEntity<List<InquiryEntity>> ctlInquiryYesAnswerList(){
        List<InquiryEntity> inqList = inquiryService.svcInquiryYesAnswerList();

        return new ResponseEntity<> (inqList, HttpStatus.OK);
    }

    /**
     * 내가 답한 문의 리스트 조회
     * @param ansVO
     * userSeq
     */
    @GetMapping("/inquiry_mylist")
    public ResponseEntity<List<InquiryEntity>> ctlInquiryMyAnswerList(@RequestBody InquiryAnswerEntity ansVO){
        List<InquiryEntity> inqList = inquiryService.svcInquiryMyAnswerList(ansVO);

        return new ResponseEntity<> (inqList, HttpStatus.OK);
    }

//    /**
//     * 문의 답변 수정
//     * @param ansVO
//     * inqAnswer, userSeq
//     * @param inqSeq
//     */
//    @PutMapping("/answer_update")
//    public String ctlInquiryUpdate(@ModelAttribute InquiryAnswerEntity ansVO,
//                                   @RequestParam("inqSeq") Long inqSeq){
//        return inquiryService.svcInquiryAnswerUpdate(ansVO, inqSeq, files);
//        // 정상적 update -> "updated"
//    }

    /**
     * 문의 답변 삭제
     * @param ansVO
     * ansSeq
     */
    @DeleteMapping("/answer_delete")
    public void ctlInquiryAnswerDelete(@RequestBody InquiryAnswerEntity ansVO){
        inquiryService.svcInquiryAnswerDelete(ansVO);
    }

    /**
     * 문의 검색(문의 제목)
     * @param insVO
     * searchStr
     */
    @GetMapping("/search_bytitle")
    public ResponseEntity<List<InquiryEntity>> ctlInquirySearchByTitle(@RequestBody InquiryEntity insVO){
        List<InquiryEntity> inqList = inquiryService.svcInquirySearchByTitle(insVO);

        return new ResponseEntity<> (inqList, HttpStatus.OK);
    }

    /**
     * 문의 검색(문의 내용)
     * @param insVO
     * searchStr
     */
    @GetMapping("/search_bycontent")
    public ResponseEntity<List<InquiryEntity>> ctlInquirySearchByContent(@RequestBody InquiryEntity insVO){
        List<InquiryEntity> inqList = inquiryService.svcInquirySearchByContent(insVO);

        return new ResponseEntity<> (inqList, HttpStatus.OK);
    }

    /**
     * 문의 검색(문의 제목+내용)
     * @param insVO
     * searchStr
     */
    @GetMapping("/search_byall")
    public ResponseEntity<List<InquiryEntity>> ctlInquirySearchByAll(@RequestBody InquiryEntity insVO){
        List<InquiryEntity> inqList = inquiryService.svcInquirySearchByAll(insVO);

        return new ResponseEntity<> (inqList, HttpStatus.OK);
    }

}
