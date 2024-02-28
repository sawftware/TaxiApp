package com.taxi.app.entity;

import lombok.Data;
import java.util.Set;
import lombok.Builder;
import java.util.HashSet;
import lombok.AccessLevel;
import javax.persistence.Id;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import com.taxi.app.entity.utils.Location;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "taxi")
public class Taxi implements Serializable {

    @Id
    @Column(name = "taxi_id")
    @GeneratedValue(generator = "taxi-sg")
    @GenericGenerator(
            name = "taxi-sg",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "taxi_sequence"),
                    @Parameter(name = "initial_value", value = "3"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long taxiId;

    @Column(name="registration", nullable = false)
    private String registration;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="booking_center_id")
    private BookingCenter bookingCenter;

    @OneToMany(mappedBy="taxi")
    private Set<Booking> bookings = new HashSet<>();

}
