package com.deloitte.elrr.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Location extends Auditable<String> {
    
    @Column(name = "street_number_and_name")
    private String streetNumberAndName;

    @Column(name = "apartment_room_suite_number")
    private String apartmentRoomSuiteNumber;
    
    @Column(name = "city")
    private String city;

    @Column(name = "state_abbreviation")
    private String stateAbbreviation;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "county")
    private String county;

    @Column(name = "country_code")
    private String countryCode;
    
    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Override
    public String toString() {
        return "Location [streetNumberAndName=" + streetNumberAndName + ", apartmentRoomSuiteNumber="
                + apartmentRoomSuiteNumber + ", id=" + id + ", city=" + city + ", stateAbbreviation="
                + stateAbbreviation + ", postalCode=" + postalCode + ", county=" + county + ", countryCode="
                + countryCode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }

}
