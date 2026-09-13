package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.entity.Participant;
import se331.lab.service.ParticipantService;
import se331.lab.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    final ParticipantService participantService;

    @GetMapping("/participants")
    public ResponseEntity<?> getParticipants() {
        return ResponseEntity.ok(
                LabMapper.INSTANCE.getParticipantDto(participantService.getParticipants())
        );
    }

    @PostMapping("/participants")
    public ResponseEntity<?> addParticipant(@RequestBody Participant participant) {
        Participant saved = participantService.save(participant);
        return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDto(saved));
    }
}