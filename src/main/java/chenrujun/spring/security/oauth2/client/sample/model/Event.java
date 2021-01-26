package chenrujun.spring.security.oauth2.client.sample.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@JsonNaming(value = PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Event {

    private final Set<Attender> attendees = new HashSet<>();
    private Content body = new Content();
    private EndDateTime end;
    private StartDateTime start;
    private String subject;

    private Event(String subject, LocalDateTime start, LocalDateTime end) {
        this.subject = subject;
        this.start = new StartDateTime(start);
        this.end = new EndDateTime(end);
    }

    public Set<Attender> getAttendees() {
        return this.attendees;
    }

    public Content getBody() {
        return body;
    }

    public void setBody(Content body) {
        this.body = body;
    }

    public EndDateTime getEnd() {
        return end;
    }

    public void setEnd(EndDateTime end) {
        this.end = end;
    }

    public StartDateTime getStart() {
        return start;
    }

    public void setStart(StartDateTime start) {
        this.start = start;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Event addAttender(String name, String address, boolean isRequired) {
        this.attendees.add(new Attender(name, address, isRequired));
        return this;
    }

    public static Event buildEvent(String subject, LocalDateTime start, LocalDateTime end) {
        return new Event(subject, start, end);
    }

    @JsonNaming(value = PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    private class Attender {
        private EmailAddress emailAddress;
        private String type = "Required";

        public Attender(String name, String address, boolean isRequired) {
            this.emailAddress = new EmailAddress(name, address);
            if (!isRequired) {
                this.type = "";
            }
        }

        public EmailAddress getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(EmailAddress emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @JsonNaming(value = PropertyNamingStrategy.UpperCamelCaseStrategy.class)
        private class EmailAddress {
            private String address;
            private String name;

            public EmailAddress(String name, String address) {
                this.name = name;
                this.address = address;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    @JsonNaming(value = PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    private class Content {
        private String content = "I think it will meet our requirements!";
        private String contentType = "HTML";

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }
    }

    @JsonNaming(value = PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    private class EndDateTime {

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime dateTime;
        private String timeZone = "UTC";

        public EndDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }
    }

    @JsonNaming(value = PropertyNamingStrategy.UpperCamelCaseStrategy.class)
    private class StartDateTime {

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime dateTime;
        private String timeZone = "UTC";

        public StartDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }
    }

}
