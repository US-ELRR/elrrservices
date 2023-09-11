/**
 *
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author mnelakurti
 *
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter

public class PersonDto implements Serializable {

    /**
     *
    */
    private static final long serialVersionUID = -8031955138252824918L;
    /**
    *
    */

    private long personid;
    /**
    *
    */
    @Size(max = 250)
    private String name;
    /**
    *
    */
    @Size(max = 50)
    private String firstName;
    /**
    *
    */
    @Size(max = 50)
    private String middleName;
    /**
    *
    */
    @Size(max = 50)
    private String lastName;
    /**
    *
    */
    @Size(max = 50)
    private String namePrefix;
    /**
    *
    */
    @Size(max = 50)
    private String titleAffixcode;
    /**
    *
    */
    @Size(max = 50)
    private String nameSuffix;
    /**
    *
    */
    @Size(max = 50)
    private String qualificationAffixcode;
    /**
    *
    */
    @Size(max = 50)
    private String maidenName;
    /**
    *
    */
    @Size(max = 50)
    private String preferredName;
    /**
    *
    */
    private String humanResourceIdentifier;
    /**
    *
    */
    private String personnelIdentificationSystem;
    /**
    *
    */
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthdate;
    /**
    *
    */
    @Size(max = 1)
    private String sex;
    /**
    *
    */
    @Size(max = 50)
    private String primaryLanguage;
    /**
    *
    */
    @Size(max = 1)
    private String militaryVeteranindicator;
    /**
    *
    */
    @Size(max = 10)
    private String recordStatus;

    /**
     *
     */
    @Override
    public String toString() {
        return "PersonDto [id=" + personid + ", name=" + name + ",firstName="
                + firstName + ", middleName=" + middleName + ",lastName="
                + lastName + ",  namePrefix=" + namePrefix
                + ",   titleAffixcode=" + titleAffixcode + ",nameSuffix="
                + nameSuffix + ",qualificationAffixcode="
                + qualificationAffixcode + ",maidenName=" + maidenName
                + ",preferredName=" + preferredName
                + ", humanResourceIdentifier=" + humanResourceIdentifier
                + ", personnelIdentificationSystem="
                + personnelIdentificationSystem
                + ", sex=" + sex + ", primaryLanguage=" + primaryLanguage
                + ", militaryVeteranindicator=" + militaryVeteranindicator
                + ",birthdate=" + birthdate
                + ",recordStatus=" + recordStatus + "]";
    }
}
