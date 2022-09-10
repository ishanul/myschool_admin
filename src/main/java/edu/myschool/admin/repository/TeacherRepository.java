package edu.myschool.admin.repository;

import edu.myschool.admin.model.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    public Teacher findByEmail(String email);
}
