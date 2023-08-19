package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {}
