package org.springframework.social.cafe24.api;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * JsonNode로 반환되는 자료들을 담을 클래스가 상속하는 추상 클래스.
 * 미리 Map 타입의 extraData 필드를 준비해서 매핑되지 않은 프로퍼티를 처리한다.
 */
public abstract class Cafe24Object {
    private Map<String, Object> extraData;

    public Cafe24Object() {
        this.extraData = new HashMap<>();
    }

    /**
     * @return Any fields in response from Facebook that are otherwise not mapped to any properties.
     */
    public Map<String, Object> getExtraData() {
        return extraData;
    }

    /**
     * {@link JsonAnySetter} hook. Called when an otherwise unmapped property is being processed during JSON deserialization.
     * @param key The property's key.
     * @param value The property's value.
     */
    protected void add(String key, Object value) {
        extraData.put(key, value);
    }
}
