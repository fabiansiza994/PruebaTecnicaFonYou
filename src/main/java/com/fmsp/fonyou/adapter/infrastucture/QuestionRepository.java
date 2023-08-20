package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
