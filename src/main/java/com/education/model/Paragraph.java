package com.education.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paragraph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private  String title;
    private StringBuilder paragraph;


    @ManyToOne
    @JoinColumn(name = "courses_id",nullable = false)
    private Courses courses ;

    @OneToMany(mappedBy ="paragraph",cascade = {CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<Annex> annexSet =new LinkedList<>();

    @OneToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Paragraph parent;

    @OneToMany(mappedBy = "parent",cascade = {CascadeType.ALL }, fetch = FetchType.EAGER)
    private  Set<Paragraph> children = new HashSet<>();




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paragraph paragraph1 = (Paragraph) o;

        if (id != null ? !id.equals(paragraph1.id) : paragraph1.id != null) return false;
        if (!title.equals(paragraph1.title)) return false;
        if (!paragraph.equals(paragraph1.paragraph)) return false;
        if (!courses.equals(paragraph1.courses)) return false;
        if (!annexSet.equals(paragraph1.annexSet)) return false;
        if (!parent.equals(paragraph1.parent)) return false;
        return children.equals(paragraph1.children);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;

        result = 31 * result + paragraph.hashCode();

        return result;
    }



}