package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.AnswerDto;
import com.fmsp.fonyou.application.port.AnswerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAnswerUseCaseTest {

    @Mock
    private AnswerService mockAnswerService;

    private CreateAnswerUseCase createAnswerUseCaseUnderTest;

    @BeforeEach
    void setUp() {
        createAnswerUseCaseUnderTest = new CreateAnswerUseCase(mockAnswerService);
    }

    @Test
    void testCreateAnswer() {
        final AnswerDto answerDto = new AnswerDto();
        answerDto.setId(0L);
        answerDto.setName("name");
        answerDto.setValue(false);
        answerDto.setQuestionId(0L);

        final AnswerDto answerDto1 = new AnswerDto();
        answerDto1.setId(0L);
        answerDto1.setName("name");
        answerDto1.setValue(false);
        answerDto1.setQuestionId(0L);
        when(mockAnswerService.createAnswer(any(AnswerDto.class))).thenReturn(answerDto1);

        final AnswerDto result = createAnswerUseCaseUnderTest.createAnswer(answerDto);

        Assertions.assertNotNull(result);
    }
}
