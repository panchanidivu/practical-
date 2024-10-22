package com.practical.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.practical.dto.StudentCreateDTO;
import com.practical.dto.StudentDTO;
import com.practical.model.AuditLog;
import com.practical.model.Student;
import com.practical.model.University;
import com.practical.repository.AuditLogRepo;
import com.practical.repository.StudentRepo;
import com.practical.repository.UniversityRepo;
import com.practical.response.CustomException;

@Service
public class StudentService implements StudentServiceImpl {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    UniversityRepo universityRepo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    EventConsumers consumers;

    @Autowired
    private AuditLogRepo auditLogRepository;

    @Override
    public List<StudentDTO> getAllStudent() {
        List<Student> students= studentRepo.findAll();
        List<StudentDTO> studentDto= new ArrayList<StudentDTO>();
        for (Student student : students) {
            StudentDTO std= new StudentDTO();
            std.setId(student.getId());
            std.setName(student.getName());
            std.setAddress(student.getAddress());
            std.setDateOfBirth(student.getDateOfBirth());
            std.setPhoneNumber(student.getPhoneNumber());
            std.setEnrollmentNumber(student.getEnrollmentNumber());
            std.setUniversityName(student.getUniversity().getName());
            studentDto.add(std);
        }
        return studentDto;
    }

    @Override
    public StudentCreateDTO addStudent(StudentCreateDTO studentDTO) {
       if(studentDTO !=null ){
        Student student= new Student();
        student.setName(studentDTO.getName());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setAddress(studentDTO.getAddress());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setEnrollmentNumber(studentDTO.getEnrollmentNumber());
        Optional<University> university = universityRepo.findById(studentDTO.getUniversitId());
        student.setUniversity(university.get());
        studentRepo.save(student);
        
        kafkaTemplate.send("student-create-topic", student.getId().toString(), student.toString());
        saveAuditLog("CREATE", student.toString());

       }
    return studentDTO;
    }

    @Override
    public StudentDTO getStudentById(Integer id) {

        Optional<Student> student = studentRepo.findById(id);
        if(student == null) {
                throw new CustomException("Student not found", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
        StudentDTO studentDTO= this.dtoConverst(student.get());
        return studentDTO;
    }

    private StudentDTO dtoConverst(Student student) {
        StudentDTO studentDTO= new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setUniversityName(student.getUniversity().getName());
        studentDTO.setUniversity(student.getUniversity());
        studentDTO.setEnrollmentNumber(student.getEnrollmentNumber());
        return studentDTO;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Optional<Student> student= studentRepo.findById(studentDTO.getId());
        if(!student.isPresent()) {
            throw new CustomException("Student not found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        Student std= student.get();
        std.setAddress(studentDTO.getAddress());
        std.setDateOfBirth(studentDTO.getDateOfBirth());
        std.setName(studentDTO.getName());
        std.setEnrollmentNumber(studentDTO.getEnrollmentNumber());
        std.setPhoneNumber(studentDTO.getPhoneNumber());
        std.setUniversity(studentDTO.getUniversity());
        this.studentRepo.save(std);
        StudentDTO studentDTo= this.dtoConverst(std);
        kafkaTemplate.send("student-update-topic", studentDTo.getId().toString(), studentDTo.toString());
        saveAuditLog("UPDATE", studentDTo.toString());


        return studentDTo;

    }

    @Override
    public boolean deleteStudent(Integer id) {
        try {
            studentRepo.deleteById(id);
            kafkaTemplate.send("student-delete-topic", id.toString(), id.toString());
            saveAuditLog("DELETE", id.toString());
            return true;
        } catch (Exception e) {
            throw new CustomException("Student not deleted", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
    }

    private void saveAuditLog(String eventType, String details) {
        AuditLog log = new AuditLog();
        log.setEventType(eventType);
        log.setEntityId(extractEntityId(details));
        log.setDetails(details);
        log.setTimestamp(new Date());
        auditLogRepository.save(log);
    }

    private String extractEntityId(String details) {
        // Example logic for extracting entity ID
        return details.split(",")[0];  // Adjust according to your message format
    }

    




   
    
}
