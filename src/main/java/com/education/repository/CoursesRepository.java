package com.education.repository;

import com.education.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses, Long> {



    List<Courses> findAllByOrderByCategory();



}