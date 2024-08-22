package com.dowadream.customercare.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "FaqEntity")
@Data
@Table(name = "faq")
public class FaqEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faq_sequence_gen")
    @SequenceGenerator(name = "faq_sequence_gen", sequenceName = "faq_sequence", allocationSize = 1)
    @Column(name = "faq_seq")
    private Long faqSeq;

    @Column(name = "faq_title", nullable = false, length = 100)
    private String faqTitle;

    @Column(name = "faq_content", nullable = false, length = 500)
    private String faqContent;

    @Column(name = "user_seq", nullable = false)
    private Long userSeq;

    @Column(name = "faq_regdate", nullable = false, columnDefinition = "date default sysdate")
    private LocalDate faqRegdate = LocalDate.now();
    // LocalDate Java 8부터 도입된 새로운 날짜 및 시간 API에서 제공하는 클래스입니다. 시간 정보를 포함하지 않고 오로지 날짜 정보(년, 월, 일)만 저장합니다.
    // Date는 변경 가능한 객체입니다. 객체의 상태가 변경될 수 있습니다.
    // LocalDate는 불변 객체로, 객체의 상태가 변경되지 않습니다. 날짜 말고 시간까지 필요하면 LocalDateTime 사용.
}
