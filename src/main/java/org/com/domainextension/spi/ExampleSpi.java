package org.com.domainextension.spi;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

import ro.gs1.provider.ComtecAccountProvider;
import ro.gs1.provider.ComtecAccountProviderFactory;

public class ExampleSpi implements Spi{

	@Override
	public boolean isInternal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "example";
	}

	@Override
	public Class<? extends Provider> getProviderClass() {
		// TODO Auto-generated method stub
		return ComtecAccountProvider.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class<? extends ProviderFactory> getProviderFactoryClass() {
		// TODO Auto-generated method stub
		return ComtecAccountProviderFactory.class;
	}

}
