package com.fmsp.fonyou.application.port;

import com.fmsp.fonyou.application.dto.ExamDto;

public interface ExamService {
    ExamDto createExam(ExamDto examDto);
}
