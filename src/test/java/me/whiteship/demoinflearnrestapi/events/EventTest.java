package me.whiteship.demoinflearnrestapi.events;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API DEVELOPMENT with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){
        Event event = new Event();
        String name = "Name";

        event.setName(name);
        String description = "Spring";
        event.setDescription(description);

        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }

    @Test
    public void testFree(){

        //given
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();


        //when
        event.update();

        //then
        assertThat(event.isFree()).isEqualTo(true);


        //given
        event = Event.builder()
                .basePrice(100)
                .maxPrice(0)
                .build();


        //when
        event.update();

        //then
        assertThat(event.isFree()).isEqualTo(false);

    }
    @Test
    public void testOffline(){
        //given
        Event event = Event.builder()
                .location("D2")
                .build();


        //when
        event.update();

        //then
        assertThat(event.isOffline()).isEqualTo(true);

        //given
        event = Event.builder()
                .build();


        //when
        event.update();

        //then
        assertThat(event.isOffline()).isEqualTo(false);


    }
}