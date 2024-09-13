package in.ashokit;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.Entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

}
