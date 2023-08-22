package com.fmsp.fonyou.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="exam_student_report")
public class ExamStudentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "exam_json", columnDefinition = "TEXT")
    private String examJson;

    private Instant presentationDate;

    private Double result;

    @Column(columnDefinition = "boolean default false")
    private Boolean status;
}
