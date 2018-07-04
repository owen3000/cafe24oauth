package org.springframework.social.cafe24.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.cafe24.api.Cafe24;
import org.springframework.social.cafe24.api.ProductOperations;
import org.springframework.social.cafe24.api.impl.json.Cafe24Module;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * {@link AbstractOAuth2ApiBinding}을 상속하고 {@link Cafe24}를 구현.<br>
 * Controller에서 사용할 API의 인스턴스
 *
 */
public class Cafe24Template extends AbstractOAuth2ApiBinding implements Cafe24 {

	private static final Logger logger = LoggerFactory.getLogger(Cafe24Template.class);


	private final String mallId;

	private ObjectMapper objectMapper;

	private ProductOperations productOperations;

	public Cafe24Template(String accessToken) {
		this(accessToken, null);
	}

	static {
		logger.debug("Cafe24Template called...");
	}

	public Cafe24Template(String accessToken, String mallId) {
		/* AUTHORIZATION_HEADER를 사용하면 Authorization 헤더에 액세스 토큰 함께 전달. */
		/* OAuth2.0 버전이 Bearer로 "Authorization: Bearer {accessToken}"으로 전달된다 */
		super(accessToken, TokenStrategy.AUTHORIZATION_HEADER);
		this.mallId = mallId;
		logger.debug("mallId: " + this.mallId);
		initialize();
	}

	/**<ol>
	 * <li>ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory())를 RequestFactory에 설정하여
	 * response를 반복하여 읽을 수 있다. </li>
	 * <li>initSubApi() 실행.</li>
	 * </ol>
	 */
	private void initialize() {
		logger.debug("initialize started...");
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApi();
	}

	/**
	 * {@link org.springframework.social.cafe24.api.Cafe24Api}에 정의된 operations들을 구현한
	 * 각 template의 인스턴스를 생성.
	 */
	public void initSubApi() {
		logger.debug("initSubApi started...");
		productOperations = new ProductTemplate(this);
	}


	/**
	 * @return api를 호출할 mallId에 해당하는 Url 생성
	 */
	public String getBaseApiUrl() {
		logger.debug("getBaseApiUrl: " + "https://" + this.mallId + ".cafe24api.com/api/v2/admin");
		return "https://" + this.mallId + ".cafe24api.com/api/v2/admin";
	}

	//https://{{mallid}}.cafe24api.com/api/v2/admin/products

	/**
	 *
	 *
	 * @param connectionType products, orders 등 api를 호출할 곳 지정
	 * @param type api 호출 결과를 반환 받을 타입 지정. (ex: {@link org.springframework.social.cafe24.api.Product})
	 * @param params api 호출할 때 함께 전달할 쿼리 스트링을
	 * @param <T>
	 * @return
	 */
	public <T> List<T> fetchObjects(String connectionType, Class<T> type, MultiValueMap<String, String> params) {
		logger.debug("fetchObjects called...");

		/* connectionType이 있다면 baseUrl에 붙인다. */
		String connectionPath = connectionType != null && connectionType.length() > 0 ? "/" + connectionType : "";
		logger.debug("fetchObjects connectionPath: " + connectionPath);
		String uri = getBaseApiUrl() + connectionPath;
		logger.debug("fetchObjects uri: " + uri);


		/* 여기서 멈춤. 왜? fileds가 null인 경우 length가 없었기 때문.*/
		MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();


		/* URI 만들기 */
		URIBuilder uriBuilder = URIBuilder.fromUri(uri).queryParams(queryParameters);
		logger.debug("fetchObjects uriBuilder created...");

		logger.debug("fetchObjects uriBuilder.build().toString(): "  + uriBuilder.build().toString());
		logger.debug("fetchObjects uriBuilder.build().getPath(): "  + uriBuilder.build().getPath());
		logger.debug("fetchObjects uriBuilder.build().getHost(): "  + uriBuilder.build().getHost());
		logger.debug("fetchObjects uriBuilder.build().getScheme(): "  + uriBuilder.build().getScheme());
		logger.debug("fetchObjects uriBuilder.build().getUserInfo(): "  + uriBuilder.build().getUserInfo());
		logger.debug("fetchObjects uriBuilder.build().getAuthority(): "  + uriBuilder.build().getAuthority());
		logger.debug("fetchObjects uriBuilder.build().getFragment(): "  + uriBuilder.build().getFragment());

		/* restTemplate으로 통신할 때 사용할 헤더 설정 */
		HttpHeaders headers = new HttpHeaders();

		/* 한글이 섞여있기 때문에 application/json;charset=UTF-8로 Content-Type 설정 */
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		/* MultiValueMap. 제네릭은 body의 타입으로 지정한다. header는 무조건 MultiValueMap이다. */
		HttpEntity<MultiValueMap<String, String>> httpEntity;
		if (params == null) {
			httpEntity = new HttpEntity<>(null, headers);
		} else {
			httpEntity = new HttpEntity<>(params, headers);
		}

		/* RestTemplate을 쓰면 URLEncoder.encode(itemIds, "UTF-8");이 된다 */
		/* API를 실제로 요청하는 부분. ResponseEntity<반환 받을 타입>으로 지정 */
		ResponseEntity<JsonNode> responseEntity
				/* exchange의 매개변수는 (uri, 통신 방식, HttpEntity, 반환 받을 타입) */
				= getRestTemplate().exchange(uriBuilder.build(), HttpMethod.GET, httpEntity, JsonNode.class);
		logger.debug("fetchObjects responseEntity getStatusCode: "  + responseEntity.getStatusCode());
		logger.debug("fetchObjects responseEntity getStatusCodeValue: "  + responseEntity.getStatusCodeValue());
		logger.debug("fetchObjects responseEntity getHeaders().getLocation(): "  + responseEntity.getHeaders().getLocation());

		/* restTemplate에서 통신한 결과 받은 responseEntity에서 필요한 body를 꺼낸다. */
		JsonNode jsonNode = responseEntity.getBody();
		/* 전달 받은 JsonNode 객체에서 products, orders 등 원하는 값을 받아서 역직렬화하여 리스트로 만들어 반환 */
		return deserializeDataList(jsonNode.get(connectionType), type);
	}


