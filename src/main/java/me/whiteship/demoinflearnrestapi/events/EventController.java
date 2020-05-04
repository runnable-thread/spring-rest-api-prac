package me.whiteship.demoinflearnrestapi.events;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value="/api/events", produces= MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController{

    @Autowired
    EventRepository eventRepository;

    @PostMapping
    ResponseEntity getEvent(@RequestBody Event event){
        Event newEvent = this.eventRepository.save(event);
        URI createdURI = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        event.setId(10);
        return ResponseEntity.created(createdURI).body(event);
    }

}