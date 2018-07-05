package org.springframework.social.cafe24.api;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * {@link Cafe24}를 구현하는 {@link org.springframework.social.cafe24.api.impl.Cafe24Template}에서 사용할
 * 범용 API 호출 메서드를 정의하는 인터페이스
 */
interface Cafe24Api {


    <T> List<T> fetchObjects(String connectionType, Class<T> type, MultiValueMap<String, String> params);
    String fetchScripttags(String endPoint);
    String fetchAllScripttags(String endPoint);
    void fetchDeleteScripttag(String endPoint, String scriptNo);
    
    String fetchScripttags(HttpMethod httpMethod, String endPoint, String data);
}
