package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.AnswerDto;
import com.fmsp.fonyou.application.dto.ExamDto;
import com.fmsp.fonyou.application.dto.QuestionDto;
import com.fmsp.fonyou.application.port.ExamService;
import com.fmsp.fonyou.config.exception.AnswerNotFound;
import com.fmsp.fonyou.config.exception.ExamAlreadyAssigned;
import com.fmsp.fonyou.config.exception.QuestionNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateExamUseCaseTest {

    @Mock
    private ExamService mockExamService;
    @Mock
    private CreateQuestionUseCase mockCreateQuestionUseCase;
    @Mock
    private CreateAnswerUseCase mockCreateAnswerUseCase;

    private CreateExamUseCase createExamUseCaseUnderTest;

    @BeforeEach
    void setUp() {
        createExamUseCaseUnderTest = new CreateExamUseCase(mockExamService, mockCreateQuestionUseCase,
                mockCreateAnswerUseCase);
    }

    @Test
    void testCreateExam() {
        final ExamDto examDto = new ExamDto();
        examDto.setId(0L);
        final QuestionDto questionDto = new QuestionDto();
        questionDto.setId(0L);
        questionDto.setQuestionName("questionName");
        questionDto.setExamId(0L);
        questionDto.setRate(0.0);
        final AnswerDto answerDto = new AnswerDto();
        answerDto.setId(0L);
        answerDto.setName("name");
        answerDto.setValue(false);
        answerDto.setQuestionId(0L);
        questionDto.setAnswer(List.of(answerDto));
        examDto.setQuestionDtoList(List.of(questionDto));

        final ExamDto examDto1 = new ExamDto();
        examDto1.setId(0L);
        final QuestionDto questionDto1 = new QuestionDto();
        questionDto1.setId(0L);
        questionDto1.setQuestionName("questionName");
        questionDto1.setExamId(0L);
        questionDto1.setRate(0.0);
        final AnswerDto answerDto1 = new AnswerDto();
        answerDto1.setId(0L);
        answerDto1.setName("name");
        answerDto1.setValue(false);
        answerDto1.setQuestionId(0L);
        questionDto1.setAnswer(List.of(answerDto1));
        examDto1.setQuestionDtoList(List.of(questionDto1));
        when(mockExamService.createExam(any(ExamDto.class))).thenReturn(examDto1);

        final QuestionDto questionDto2 = new QuestionDto();
        questionDto2.setId(0L);
        questionDto2.setQuestionName("questionName");
        questionDto2.setExamId(0L);
        questionDto2.setRate(0.0);
        final AnswerDto answerDto2 = new AnswerDto();
        answerDto2.setId(0L);
        answerDto2.setName("name");
        answerDto2.setValue(false);
        answerDto2.setQuestionId(0L);
        questionDto2.setAnswer(List.of(answerDto2));
        when(mockCreateQuestionUseCase.createQuestion(any(QuestionDto.class))).thenReturn(questionDto2);

        final AnswerDto answerDto3 = new AnswerDto();
        answerDto3.setId(0L);
        answerDto3.setName("name");
        answerDto3.setValue(false);
        answerDto3.setQuestionId(0L);
        when(mockCreateAnswerUseCase.createAnswer(any(AnswerDto.class))).thenReturn(answerDto3);

        final ExamDto result = createExamUseCaseUnderTest.createExam(examDto);

        Assertions.assertNotNull(result);
    }

    @Test
    void testCreateExamNotHaveQuestion() {
        final ExamDto examDto = new ExamDto();
        examDto.setId(0L);
        final QuestionDto questionDto = new QuestionDto();
        questionDto.setId(0L);
        questionDto.setQuestionName("questionName");
        questionDto.setExamId(0L);
        questionDto.setRate(0.0);
        final AnswerDto answerDto = new AnswerDto();
        answerDto.setId(0L);
        answerDto.setName("name");
        answerDto.setValue(false);
        answerDto.setQuestionId(0L);
        questionDto.setAnswer(List.of(answerDto));
        examDto.setQuestionDtoList(Collections.emptyList());

        when(mockExamService.createExam(any(ExamDto.class))).thenReturn(examDto);

        assertThatThrownBy(() -> createExamUseCaseUnderTest.createExam(examDto))
                .isInstanceOf(QuestionNotFound.class);
    }

    @Test
    void testCreateExamNotHaveAnswer() {
        final ExamDto examDto = new ExamDto();
        examDto.setId(0L);
        final QuestionDto questionDto = new QuestionDto();
        questionDto.setId(0L);
        questionDto.setQuestionName("questionName");
        questionDto.setExamId(0L);
        questionDto.setRate(0.0);
        final AnswerDto answerDto = new AnswerDto();
        answerDto.setId(0L);
        answerDto.setName("name");
        answerDto.setValue(false);
        answerDto.setQuestionId(0L);
        questionDto.setAnswer(Collections.emptyList());
        examDto.setQuestionDtoList(List.of(questionDto));

        when(mockExamService.createExam(any(ExamDto.class))).thenReturn(examDto);
        when(mockCreateQuestionUseCase.createQuestion(any(QuestionDto.class))).thenReturn(questionDto);

        assertThatThrownBy(() -> createExamUseCaseUnderTest.createExam(examDto))
                .isInstanceOf(AnswerNotFound.class);
    }
}
