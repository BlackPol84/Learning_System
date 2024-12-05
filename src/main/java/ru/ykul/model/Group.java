package ru.ykul.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="groups")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id", referencedColumnName="id")
    private Course course;
    @OneToMany(mappedBy = "group")
    private List<Student> students;
}
