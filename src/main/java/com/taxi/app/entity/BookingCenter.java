package com.taxi.app.entity;

import lombok.Data;
import java.util.Set;
import lombok.Builder;
import java.util.HashSet;
import lombok.AccessLevel;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

/**
 * BookingCenter Model/Entity
 *
 * @author alankavanagh
 *
 * Defines the BookingCenter entity used for storing the details of a booking center
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "booking_center")
public class BookingCenter implements Serializable {

    @Id
    @Column(name = "booking_center_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingCenterId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy="bookingCenter")
    private Set<Taxi> taxis = new HashSet<>();

    @OneToMany(mappedBy="bookingCenter")
    private Set<Booking> bookings = new HashSet<>();

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        return (int) bookingCenterId * name.hashCode();
    }
}