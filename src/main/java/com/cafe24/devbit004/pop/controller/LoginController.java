package com.cafe24.devbit004.pop.controller;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.social.cafe24.api.Cafe24;
import org.springframework.social.cafe24.api.Product;
import org.springframework.social.cafe24.api.ProductOperations;
import org.springframework.social.cafe24.api.scripttag.Scripttags;
import org.springframework.social.cafe24.api.scripttag.ScripttagsOperations;
import org.springframework.social.cafe24.config.util.UserCookieSignInAdapter;
import org.springframework.social.cafe24.connect.Cafe24ConnectionFactory;
import org.springframework.social.cafe24.connect.Cafe24OAuth2Connection;
import org.springframework.social.cafe24.connect.Cafe24OAuth2Template;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.DuplicateConnectionException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.connect.web.ProviderSignInInterceptor;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UrlPathHelper;

import com.alibaba.fastjson.JSON;

/**
 * @author Petri Kainulainen
 */
@Controller("LoginController")
@RequestMapping("/connect2")
public class LoginController extends ConnectController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private Cafe24ConnectionFactory cafe24ConnectionFactory;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	private Cafe24 cafe24;

	private final UrlPathHelper urlPathHelper = new UrlPathHelper();

	private final MultiValueMap<Class<?>, ConnectInterceptor<?>> connectInterceptors = new LinkedMultiValueMap<Class<?>, ConnectInterceptor<?>>();
	private final ConnectionFactoryLocator connectionFactoryLocator;
	private final ConnectionRepository connectionRepository;
	private final ConnectSupport connectSupport;
	private SessionStrategy sessionStrategy;

	/* Sign in 과 관련된 필드 */
	private final UserCookieSignInAdapter userCookieSignInAdapter;
	private final MultiValueMap<Class<?>, ProviderSignInInterceptor<?>> signInInterceptors = new LinkedMultiValueMap<Class<?>, ProviderSignInInterceptor<?>>();

	/**
	 * Constructs a ConnectController.
	 *
	 * @param connectionFactoryLocator
	 *            the locator for {@link ConnectionFactory} instances needed to
	 *            establish connections
	 * @param connectionRepository
	 *            the current user's {@link ConnectionRepository} needed to persist
	 *            connections; must be a proxy to a request-scoped bean
	 */
	@Inject
	public LoginController(ConnectionFactoryLocator connectionFactoryLocator,
			ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.connectionRepository = connectionRepository;
		HttpSessionSessionStrategy httpSessionSessionStrategy = new HttpSessionSessionStrategy();
		this.sessionStrategy = httpSessionSessionStrategy;
		this.connectSupport = new ConnectSupport(this.sessionStrategy);
		this.userCookieSignInAdapter = new UserCookieSignInAdapter();
	}

	@RequestMapping(value = "/result")
	public String result(NativeWebRequest request, Model model) {
		logger.info("in /result handler");
		ProductOperations productTemplate = cafe24.productOperations();

		List<Product> productList = productTemplate.getProducts();
		logger.info("oauth2Callback productList");

		if (productList != null) {
			for (Product product : productList) {
				logger.info("result getProductName: " + product.getProductName());
				logger.info("result getProductName: " + product.getProductCode());
				logger.info("result getProductName: " + product.getShopNo());
			}
		} else {
			logger.info("result productList 가져오기 실패");

		}
		model.addAttribute("productList", productList);
		return "user/result";
	}

	@RequestMapping(value = "/scripttags")
	public String scripttags(NativeWebRequest request, Model model) {
		logger.info("in /scripttags handler");
		ScripttagsOperations scripttagsTemplate = cafe24.scripttagsOperations();

		String createdScripttag = scripttagsTemplate.create("data");
		logger.info("[ createdScripttag ] " + createdScripttag);

		if (createdScripttag != null) {
			logger.info("result createdScripttag: " + createdScripttag);
		} else {
			logger.info("scripttags createdScripttag  실패");

		}
		model.addAttribute("createdScripttag", createdScripttag);
		return "user/test_scripttag";
	}

	@RequestMapping(value = "/allScripttags")
	public String allScripttags(NativeWebRequest request, Model model) {
		logger.info("in /scripttags handler");
		ScripttagsOperations scripttagsTemplate = cafe24.scripttagsOperations();

		String allScripttags = scripttagsTemplate.getAll();
		logger.info("[ allScripttags ] " + allScripttags);

		if (allScripttags != null) {
			logger.info("result allScripttags: " + allScripttags);
		} else {
			logger.info("scripttags allScripttags  실패");

		}

		// parsing
		Scripttags scripttags = new Scripttags();
		try {
			
			 scripttags = JSON.parseObject(allScripttags, Scripttags.class);
			logger.info("[scripttags: ]" + scripttags);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("scripttags", scripttags);
		return "user/test_scripttag";
	}

	@RequestMapping(value = "/delete/scripttag")
	public String deleteScripttag(NativeWebRequest request, Model model,
			@RequestParam(name = "script_no") String scriptNo) {
		logger.info("in /scripttags handler");
		logger.info("[ deleteScripttag :  scriptNo ]" + scriptNo);
		ScripttagsOperations scripttagsTemplate = cafe24.scripttagsOperations();

		scripttagsTemplate.delete(scriptNo);

		return "user/login";
	}

	@Override
	@RequestMapping(value = "/{providerId}", method = RequestMethod.POST)
	public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
		return super.connect(providerId, request);
	}

	@Override
	@RequestMapping(value = "/{providerId}", method = RequestMethod.GET, params = "code")
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
		try {
			logger.info("oauth2Callback started...");
			logger.info("oauth2Callback providerId: " + providerId);
			logger.info("oauth2Callback getParameter(code): " + request.getParameter("code"));
			OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator
					.getConnectionFactory(providerId);
			logger.info(
					"oauth2Callback connectionFactory.getClass().getName(): " + connectionFactory.getClass().getName());
			if (connectionFactory == null) {
				logger.info("oauth2Callback connectionFactory == null");
			}
			String code = request.getParameter("code");
			logger.info("oauth2Callback code: " + code);

			Connection<?> connection = connectSupport.completeConnection(connectionFactory, request);
			logger.info("oauth2Callback connection done...");

			// cafe24Template = (Cafe24Template) connection.getApi();
			Cafe24OAuth2Connection cafe24OAuth2Connection = (Cafe24OAuth2Connection) connection;

			this.cafe24 = cafe24OAuth2Connection.getApi();
			addConnection(connection, connectionFactory, request);
			handleSignIn(connection, connectionFactory, request);

		} catch (Exception e) {
			logger.info("e.getMessage: " + e.getMessage());

			e.getStackTrace();
			sessionStrategy.setAttribute(request, PROVIDER_ERROR_ATTRIBUTE, e);
			logger.warn("1111Exception while handling OAuth2 callback (" + e.getMessage() + "). Redirecting to "
					+ providerId + " connection status page.");
		}
		// return connectionStatusRedirect(providerId, request);
		return new RedirectView("/", true);
	}

	private RedirectView handleSignIn(Connection<?> connection, ConnectionFactory<?> connectionFactory,
			NativeWebRequest request) {
		logger.info("handleSignIn called...");
		List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
		if (userIds.contains(Cafe24OAuth2Template.getMallId())) {
			/* 해당 유저 정보 찾아와서 액세스 토큰 만료됐다면 리프레시 */
		}
		if (userIds != null) {
			logger.info("handleSignIn userIds: " + userIds);
		}
		if (userIds.size() == 0) {
			ProviderSignInAttempt signInAttempt = new ProviderSignInAttempt(connection);
			logger.info("handleSignIn userIds.size() == 0");

			sessionStrategy.setAttribute(request, ProviderSignInAttempt.SESSION_ATTRIBUTE, signInAttempt);
			return redirect("/");
		} else if (userIds.size() == 1) {
			logger.info("handleSignIn userIds.size() == 1");

			usersConnectionRepository.createConnectionRepository(userIds.get(0)).updateConnection(connection);
			String originalUrl = userCookieSignInAdapter.signIn(userIds.get(0), connection, request);
			postSignIn(connectionFactory, connection, request);
			return originalUrl != null ? redirect(originalUrl) : redirect("/connect2/result");
		} else {
			logger.info("handleSignIn userIds.size() > 1");
			return redirect(
					URIBuilder.fromUri("/connect2/result").queryParam("error", "multiple_users").build().toString());
		}
	}

	private RedirectView redirect(String url) {
		return new RedirectView(url, true);
	}

	@Override
	public String connectionStatus(NativeWebRequest request, Model model) {
		logger.info("connectionStatus(NativeWebRequest request, Model model) called...");
		return super.connectionStatus(request, model);
	}

	@Override
	public String connectionStatus(String providerId, NativeWebRequest request, Model model) {
		logger.info("connectionStatus(String providerId, NativeWebRequest request, Model model) called...");

		return super.connectionStatus(providerId, request, model);
	}

	private void addConnection(Connection<?> connection, ConnectionFactory<?> connectionFactory, WebRequest request) {
		logger.info("addConnection started...");
		try {
			logger.info("addConnection 1");

			connectionRepository.addConnection(connection);
			logger.info("addConnection 2");

			postConnect(connectionFactory, connection, request);
			logger.info("addConnection 3");

		} catch (DuplicateConnectionException e) {
			logger.info("addConnection DuplicateConnectionException");

			sessionStrategy.setAttribute(request, DUPLICATE_CONNECTION_ATTRIBUTE, e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void postConnect(ConnectionFactory<?> connectionFactory, Connection<?> connection, WebRequest request) {
		logger.info("postConnect started...");
		for (ConnectInterceptor interceptor : interceptingConnectionsTo(connectionFactory)) {
			logger.info("postConnect interceptor: " + interceptor.getClass().getName());
			interceptor.postConnect(connection, request);
		}
	}

	private void postSignIn(ConnectionFactory<?> connectionFactory, Connection<?> connection, WebRequest request) {
		for (ProviderSignInInterceptor interceptor : interceptingSignInTo(connectionFactory)) {
			interceptor.postSignIn(connection, request);
		}
	}

	private List<ProviderSignInInterceptor<?>> interceptingSignInTo(ConnectionFactory<?> connectionFactory) {
		Class<?> serviceType = GenericTypeResolver.resolveTypeArgument(connectionFactory.getClass(),
				ConnectionFactory.class);
		List<ProviderSignInInterceptor<?>> typedInterceptors = signInInterceptors.get(serviceType);
		if (typedInterceptors == null) {
			typedInterceptors = Collections.emptyList();
		}
		return typedInterceptors;
	}

	private List<ConnectInterceptor<?>> interceptingConnectionsTo(ConnectionFactory<?> connectionFactory) {
		logger.info("interceptingConnectionsTo started...");

		Class<?> serviceType = GenericTypeResolver.resolveTypeArgument(connectionFactory.getClass(),
				ConnectionFactory.class);
		List<ConnectInterceptor<?>> typedInterceptors = connectInterceptors.get(serviceType);
		if (typedInterceptors == null) {
			typedInterceptors = Collections.emptyList();
		}
		return typedInterceptors;
	}

	protected RedirectView connectionStatusRedirect(String providerId, NativeWebRequest request) {
		logger.info("connectionStatusRedirect started...");
		HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
		String path = "/connect2/result/" + getPathExtension(servletRequest);
		logger.info("connectionStatusRedirect path: " + path);

		if (prependServletPath(servletRequest)) {
			path = servletRequest.getServletPath() + path;
			logger.info("connectionStatusRedirect path in if(prependServletPath): " + path);

		}
		return new RedirectView(path, true);
	}

	private boolean prependServletPath(HttpServletRequest request) {
		logger.info("prependServletPath started...");

		return !this.urlPathHelper.getPathWithinServletMapping(request).equals("");
	}

	private String getPathExtension(HttpServletRequest request) {
		logger.info("getPathExtension started...");

		String fileName = extractFullFilenameFromUrlPath(request.getRequestURI());
		logger.info("getPathExtension fileName: " + fileName);

		String extension = StringUtils.getFilenameExtension(fileName);
		logger.info("getPathExtension extension: " + extension);

		return extension != null ? "." + extension : "";
	}

	private String extractFullFilenameFromUrlPath(String urlPath) {
		logger.info("extractFullFilenameFromUrlPath started...");

		int end = urlPath.indexOf('?');
		if (end == -1) {
			end = urlPath.indexOf('#');
			if (end == -1) {
				end = urlPath.length();
			}
		}
		int begin = urlPath.lastIndexOf('/', end) + 1;
		logger.info("extractFullFilenameFromUrlPath begin: " + begin);

		int paramIndex = urlPath.indexOf(';', begin);
		logger.info("extractFullFilenameFromUrlPath paramIndex: " + paramIndex);

		end = (paramIndex != -1 && paramIndex < end ? paramIndex : end);
		logger.info("extractFullFilenameFromUrlPath end: " + end);
		String result = urlPath.substring(begin, end);
		logger.info("extractFullFilenameFromUrlPath result: " + result);

		return result;
	}
}
