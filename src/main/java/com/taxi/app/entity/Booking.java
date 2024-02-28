package com.taxi.app.entity;

import lombok.Data;
import java.util.Date;
import lombok.Builder;
import lombok.AccessLevel;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Temporal;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator = "booking-sg")
    @GenericGenerator(
            name = "booking-sg",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "booking_sequence"),
                    @Parameter(name = "initial_value", value = "9"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
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

}