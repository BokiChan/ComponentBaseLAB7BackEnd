package se331.lab.service;

import se331.lab.entity.Participant;
import java.util.List;

public interface ParticipantService {
    List<Participant> getParticipants();
    Participant getParticipant(Long id);
    Participant save(Participant participant);
}