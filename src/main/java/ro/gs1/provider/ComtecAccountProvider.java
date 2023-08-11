package ro.gs1.provider;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.jboss.logging.Logger;
import org.keycloak.forms.account.AccountPages;
import org.keycloak.forms.account.AccountProvider;
import org.keycloak.forms.account.freemarker.FreeMarkerAccountProvider;
import org.keycloak.forms.account.freemarker.model.AccountBean;
import org.keycloak.forms.account.freemarker.model.AccountFederatedIdentityBean;
import org.keycloak.forms.account.freemarker.model.ApplicationsBean;
import org.keycloak.forms.account.freemarker.model.AuthorizationBean;
import org.keycloak.forms.account.freemarker.model.FeaturesBean;
import org.keycloak.forms.account.freemarker.model.LogBean;
import org.keycloak.forms.account.freemarker.model.PasswordBean;
import org.keycloak.forms.account.freemarker.model.RealmBean;
import org.keycloak.forms.account.freemarker.model.ReferrerBean;
import org.keycloak.forms.account.freemarker.model.SessionsBean;
import org.keycloak.forms.account.freemarker.model.TotpBean;
import org.keycloak.forms.account.freemarker.model.UrlBean;
import org.keycloak.models.KeycloakSession;
import org.keycloak.theme.FreeMarkerUtil;
import org.keycloak.theme.Theme;
import org.keycloak.theme.beans.AdvancedMessageFormatterMethod;
import org.keycloak.theme.beans.LocaleBean;

public class ComtecAccountProvider extends FreeMarkerAccountProvider {

	private static final Logger logger = Logger.getLogger(ComtecAccountProvider.class);

	private boolean authorizationSupported;

	public ComtecAccountProvider(KeycloakSession session, FreeMarkerUtil freeMarker) {
		super(session, freeMarker);
		logger.debug("ComtecAccountProvider - start");
	}

	@Override
	public Response createResponse(AccountPages page) {
		logger.debug("createResponse() - start");
		Map<String, Object> attributes = new HashMap<>();
		if (this.attributes != null) {
			attributes.putAll(this.attributes);
		}
		Theme theme;
		try {
			theme = getTheme();
		} catch (IOException e) {
			logger.error("Failed to create theme", e);
			return Response.serverError().build();
		}
		Locale locale = session.getContext().resolveLocale(user);
		Properties messagesBundle = handleThemeResources(theme, locale, attributes);
		URI baseUri = uriInfo.getBaseUri();
		UriBuilder baseUriBuilder = uriInfo.getBaseUriBuilder();
		for (Map.Entry<String, List<String>> e : uriInfo.getQueryParameters().entrySet()) {
			baseUriBuilder.queryParam(e.getKey(), e.getValue().toArray());
		}
		URI baseQueryUri = baseUriBuilder.build();
		if (stateChecker != null) {
			attributes.put("stateChecker", stateChecker);
		}
		handleMessages(locale, messagesBundle, attributes);
		if (referrer != null) {
			attributes.put("referrer", new ReferrerBean(referrer));
		}
		if (realm != null) {
			attributes.put("realm", new RealmBean(realm));
		}
		attributes.put("url", new UrlBean(realm, theme, baseUri, baseQueryUri, uriInfo.getRequestUri(), stateChecker));
		if (realm.isInternationalizationEnabled()) {
			UriBuilder b = UriBuilder.fromUri(baseQueryUri).path(uriInfo.getPath());
			attributes.put("locale", new LocaleBean(realm, locale, b, messagesBundle));
		}
		attributes.put("features", new FeaturesBean(identityProviderEnabled, eventsEnabled, passwordUpdateSupported,
				authorizationSupported));
		attributes.put("account", new AccountBean(user, profileFormData));
		attributes.put("user", user);
		attributes.put("roles", new RoleResolverModel(user));
		switch (page) {
		case TOTP:
			attributes.put("totp", new TotpBean(session, realm, user, uriInfo.getRequestUriBuilder()));
			break;
		case FEDERATED_IDENTITY:
			attributes.put("federatedIdentity",
					new AccountFederatedIdentityBean(session, realm, user, uriInfo.getBaseUri(), stateChecker));
			break;
		case LOG:
			attributes.put("log", new LogBean(events));
			break;
		case SESSIONS:
			attributes.put("sessions", new SessionsBean(realm, sessions));
			break;
		case APPLICATIONS:
			attributes.put("applications", new ApplicationsBean(session, realm, user));
			attributes.put("advancedMsg", new AdvancedMessageFormatterMethod(locale, messagesBundle));
			break;
		case PASSWORD:
			attributes.put("password", new PasswordBean(passwordSet));
			break;
		case RESOURCES:
			if (!realm.isUserManagedAccessAllowed()) {
				return Response.status(Status.FORBIDDEN).build();
			}
			attributes.put("authorization", new AuthorizationBean(session, user, uriInfo));
		case RESOURCE_DETAIL:
			if (!realm.isUserManagedAccessAllowed()) {
				return Response.status(Status.FORBIDDEN).build();
			}
			attributes.put("authorization", new AuthorizationBean(session, user, uriInfo));
		}
		logger.debug("createResponse() - end");
		return processTemplate(theme, page, attributes, locale);
	}

	@Override
	public AccountProvider setFeatures(boolean identityProviderEnabled, boolean eventsEnabled,
			boolean passwordUpdateSupported, boolean authorizationSupported) {
		logger.debug("setFeatures() - start");
		this.identityProviderEnabled = identityProviderEnabled;
		this.eventsEnabled = eventsEnabled;
		this.passwordUpdateSupported = passwordUpdateSupported;
		this.authorizationSupported = authorizationSupported;
		logger.debug("setFeatures() - end");
		return this;
	}
}