package com.taxi.app.entity;

import com.taxi.app.entity.utils.Location;
import lombok.Data;
import java.util.Date;
import lombok.Builder;
import lombok.AccessLevel;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Temporal;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookingId;

    @Column(name = "customer", nullable = false)
    private String customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="booking_center_id")
    private BookingCenter bookingCenter;

    @ManyToOne(cascade = CascadeType.ALL)
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
        return (int) bookingId * customer.hashCode() + createDt.hashCode() + assignedDt.hashCode();
    }
}