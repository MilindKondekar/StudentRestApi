package com.student.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.demo.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

}
