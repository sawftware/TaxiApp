package com.taxi.app.entity;

import lombok.Data;
import java.util.Set;
import lombok.Builder;
import java.util.HashSet;
import lombok.AccessLevel;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.taxi.app.entity.security.User;
import com.taxi.app.entity.utils.Location;

/**
 * Taxi Model/Entity
 *
 * @author alankavanagh
 *
 * Defines the Taxi entity used for storing the details of a taxi
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "taxi")
public class Taxi implements Serializable, Comparable<Taxi> {

    @Id
    @Column(name = "taxi_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(mappedBy = "taxi")
    private User user;

    @Override
    public String toString() { return getRegistration(); }

    @Override
    public int compareTo(final Taxi taxi) {
        return Integer.compare(this.getBookings().size(), taxi.getBookings().size());
    }

    @Override
    public int hashCode() {
        return (int) taxiId + registration.hashCode() + isAvailable.hashCode();
    }
}
