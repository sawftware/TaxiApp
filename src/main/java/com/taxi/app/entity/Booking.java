package com.taxi.app.entity;

import lombok.Data;
import lombok.Builder;
import java.util.Date;
import lombok.AccessLevel;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import javax.persistence.Temporal;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.taxi.app.entity.utils.Location;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Booking Model/Entity
 *
 * @author alankavanagh
 *
 * Defines the Booking entity used for storing the details of a booking
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(name = "customer", nullable = false)
    private String customer;

    @ManyToOne
    @JoinColumn(name="booking_center_id")
    private BookingCenter bookingCenter;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pickup", referencedColumnName = "location_id")
    private Location pickup;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dropoff", referencedColumnName = "location_id")
    private Location dropoff;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="taxi_id")
    private Taxi taxi;

    @Column(name = "create_dt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDt;

    @Column(name = "assigned_dt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedDt;

    @Column(name = "dropoff_dt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dropoffDt;

    @Override
    public String toString() { return getCustomer(); }

    @Override
    public int hashCode() {
        return (int) bookingId * customer.hashCode();
    }
}