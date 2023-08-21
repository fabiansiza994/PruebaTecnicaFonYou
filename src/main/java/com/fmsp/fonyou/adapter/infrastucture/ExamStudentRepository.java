package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.application.dto.ExamProjection;
import com.fmsp.fonyou.domain.ExamStudentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudentReport, Long> {
    List<ExamStudentReport> findAllByStudentId(Long studentId);
    Optional<ExamStudentReport> findByStudentIdAndExamIdAndStatusTrue(Long studentId, Long examId);

    @Query(value = "select e.name as examName, q.id as questionId, q.name as questionName, an.id as answerId, an.name as answerName, 'false' as answerValue, q.rate " +
            "from exam_student_report er " +
            "inner join student s on s.id = er.student_id " +
            "inner join exam e on e.id = er.exam_id " +
            "inner join question q on q.exam_id = e.id " +
            "inner join answer an on an.question_id = q.id " +
            "where e.id = :examId and s.id = :studentId and er.status = true", nativeQuery = true)
    List<ExamProjection> getExamStudent(@Param("studentId") Long studentId, @Param("examId") Long examId);
}
