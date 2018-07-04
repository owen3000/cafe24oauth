package org.springframework.social.cafe24.api.impl.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Cafe24ObjectMixin {

    @JsonAnySetter
    abstract void add(String key, Object value);
}
