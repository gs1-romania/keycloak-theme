package org.com.domainextension.spi;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

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
		return ExampleService.class;
	}

	@Override
	public Class<? extends ProviderFactory<ExampleService>> getProviderFactoryClass() {
		// TODO Auto-generated method stub
		return ExampleServiceProviderFactory.class;
	}

}
