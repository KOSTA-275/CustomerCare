package com.dowadream.customercare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "InquiryAnswerEntity")
@Data
@Table(name = "inquiry_answer")
public class InquiryAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ans_sequence_gen")
    @SequenceGenerator(name = "ans_sequence_gen", sequenceName = "ans_sequence", allocationSize = 1)
    @Column(name = "ans_seq")
    private Long ansSeq;

    @Column(name = "inq_answer", nullable = false, length = 100)
    private String inqAnswer;

    @Column(name = "user_seq", nullable = false)
    private Long userSeq;

    @Column(name = "ans_regdate", nullable = false, columnDefinition = "date default sysdate")
    private LocalDateTime ansRegdate = LocalDateTime.now();
    // LocalDate Java 8부터 도입된 새로운 날짜 및 시간 API에서 제공하는 클래스입니다. 시간 정보를 포함하지 않고 오로지 날짜 정보(년, 월, 일)만 저장합니다.
    // Date는 변경 가능한 객체입니다. 객체의 상태가 변경될 수 있습니다.
    // LocalDate는 불변 객체로, 객체의 상태가 변경되지 않습니다. 날짜 말고 시간까지 필요하면 LocalDateTime 사용.

    @OneToMany(mappedBy = "inquiryAnswer", cascade = CascadeType.ALL)
    private List<InqFileEntity> inqFile;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="inq_seq", referencedColumnName="inq_seq", nullable = false)
    private InquiryEntity inquiry;

}
