package com.SWE_photoshoot_booking.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "photoshoots")
public class PhotoshootEntity {

    @Id
    private UUID photoshootID;

    private String name;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity photographer;

    @PrePersist
    public void generateUUID() {
        if (photoshootID == null) {
            photoshootID = UUID.randomUUID();
        }
    }

}
