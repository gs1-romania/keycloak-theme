package org.com.provider;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.theme.ThemeSelectorProvider;
import org.keycloak.theme.ThemeSelectorProviderFactory;

public class MyThemeSelectorProviderFactory implements ThemeSelectorProviderFactory{

	@Override
	public ThemeSelectorProvider create(KeycloakSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(Scope config) {
		// TODO Auto-generated method stub
		String themeName = config.get("theme");
	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "myThemeSelector";
	}

}
