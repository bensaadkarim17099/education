package com.education.service;

import com.education.model.Paragraph;
import com.education.repository.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ParagraphService {

    @Autowired
    private ParagraphRepository paragraphRepository;


    public Paragraph addParagraph(Paragraph paragraph) {

        paragraph.getAnnexSet().forEach(el-> el.setParagraph(paragraph) );
        savedParagraph(paragraph);
        return paragraph;

    }

    private void savedParagraph(Paragraph paragraph) {

        Paragraph savedParagraph ;
        if (paragraph.getChildren() != null) {

            LinkedList<Paragraph> paragraph1=new LinkedList<>();
            //Set<Paragraph> paragraph1=new HashSet<>();

            paragraph.getChildren().forEach(el->paragraph1.add(el));
            paragraph.setChildren(null);
            paragraph.getAnnexSet().forEach(el-> {
                System.out.println(paragraph.getTitle());
                System.out.println(el.getAnnex());
                System.out.println("*************************");
                el.setParagraph(paragraph);
            });
            savedParagraph = paragraphRepository.save(paragraph);
            for (Paragraph el : paragraph1) {
                Paragraph paragraph2=new Paragraph();
                paragraph2.setId(savedParagraph.getId());
                el.setParent(paragraph2);
                savedParagraph(el);
            }
        } else {
             paragraphRepository.save(paragraph);
        }
    }

    public List<Paragraph> getAllParagraph(Long course_id) {
         return paragraphRepository.findByCoursesIdAndParentIdIsNull(course_id);
    }

    public void deleteParagraph(Long paragraph_id) {
        paragraphRepository.deleteById(paragraph_id);
    }

}


