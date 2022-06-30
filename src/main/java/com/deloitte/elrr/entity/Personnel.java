package com.deloitte.elrr.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Personnel {
    /**
    *
    */

    private Person person;
    /**
    *
    */
    private Organization organization;
    /**
    *
    */
    private ContactInformation contactInformation;
    /**
    *
    */
    private List<Employment> employment;

    /**
     *
     */
    @Override
    public String toString() {
        return "Personnel [personal=" + person + ", organization="
                + organization + ", employment=" + employment + "]";
    }
}
