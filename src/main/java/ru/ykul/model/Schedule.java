package ru.ykul.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="schedule")
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="group_id", referencedColumnName = "id")
    private Group group;
    @ManyToOne
    @JoinColumn(name="teacher_id", referencedColumnName = "id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private Course course;
    @Column(name="start_date")
    LocalDateTime startDate;
    @Column(name="end_date")
    LocalDateTime endDate;
}
