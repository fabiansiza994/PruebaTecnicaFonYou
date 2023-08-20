package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.domain.ExamStudentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudentReport, Long> {
    List<ExamStudentReport> findAllByStudentId(Long studentId);
}
