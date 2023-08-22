package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.ExamStudentReportDto;
import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.port.AssignExamService;
import com.fmsp.fonyou.config.exception.ExamAlreadyAssigned;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignExamUseCaseTest {

    @Mock
    private AssignExamService mockAssignExamUseCase;
    @Mock
    private GetStudentByIdUseCase mockGetStudentByIdUseCase;
    @Mock
    private GetExamByStudentAndStatusTrueUseCase mockGetExamByStudentAndStatusTrueUseCase;

    @Mock
    private GetExamByIdUseCase getExamByIdUseCase;

    private AssignExamUseCase assignExamUseCaseUnderTest;

    @BeforeEach
    void setUp() {
        assignExamUseCaseUnderTest = new AssignExamUseCase(mockAssignExamUseCase, mockGetStudentByIdUseCase,
                mockGetExamByStudentAndStatusTrueUseCase, getExamByIdUseCase);
    }

    @Test
    void testAssignExamAlreadyAssigned() {

        final ExamStudentReportDto examStudentReportDto = new ExamStudentReportDto();
        examStudentReportDto.setStudentId(0L);
        examStudentReportDto.setExamId(0L);
        examStudentReportDto.setPresentationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        examStudentReportDto.setResult(0.0);
        examStudentReportDto.setStatus(false);

        final StudentDto studentDto = new StudentDto();
        studentDto.setId(0L);
        studentDto.setName("name");
        studentDto.setAge(0);
        studentDto.setCity("city");
        studentDto.setUtc("utc");

        final ExamDto examDto = new ExamDto();
        when(mockGetStudentByIdUseCase.getStudentById(0L)).thenReturn(studentDto);
        when(getExamByIdUseCase.getExamById(0L)).thenReturn(examDto);

        final ExamStudentReportDto examStudentReportDto2 = new ExamStudentReportDto();
        examStudentReportDto2.setStudentId(0L);
        examStudentReportDto2.setExamId(0L);
        examStudentReportDto2.setPresentationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        examStudentReportDto2.setResult(0.0);
        examStudentReportDto2.setStatus(false);
        final Optional<ExamStudentReportDto> examStudentReportDto1 = Optional.of(examStudentReportDto2);
        when(mockGetExamByStudentAndStatusTrueUseCase.getExamByStudentAndStatusTrue(0L, 0L))
                .thenReturn(examStudentReportDto1);

        assertThatThrownBy(() -> assignExamUseCaseUnderTest.AssignExam(examStudentReportDto))
                .isInstanceOf(ExamAlreadyAssigned.class);
    }

}
