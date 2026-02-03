package com.example.oh_queue.service;

import com.example.oh_queue.entity.QueueEntry;
import com.example.oh_queue.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {
    private final QueueRepository queueRepository;

    public QueueService(QueueRepository queueRepository) {
        this.queueRepository =  queueRepository;
    }

    public Long add(String name, String section, String topic, String location) {
        boolean inPerson = true;
        if (location.equals("Online")) {
            inPerson = false;
        }
        QueueEntry queueEntry = new QueueEntry();
        queueEntry.setName(name);
        queueEntry.setSection(section);
        queueEntry.setTopic(topic);
        queueEntry.setInPerson(inPerson);
        queueEntry.setStatus("Queued");
        queueRepository.save(queueEntry);
        return queueEntry.getId();
    }

    public List<QueueEntry> getQueue() {
        return queueRepository.findEntireQueue();
    }

    public Integer getQueueSpot(Long id) {
        QueueEntry entry = queueRepository.findById(id).orElseThrow();
        return queueRepository.getQueueSpot(entry.getJoinedAt());
    }

    public void updateRequest(Long id, String status) {
        QueueEntry queueEntry = queueRepository.findById(id).orElseThrow();
        queueEntry.setStatus(status);
        queueRepository.save(queueEntry);
    }
}
