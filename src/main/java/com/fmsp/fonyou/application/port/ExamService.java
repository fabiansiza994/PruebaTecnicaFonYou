package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.ExamListDto;

import java.util.List;

public interface ExamService {
    ExamDto createExam(ExamDto examDto);
    List<ExamListDto> getExamList();
    ExamDto getExamById(Long examId);
}