	/**
	 * JsonNode를 역직렬화하여 List<T>로 반환하려는 메서드
	 * @param jsonNode Api 서버와 통신 결과 반환 받은 JsonNode 객체
	 * @param elementType Product.class 등 반환 받으려는 객체의 타입
	 */

	@SuppressWarnings("unchecked")
	private <T> List<T> deserializeDataList(JsonNode jsonNode, final Class<T> elementType) {
		logger.debug("deserializeDataList called...");
		Iterator<String> fieldNamesIterator = jsonNode.fieldNames();
		while (fieldNamesIterator.hasNext()) {
			logger.debug("jsonNode.fieldNames(): " + fieldNamesIterator.next());

		}

		try {
			logger.debug("jsonNode.toString(): " + jsonNode.toString());
			logger.debug("deserializeDataList try to make CollectionType listType");

			/* */
			CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, elementType);
			logger.debug("deserializeDataList CollectionType listType.getTypeName: " + listType.getTypeName());
			//[collection type; class java.util.List, contains [simple type, class org.springframework.social.cafe24.api.Product]]

			logger.debug("deserializeDataList List<T> result = objectMapper.reader(listType).readValue(jsonNode.toString())");

			/* 이 부분에서 멈추기에 FacebookObject 같은 추상 클래스 만들어서 Product.class가 상속하도록 함 */
			/* 매핑되지 않은 프로퍼티는 Cafe24Object의 add 메서드에서 hook을 하여 추가 */
			List<T> result = objectMapper.readValue(jsonNode.toString(), listType);

			return result;
		} catch (IOException e) {
			throw new UncategorizedApiException("cafe24", "Error deserializing data from cafe24: " + e.getMessage(), e);
		}
	}


	/**
	 * {@link AbstractOAuth2ApiBinding}가 생성되면서 함께 생성되는 RestTemplate 인스턴스 사용.
	 * @return restTemplate
	 */
	@Override
	public RestTemplate getRestTemplate() {
		logger.debug("getRestTemplate called...");

		return super.getRestTemplate();
	}

	/**
	 * 특정 서비스 프로바이더를 위한 설정을 할 수 있는 메서드. <br>
	 * 메시지 컨버터, 에러 핸들러 등을 등록할 수 있다.
	 * @param restTemplate 데코레이션할 restTemplate
	 */
	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		logger.debug("configureRestTemplate called...");
		super.configureRestTemplate(restTemplate);
	}

	/**
	 * {@link AbstractOAuth2ApiBinding}에서 생성된 restTemplate에 대한 설정 메서드
	 * @param restTemplate
	 * @return restTemplate
	 */
	@Override
	protected RestTemplate postProcess(RestTemplate restTemplate) {
		logger.debug("postProcess called");
		return super.postProcess(restTemplate);
	}


	/**
	 * {@link AbstractOAuth2ApiBinding}에서 HttpMessageConverter 타입의 다양한 메시지 컨버터를 등록할 때 사용할
	 * JsonMessageConverter를 전달한다. Target 클래스와 Mixin 클래스가 설정된 Cafe24Module을 등록한다.
	 * @return
	 */
	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		logger.debug("getJsonMessageConverter called...");

		MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Cafe24Module());
		logger.debug("getJsonMessageConverter Cafe24Module registered...");

		converter.setObjectMapper(objectMapper);
		return converter;
	}


	/**
	 * 현재 인증을 받은 mall의 id를 반환한다.
	 * @return mallId
	 */
	public String getMallId() {
		return mallId;
	}

	/**
	 * initSubApi에서 생성된 productTemplate를 {@link ProductOperations} 인터페이스 타입으로 반환한다.
	 * {@link ProductOperations}에 미리 정의된 메서드들을 사용할 수 있다.
	 * @return
	 */
	@Override
	public ProductOperations productOperations() {
		logger.debug("productOperations called...");
		return productOperations;
	}


}
