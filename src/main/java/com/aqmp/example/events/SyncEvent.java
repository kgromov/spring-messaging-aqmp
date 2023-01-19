package com.aqmp.example.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncEvent implements Serializable {
    private static final long serialVersionUID = -2338626292552177485L;

    private UUID id;
    private OffsetDateTime timestamp;

   public static SyncEvent createDefault() {
       return new SyncEvent(UUID.randomUUID(), OffsetDateTime.now());
   }
}
