package com.fmsp.fonyou.application.usecase;

import com.fmsp.fonyou.application.dto.StudentDto;
import com.fmsp.fonyou.application.port.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateStudentUseCaseTest {

    @Mock
    private StudentService mockStudentService;

    private CreateStudentUseCase createStudentUseCaseUnderTest;

    @BeforeEach
    void setUp() {
        createStudentUseCaseUnderTest = new CreateStudentUseCase(mockStudentService);
    }

    @Test
    void testCreateStudent() {
        final StudentDto studentDto = new StudentDto();
        studentDto.setId(0L);
        studentDto.setName("name");
        studentDto.setAge(0);
        studentDto.setCity("city");
        studentDto.setUtc("utc");

        final StudentDto studentDto1 = new StudentDto();
        studentDto1.setId(0L);
        studentDto1.setName("name");
        studentDto1.setAge(0);
        studentDto1.setCity("city");
        studentDto1.setUtc("utc");
        when(mockStudentService.createStudent(any(StudentDto.class))).thenReturn(studentDto1);

        final StudentDto result = createStudentUseCaseUnderTest.createStudent(studentDto);

        Assertions.assertNotNull(result);
    }
}
