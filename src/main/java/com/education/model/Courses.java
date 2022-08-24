package com.education.model;

import com.education.exporter.DownloadImage;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    private String courseDescription;

    private String image;

    private float price ;

    @Transient
    private  String coursesImages;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category ;



    @OneToMany(mappedBy = "paragraph", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<Paragraph> paragraphs =new HashSet<>();



    public String getCoursesImages() throws Exception {
        if(image!=null) {
            return DownloadImage.getImage("Courses-images/", image, id);
        }
        return null;
    }

}

