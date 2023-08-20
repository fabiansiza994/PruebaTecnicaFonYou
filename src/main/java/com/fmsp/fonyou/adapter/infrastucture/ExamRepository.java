package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
