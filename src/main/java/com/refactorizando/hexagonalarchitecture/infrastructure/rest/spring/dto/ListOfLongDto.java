package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
public class ListOfLongDto {

    @JsonAlias({"movies", "genres"})
    @NotEmpty
    private List<Long> list;
}
