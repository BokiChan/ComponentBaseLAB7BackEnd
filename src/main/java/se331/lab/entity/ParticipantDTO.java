package se331.lab.entity;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {
    Long id;
    String name;
    String telNo;
    @Builder.Default
    List<EventDTO> eventHistories = new ArrayList<>();
}