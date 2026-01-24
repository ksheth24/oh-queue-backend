package com.example.oh_queue.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "queue_entry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueueEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "section")
    private String section;

    @Column(nullable = false, name = "topic")
    private String topic;

    @Column(nullable = false, name = "in_person")
    private boolean inPerson;

    @Column(nullable = false, updatable = false)
    private Instant joinedAt;

    @PrePersist
    protected void onCreate() {
        this.joinedAt = Instant.now();
    }

    @Column(nullable = false, name = "status")
    private String status;

}
