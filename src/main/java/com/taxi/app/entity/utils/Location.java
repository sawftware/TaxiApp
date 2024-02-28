package com.taxi.app.entity.utils;

import lombok.Data;
import lombok.Builder;
import lombok.AccessLevel;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import com.taxi.app.entity.Taxi;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @Column(name = "location_id")
    @GeneratedValue(generator = "location-sg")
    @GenericGenerator(
            name = "location-sg",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "location_sequence"),
                    @Parameter(name = "initial_value", value = "3"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long locationId;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @OneToOne(mappedBy = "location")
    private Taxi taxi;

    public String toString() {
        return "Lat,Long: " + getLatitude() + ", " + getLongitude();
    }

}
