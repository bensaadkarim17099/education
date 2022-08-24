package com.education.service;

import com.education.model.Courses;
import com.education.model.Paragraph;
import com.education.repository.CoursesRepository;
import com.education.repository.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private ParagraphRepository paragraphRepository;



    public List<Courses> listAllCourses(){
        return  coursesRepository.findAll();

    }

    public Courses addCourses(Courses courses) throws Exception {
        Courses savedCourses=new Courses();
        try{
             savedCourses= coursesRepository.save(courses);
        }catch (Exception e){
            throw new Exception("Bad Request");
        }
        return savedCourses;
    }


    public void deleteCourse(Long course_id)  {
        Courses coursesDB=coursesRepository.findById(course_id).get();
        List<Paragraph> paragraphs =paragraphRepository.findByCoursesIdAndParentIdIsNull(course_id);
        paragraphRepository.deleteAll(paragraphs);

        coursesRepository.delete(coursesDB);

    }

}
