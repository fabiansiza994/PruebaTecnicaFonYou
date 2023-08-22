package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.application.dto.ExamAnswerDto;
import com.fmsp.fonyou.application.dto.ExamProjectionDto;
import com.fmsp.fonyou.domain.ExamStudentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamStudentRepository extends JpaRepository<ExamStudentReport, Long> {
    List<ExamStudentReport> findAllByStudentId(Long studentId);
    Optional<ExamStudentReport> findByStudentIdAndExamIdAndStatusTrue(Long studentId, Long examId);


    @Query("select new com.fmsp.fonyou.application.dto.ExamAnswerDto(" +
            "e.name, " +
            "er.examJson, " +
            "er.result " +
            ") " +
            "from ExamStudentReport er " +
            "inner join Student s on s.id = er.studentId " +
            "inner join Exam e on e.id = er.examId " +
            "where e.id = :examId and s.id = :studentId and er.status = false")
    Optional<ExamAnswerDto> findByStudentIdAndExamId(Long studentId, Long examId);

    @Query(value = "select new com.fmsp.fonyou.application.dto.ExamProjectionDto(" +
            "e.id, " +
            "e.name, " +
            "q.id, " +
            "q.questionName, " +
            "an.id, " +
            "an.name, " +
            "an.value, " +
            "q.rate) " +
            "from ExamStudentReport er " +
            "inner join Student s on s.id = er.studentId " +
            "inner join Exam e on e.id = er.examId " +
            "inner join Question q on q.exam.id = e.id " +
            "inner join Answer an on an.question.id = q.id " +
            "where e.id = :examId and s.id = :studentId and er.status = true")
    List<ExamProjectionDto> getExamStudent(@Param("studentId") Long studentId, @Param("examId") Long examId);

    @Modifying
    @Query("update ExamStudentReport er set er.result =:rate, er.examJson =:json, er.status =:status " +
            "where er.studentId =:studentId and er.examId =:examId")
    int updateExamStudentReport(@Param("rate") Double rate, @Param("examId") Long examId,
                                @Param("studentId") Long studentId, @Param("status") Boolean status, @Param("json") String json);
}
