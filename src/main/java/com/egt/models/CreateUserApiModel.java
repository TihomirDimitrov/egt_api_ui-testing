package com.egt.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserApiModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String job;
}
