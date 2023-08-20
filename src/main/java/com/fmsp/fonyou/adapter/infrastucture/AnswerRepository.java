package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
