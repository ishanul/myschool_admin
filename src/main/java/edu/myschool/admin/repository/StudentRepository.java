package edu.myschool.admin.repository;

import edu.myschool.admin.model.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByEmail(String email);
}