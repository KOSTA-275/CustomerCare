package com.dowadream.customercare.serviceImpl;

import com.dowadream.customercare.entity.InqFileEntity;
import com.dowadream.customercare.entity.InquiryAnswerEntity;
import com.dowadream.customercare.entity.InquiryEntity;
import com.dowadream.customercare.repository.InqFileRepository;
import com.dowadream.customercare.repository.InquiryAnswerRepository;
import com.dowadream.customercare.repository.InquiryRepository;
import com.dowadream.customercare.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;
    @Autowired
    private InquiryAnswerRepository inquiryAnswerRepository;
    @Autowired
    private InqFileRepository inqFileRepository;

    @Transactional
    @Override   // 문의 추가
    public String svcInquiryInsert(InquiryEntity inqVO, List<MultipartFile> files) {
        InquiryEntity inqEntity = inquiryRepository.save(inqVO);

        //사용자파일명, 크기, 확장자, 시스템파일명(유니크)
        InqFileEntity fvo = null;
        if (files != null) {
            for(MultipartFile file : files) {
                fvo = new InqFileEntity();
                String fileRealName 	= file.getOriginalFilename();
                Long size 				= file.getSize();
                String fileExtension 	= fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
                String uniqueName 		= UUID.randomUUID().toString().split("-")[0];

                String uploadFolder = "C:\\KOSTA\\dowadream\\imgaes\\image_test";
                String filePath 	=  uploadFolder +"\\" + uniqueName + fileExtension;

                fvo.setOname(fileRealName);
                fvo.setSname(uniqueName + fileExtension);						//45dfered.txt
                fvo.setFsize(size);
                fvo.setFpath(filePath);   	//C:\\test\\upload\\45dfered.txt
                fvo.setUserSeq(inqVO.getUserSeq());
                fvo.setInquiry(inqEntity);

                //파일카피
                try {
                    file.transferTo( new File(filePath) );
                    inqFileRepository.save(fvo);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } //e.o.for
        } //e.o.if

        return "created";            //  /templates/  hello   .html

    }

    @Transactional
    @Override   // 모든 문의 리스트 조회
    public List<InquiryEntity> svcInquiryList() {
        return inquiryRepository.findAllByOrderByInqSeq();
    }

    @Transactional
    @Override   // 특정 유저 문의 리스트 조회
    public List<InquiryEntity> svcInquiryUserList(InquiryEntity inqVO) {
        return inquiryRepository.findAllByUserSeqOrderByInqSeq(inqVO.getUserSeq());
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
    @Override // 문의 답변 입력 
    public String svcInquiryAnswerInsert(InquiryAnswerEntity ansVO, Long inqSeq, List<MultipartFile> files) {
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
            InquiryAnswerEntity inquiryAnswerEntity = inquiryAnswerRepository.save(ansVO);

            //사용자파일명, 크기, 확장자, 시스템파일명(유니크)
            InqFileEntity fvo = null;
            if (files != null) {
                for(MultipartFile file : files) {
                    fvo = new InqFileEntity();
                    String fileRealName 	= file.getOriginalFilename();
                    Long size 				= file.getSize();
                    String fileExtension 	= fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
                    String uniqueName 		= UUID.randomUUID().toString().split("-")[0];

                    String uploadFolder = "C:\\KOSTA\\dowadream\\imgaes\\image_test";
                    String filePath 	=  uploadFolder +"\\" + uniqueName + fileExtension;

                    fvo.setInquiry(inqVO);
                    fvo.setOname(fileRealName);
                    fvo.setSname(uniqueName + fileExtension);						//45dfered.txt
                    fvo.setFsize(size);
                    fvo.setFpath(filePath);   	//C:\\test\\upload\\45dfered.txt
                    fvo.setUserSeq(inqVO.getUserSeq());
                    fvo.setInquiryAnswer(inquiryAnswerEntity);

                    //파일카피
                    try {
                        file.transferTo( new File(filePath) );
                        inqFileRepository.save(fvo);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } //e.o.for
            } //e.o.if
            return "created";

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

        List<InquiryEntity> inqList = new ArrayList<>();
        // 직원이 작성한 InquiryEntity담을 List 선언

        for(InquiryAnswerEntity answerEntity : ansList){
            inqList.add(inquiryRepository.findById(answerEntity.getInquiry().getInqSeq()).get());
        }
        // ansList안에 담겨있는 inqSeq 하나마다 InqVO하나씩 찾아서 inqList에 담아준다.

        inqList.sort(Comparator.comparing(InquiryEntity::getInqSeq));
        // inqSeq로 orderby 해주는 코드

        return inqList;
    }

    @Transactional
    @Override // 문의 답변 수정
    public String svcInquiryAnswerUpdate(InquiryAnswerEntity ansVO, Long inqSeq) {
        // inquiryRepository에서 inqSeq를 사용해 InquiryEntity 조회
        Optional<InquiryEntity> optionalInqVO = inquiryRepository.findById(inqSeq);

        // InquiryEntity가 존재하지 않을 경우 "notFoundError" 반환
        if (optionalInqVO.isEmpty()) {
            return "notFoundError";
        }

        InquiryEntity inqVO = optionalInqVO.get();

        // 기존 답변이 존재하는지 확인
        InquiryAnswerEntity existingAnswer = inqVO.getInqAnswer();

        if (existingAnswer != null) {
            // 기존 답변이 존재하는 경우 해당 엔티티를 수정
            existingAnswer.setInqAnswer(ansVO.getInqAnswer()); // 수정할 필드들을 업데이트
            existingAnswer.setAnsRegdate(ansVO.getAnsRegdate()); // 필요에 따라 추가 필드 업데이트
            inquiryAnswerRepository.save(existingAnswer); // 수정된 답변 엔티티 저장

            return "updated";
        } else {
            return "noAnswerError"; // 답변이 아직 저장되지 않았음.
        }
    }

    @Transactional
    @Override   // 문의 답변 삭제
    public void svcInquiryAnswerDelete(InquiryAnswerEntity ansVO) {
        inquiryAnswerRepository.deleteById(ansVO.getAnsSeq());
    }



    @Transactional
    @Override   // 문의 검색(제목)
    public List<InquiryEntity> svcInquirySearchByTitle(InquiryEntity inqVO) {
        return inquiryRepository.findAllByInqTitleContainingOrderByInqSeqDesc(inqVO.getSearchStr());
    }

    @Transactional
    @Override   // 문의 검색(내용)
    public List<InquiryEntity> svcInquirySearchByContent(InquiryEntity inqVO) {
        return inquiryRepository.findAllByInqContentContainingOrderByInqSeqDesc(inqVO.getSearchStr());
    }

    @Transactional
    @Override   // 문의 검색(내용+제목)
    public List<InquiryEntity> svcInquirySearchByAll(InquiryEntity inqVO) {
        return inquiryRepository.findAllByInqTitleContainingOrInqContentContainingOrderByInqSeqDesc(inqVO.getSearchStr(), inqVO.getSearchStr());
    }

}
