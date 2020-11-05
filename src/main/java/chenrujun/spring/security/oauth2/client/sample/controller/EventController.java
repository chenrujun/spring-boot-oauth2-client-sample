package chenrujun.spring.security.oauth2.client.sample.controller;

import chenrujun.spring.security.oauth2.client.sample.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * Scopes to access shared calendars Office 365 and Outlook.com calendars support sharing. A user who created a calendar
 * can share the calendar with other users. The following scopes are required to access a calendar that has been shared
 * with that user:
 * <p>
 * For read access: https://outlook.office.com/calendars.read.shared For read/write access:
 * https://outlook.office.com/calendars.readwrite.shared
 */
@RestController
@RequestMapping(path = { "/events" })
public class EventController {

    private final String graphBaseUrl = "https://graph.microsoft.com/v1.0%s";
    private final WebClient webClient;

    @Autowired
    public EventController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    Mono<?> getEvents() {
        // TODO: 2020/11/2 Get the Events

        return this.webClient.get()
                             .uri(URI.create(this.getGraphUrl("/me/events")))
                             .retrieve()
                             .bodyToMono(String.class);
    }

    @PostMapping(path = { "/{eventId}/accept" })
    void acceptEvent(@PathVariable String eventId, @PathVariable String attenderId) {
        // TODO: 2020/11/2 Accept the event

        this.webClient.post()
                      .uri(this.getGraphUrl("/me/events/{event_id}/accept"), eventId)
                      .retrieve()
                      .bodyToMono(String.class)
                      .subscribe(System.out::println);
    }

    @PostMapping
    void createEvent(Event event) {
        // TODO: 2020/11/2 Create the event

        // easy to test
        if (event == null) {
            event = Event.buildEvent("Test event", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(1).plusHours(1));
            //.addAttender("User", "user@augend.onmicrosoft.com", true);
        }

        this.webClient.post()
                      .uri(URI.create(this.getGraphUrl("/me/events")))
                      .contentType(MediaType.APPLICATION_JSON)
                      .bodyValue(event)
                      .retrieve()
                      .bodyToMono(String.class)
                      .subscribe(System.out::println);
    }

    private String getGraphUrl(String uri) {
        return String.format(this.graphBaseUrl, uri);
    }
}
