package com.example.demo.student;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

 private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){

      return studentRepository.findAll();
    }


    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw  new IllegalStateException("Email taken");
        }else
        {
            studentRepository.save(student);
        }

    }

    public void deteleteStudent(Long studentId) {
        boolean studentExist=studentRepository.existsById(studentId);
        if(studentExist==false){

            throw new IllegalArgumentException("Student with id "+studentId+" doesn't exist");

        }
        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId, String studentName, String studentEmail) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalArgumentException("Student with id" +
                studentId+" doesn't exist"));

        if(studentName!=null&&
           studentName.length()>0&&
           !Objects.equals(student.getName(),studentName)
            ){

            student.setName(studentName);
             }

        if(studentEmail!=null&&
                studentEmail.length()>0&&
                !Objects.equals(student.getEmail(),studentEmail)
        ){

            Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentEmail);
            if(studentOptional.isPresent())
            {
                throw new IllegalArgumentException("Email is taken");
            }

            student.setEmail(studentEmail);
        }

    }
}
