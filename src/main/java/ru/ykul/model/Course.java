package ru.ykul.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="courses")
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id", referencedColumnName="id")
    private Teacher teacher;
}
