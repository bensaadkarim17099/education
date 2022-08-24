package com.education.repository;

import com.education.model.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {


    List<Paragraph> findByCoursesIdAndParentIdIsNull(Long courses_id);

    @Modifying
    void deleteByCoursesId(Long courses_id);

}