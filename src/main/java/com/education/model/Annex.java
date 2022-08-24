package com.education.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Annex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeAnnex;

    private String annex;


    @ManyToOne
    @JoinColumn(name = "paragraph_id")
    private  Paragraph paragraph;


    public Annex(String typeAnnex, String annex, Paragraph paragraph) {
        this.typeAnnex = typeAnnex;
        this.annex = annex;
        this.paragraph = paragraph;
    }


    @JsonIgnore
    public Paragraph getParagraph() {
        return paragraph;
    }




}
