package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;

import se331.lab.service.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<?> getEventList(@RequestParam(value = "_limit",
    required = false)Integer perPage
            ,@RequestParam(value = "_page",required = false)Integer page){
        List<Event> output = null;
        Integer eventSize = eventService.getEventSize();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(eventSize));
        try {
            output = eventService.getEvents(perPage, page);
            return ResponseEntity.ok().headers(responseHeaders).body(output);
        } catch (IndexOutOfBoundsException ex){
            return ResponseEntity.ok().headers(responseHeaders).body(output);
        }
    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable ("id") Long id){
        Event output = eventService.getEvent(id);
        if (output != null){
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"this given id is not found");
        }
    }
}
