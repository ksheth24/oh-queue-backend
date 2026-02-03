package com.example.oh_queue.controller;

import com.example.oh_queue.entity.QueueEntry;
import com.example.oh_queue.service.QueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    public record AddRequest(String name, String section, String topic, String location) {}

    public record UpdateRequest(Long id, String status) {}

    @PostMapping("/add")
    public Long add(@RequestBody AddRequest addRequest) {
        return queueService.add(addRequest.name, addRequest.section, addRequest.topic, addRequest.location);
    }

    @GetMapping("/getQueue")
    public List<QueueEntry> queue() {
        return queueService.getQueue();
    }

    @GetMapping("/getQueueLength")
    public Integer getQueueLength() {
        return queueService.getQueue().size();
    }

    @GetMapping("/getQueueSpot/{id}")
    public Integer queueSpot(@PathVariable Long id) {
        return queueService.getQueueSpot(id);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody UpdateRequest updateRequest) {
        queueService.updateRequest(updateRequest.id, updateRequest.status);
        return ResponseEntity.ok()
                .body(Map.of("message", "Entry added to queue!"));
    }
}
