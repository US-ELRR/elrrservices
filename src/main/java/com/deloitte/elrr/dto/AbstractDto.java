package com.deloitte.elrr.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public abstract class AbstractDto implements Serializable {

    protected static final long serialVersionUID = -8031955138252824918L;

    protected UUID id;

}
