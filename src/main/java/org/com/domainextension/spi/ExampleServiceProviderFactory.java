package org.com.domainextension.spi;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderFactory;

public class ExampleServiceProviderFactory implements ProviderFactory<ExampleService>{

	@Override
	public ExampleService create(KeycloakSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(Scope config) {
		// TODO Auto-generated method stub

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
		return "newspi";
	}

}