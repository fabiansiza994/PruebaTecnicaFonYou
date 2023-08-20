package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.ExamDto;

import java.util.List;

public interface ExamService {
    ExamDto createExam(ExamDto examDto);
    List<ExamDto> getExamList();
}
