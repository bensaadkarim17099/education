package com.education.controller;

import com.education.exporter.FileUploadUtil;
import com.education.model.Courses;
import com.education.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin("*")
public class CoursesController {
    public static final String srcImg = "/home/karim/Bureau/education/src/main/resources/static/Courses-images/";
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Courses>> listAllCourses(){
        return ResponseEntity.ok(courseService.listAllCourses());
    }




    @PostMapping("/addcourses")
    public ResponseEntity<?> addCourses(@RequestParam(name = "file") MultipartFile file,
                                        @RequestParam(name = "courses") String courseStr) throws JsonProcessingException {
        System.out.println(file.getOriginalFilename());
        ObjectMapper objectMapper = new ObjectMapper();
        Courses courses=objectMapper.readValue(courseStr,Courses.class);
        try {
            Courses savedCourse=new Courses();
            if(file!=null){
                String fileName= StringUtils.cleanPath(file.getOriginalFilename());
                courses.setImage(fileName);
                savedCourse=courseService.addCourses(courses);
                String uploadDir= srcImg +savedCourse.getId();
                FileUploadUtil.cleanDir(uploadDir);
                FileUploadUtil.saveFile(uploadDir,fileName,file);
            }else{
                savedCourse=courseService.addCourses(courses);
            }
            return ResponseEntity.ok(savedCourse);
        }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/{course_id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long course_id){

        try{
            courseService.deleteCourse(course_id);
            String courseDir=srcImg+course_id;
            FileUploadUtil.removeDir(courseDir);
            return new ResponseEntity<>("Courses deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Could not find any courses with id "+course_id,HttpStatus.OK);

        }




    }

}
