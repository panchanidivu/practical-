package com.practical.studentServiceTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import com.practical.dto.StudentCreateDTO;
import com.practical.dto.StudentDTO;
import com.practical.model.Student;
import com.practical.model.University;
import com.practical.repository.StudentRepo;
import com.practical.repository.UniversityRepo;
import com.practical.response.CustomException;
import com.practical.services.StudentService;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private UniversityRepo universityRepo;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddStudent_Success() {
        StudentCreateDTO studentDTO = new StudentCreateDTO();
        studentDTO.setName("AAA");
        studentDTO.setAddress("123abc");
        studentDTO.setDateOfBirth(new Date());
        studentDTO.setPhoneNumber(1234567890L);
        studentDTO.setEnrollmentNumber("ENR12345");
        studentDTO.setUniversitId(1);

        University university = new University();
        university.setName("Test University");

        when(universityRepo.findById(1)).thenReturn(Optional.of(university));
        when(studentRepo.save(any(Student.class))).thenReturn(new Student());
        if(studentDTO!=null){
        studentService.addStudent(studentDTO);

        
        verify(studentRepo, times(1)).save(any(Student.class));
        verify(kafkaTemplate, times(1)).send(eq("student-create-topic"), anyString(), anyString());
    }
    }

    @Test
    public void testAddStudent_UniversityNotFound() {
        StudentCreateDTO studentDTO = new StudentCreateDTO();
        studentDTO.setUniversitId(999);

        
        when(universityRepo.findById(999)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> studentService.addStudent(studentDTO));
    }

    @Test
    public void testUpdateStudent_Success() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(1);
        studentDTO.setName("AAA Updated");
        studentDTO.setAddress("ABC123");
        studentDTO.setDateOfBirth(new Date());
        studentDTO.setPhoneNumber(9876543210L);
        studentDTO.setEnrollmentNumber("ENR678fgn");

        Student existingStudent = new Student();
        existingStudent.setId(1);

        when(studentRepo.findById(1)).thenReturn(Optional.of(existingStudent));
        when(studentRepo.save(any(Student.class))).thenReturn(existingStudent);

        studentService.updateStudent(studentDTO);

        verify(studentRepo, times(1)).save(existingStudent);
        verify(kafkaTemplate, times(1)).send(eq("student-update-topic"), anyString(), anyString());
    }

    @Test
    public void testUpdateStudent_StudentNotFound() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(1);

        when(studentRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> studentService.updateStudent(studentDTO));
    }

    @Test
    public void testDeleteStudent_Success() {
        Integer studentId = 1;

        doNothing().when(studentRepo).deleteById(studentId);

        boolean result = studentService.deleteStudent(studentId);

        
        assertTrue(result);
        verify(studentRepo, times(1)).deleteById(studentId);
        verify(kafkaTemplate, times(1)).send(eq("student-delete-topic"), anyString(), anyString());
    }

    @Test
    public void testDeleteStudent_StudentNotFound() {
        Integer studentId = 1;

        doThrow(new RuntimeException("Student not found")).when(studentRepo).deleteById(studentId);

        assertThrows(CustomException.class, () -> studentService.deleteStudent(studentId));
    }

    @Test
    public void testGetStudentById_Success() {
        Integer studentId = 1;
        Student student = new Student();
        student.setId(studentId);
        student.setName("John Doe");
        University university = new University();
        university.setName("Test University");
        student.setUniversity(university);

        when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));

        StudentDTO studentDTO = studentService.getStudentById(studentId);

        assertNotNull(studentDTO);
        assertEquals("AAA", studentDTO.getName());
        assertEquals("Test University", studentDTO.getUniversityName());
    }

    @Test
    public void testGetStudentById_StudentNotFound() {
        Integer studentId = 1;

        when(studentRepo.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> studentService.getStudentById(studentId));
    }
}
