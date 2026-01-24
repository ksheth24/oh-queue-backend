package com.example.oh_queue.repository;

import com.example.oh_queue.entity.QueueEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QueueRepository extends JpaRepository<QueueEntry, Long> {
    @Query(value = "SELECT * FROM queue", nativeQuery = true)
    List<QueueEntry> findEntireQueue();
}
