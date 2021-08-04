package com.example.entrylistServer.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "visitant")
public class VisitantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "residence")
    private String residence;

    @CreationTimestamp
    @Column(name = "visit_time")
    private Timestamp visit_time;

    public VisitantEntity(String phone_number, String residence) {
        this.phone_number = phone_number;
        this.residence = residence;
    }
}
