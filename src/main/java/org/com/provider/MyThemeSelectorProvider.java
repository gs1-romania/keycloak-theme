package org.com.provider;

import org.keycloak.theme.Theme.Type;
import org.keycloak.theme.ThemeSelectorProvider;

public class MyThemeSelectorProvider implements ThemeSelectorProvider {

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getThemeName(Type type) {
		// TODO Auto-generated method stub
		return "my-theme";
	}

}
