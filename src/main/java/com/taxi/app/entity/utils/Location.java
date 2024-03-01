package com.taxi.app.entity.utils;

import com.taxi.app.entity.Booking;
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
import javax.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "location")
public class Location implements Serializable {

    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long locationId;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @OneToOne(mappedBy = "location")
    private Taxi taxi;

    @Override
    public String toString() {
        return "Lat,Long: " + getLatitude() + "," + getLongitude();
    }

    @Override
    public int hashCode() {
        return (int) locationId + (int) latitude + (int) longitude;
    }
}
