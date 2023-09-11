/**
 *
 */
package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
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
public class CompetencyDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
    *
    */
    private long competencyid;
    /**
    *
    */
    @NotNull
    @Size(max = 100)
    private String competencyframeworktitle;
    /**
    *
    */
    @Size(max = 100)
    private String competencyframeworkversion;
    /**
    *
    */
    @Size(max = 100)
    private String competencyframeworkidentifier;
    /**
    *
    */
    private String competencyframeworkdescription;
    /**
    *
    */
    @Size(max = 100)
    private String competencyframeworksubject;
    /**
    *
    */
    private Date competencyframeworkvalidstartdate;
    /**
    *
    */
    private Date competencyframeworkvalidenddate;
    /**
    *
    */
    @Size(max = 100)
    private String competencydefinitionidentifier;
    /**
    *
    */
    private String competencydefinitionidentifierurl;
    /**
    *
    */
    @Size(max = 100)
    private String competencytaxonomyid;
    /**
    *
    */
    private Date competencydefinitionvalidstartdate;
    /**
    *
    */
    private Date competencydefinitionvalideenddate;
    /**
    *
    */
    @Size(max = 100)
    private String competencydefinitionparentidentifier;
    /**
    *
    */
    private String competencydefinitionparenturl;
    /**
    *
    */
    @Size(max = 100)
    private String competencydescriptionparentcode;
    /**
    *
    */
    @Size(max = 100)
    private String competencydefinitioncode;
    /**
    *
    */
    private String competencydefinitionstatement;
    /**
    *
    */
    private String competencydefinitiontypeurl;
    /**
    *
    */
    @Size(max = 100)
    private String competencydefinitiontype;
    /**
    *
    */
    @Size(max = 10)
    private String recordstatus;
}
