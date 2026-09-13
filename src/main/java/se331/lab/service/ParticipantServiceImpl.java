package se331.lab.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.dao.EventDao;
import se331.lab.dao.ParticipantDao;
import se331.lab.entity.Event;
import se331.lab.entity.Participant;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    final ParticipantDao participantDao;
    final EventDao eventDao;

    @Override
    public List<Participant> getParticipants() {
        return participantDao.getParticipants();
    }

    @Override
    public Participant getParticipant(Long id) {
        return participantDao.getParticipant(id);
    }

    @Override
    @Transactional
    public Participant save(Participant participant) {
        if (participant.getEventHistories() != null && !participant.getEventHistories().isEmpty()) {
            List<Event> resolved = new ArrayList<>();
            for (Event e : participant.getEventHistories()) {
                if (e.getId() == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Each event needs an id");
                }
                Event found = eventDao.getEvent(e.getId());
                if (found == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event id " + e.getId() + " not found");
                }
                resolved.add(found);
            }
            participant.setEventHistories(resolved);
        }
        return participantDao.save(participant);
    }
}