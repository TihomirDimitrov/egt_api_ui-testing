package com.egt.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserApiModel {
    //В този случай използването на JsonProperty не е задължително, но е добра практика и го демостираме по този начин.
    @JsonProperty("name")
    private String name;
    @JsonProperty("job")
    private String userJob;
}
