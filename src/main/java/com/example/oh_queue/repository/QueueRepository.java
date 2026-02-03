package com.example.oh_queue.repository;

import com.example.oh_queue.entity.QueueEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface QueueRepository extends JpaRepository<QueueEntry, Long> {
    @Query(value = "SELECT * FROM queue", nativeQuery = true)
    List<QueueEntry> findEntireQueue();

    Optional<QueueEntry> findById(Long id);

    @Query(value = """
    SELECT count(*) FROM queue WHERE joined_at < :joinedAt
    """, nativeQuery = true)
    Integer getQueueSpot(@Param("joinedAt") Instant joinedAt);


}
