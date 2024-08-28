package com.dowadream.customercare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "InquiryFileEntity")
@Table(name = "inquiry_file")
public class InqFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inquiry_file_sequence_gen")
    @SequenceGenerator(name = "inquiry_file_sequence_gen", sequenceName = "inq_file_sequence", allocationSize = 1)
    @Column(name = "inq_fseq")
    private Long inqFseq;

    @Column(name = "oname", nullable = false, length = 100)
    private String oname;   //사용자가 올린 파일 원본명         aa.jpg

    @Column(name = "sname", nullable = false, length = 100)
    private String sname;	//시스템에 올릴때 리네임되는 파일명   2015777_aa.jpg

    @Column(name = "fsize", nullable = false, length = 20, columnDefinition = "NUMBER DEFAULT 0")
    private Long fsize;		//바이트  457848787

    @Column(name = "fpath", nullable = false, length = 100)
    private String fpath;	//파일저장경로 :  </webapp>   /uploads/2015777_aa.jpg

    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "inq_file_regdate", nullable = false, columnDefinition = "date default sysdate")
    private LocalDateTime inqFileRegdate = LocalDateTime.now();
    // LocalDate Java 8부터 도입된 새로운 날짜 및 시간 API에서 제공하는 클래스입니다. 시간 정보를 포함하지 않고 오로지 날짜 정보(년, 월, 일)만 저장합니다.
    // Date는 변경 가능한 객체입니다. 객체의 상태가 변경될 수 있습니다.
    // LocalDate는 불변 객체로, 객체의 상태가 변경되지 않습니다. 날짜 말고 시간까지 필요하면 LocalDateTime 사용.

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "inq_seq", referencedColumnName="inq_seq", nullable = true)
    private InquiryEntity inquiry;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ans_seq", referencedColumnName="ans_seq", nullable = true)
    private InquiryAnswerEntity inquiryAnswer;

}
