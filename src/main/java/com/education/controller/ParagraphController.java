package com.education.controller;

import com.education.model.Paragraph;
import com.education.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/paragraph")
@CrossOrigin("*")
public class ParagraphController {

    @Autowired
    private ParagraphService paragraphService;


    @PostMapping("/add")
    public ResponseEntity<Paragraph> addParagraph(@RequestBody Paragraph paragraph){

        return ResponseEntity.ok(paragraphService.addParagraph(paragraph));
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<List<Paragraph>> listParagraph(@PathVariable Long course_id){
        return ResponseEntity.ok(paragraphService.getAllParagraph(course_id));
    }
    @DeleteMapping("{paragraph_id}")
    public ResponseEntity<?> deleteParagraphById(@PathVariable Long paragraph_id){
        paragraphService.deleteParagraph(paragraph_id);
        return ResponseEntity.ok("deleted");
    }



}
