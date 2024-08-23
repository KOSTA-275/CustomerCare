package com.dowadream.customercare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "InquiryEntity")
@Data
@Table(name = "inquiry")
public class InquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inq_sequence_gen")
    @SequenceGenerator(name = "inq_sequence_gen", sequenceName = "inq_sequence", allocationSize = 1)
    @Column(name = "inq_seq")
    private Long inqSeq;

    @Column(name = "inq_title", nullable = false, length = 100)
    private String inqTitle;

    @Column(name = "inq_content", nullable = false, length = 500)
    private String inqContent;

    @Column(name = "user_seq", nullable = false)
    private Long userSeq;

    @Column(name = "inq_regdate", nullable = false, columnDefinition = "date default sysdate")
    private LocalDateTime inqRegdate = LocalDateTime.now();
    // LocalDate Java 8부터 도입된 새로운 날짜 및 시간 API에서 제공하는 클래스입니다. 시간 정보를 포함하지 않고 오로지 날짜 정보(년, 월, 일)만 저장합니다.
    // Date는 변경 가능한 객체입니다. 객체의 상태가 변경될 수 있습니다.
    // LocalDate는 불변 객체로, 객체의 상태가 변경되지 않습니다. 날짜 말고 시간까지 필요하면 LocalDateTime 사용.

    @OneToOne(mappedBy = "inquiry")
    private InquiryAnswerEntity inqAnswer;
}
