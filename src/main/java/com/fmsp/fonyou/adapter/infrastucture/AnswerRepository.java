package com.fmsp.fonyou.adapter.infrastucture;

import com.fmsp.fonyou.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByIdAndQuestion_Id(Long answerId, Long questionId);
}
