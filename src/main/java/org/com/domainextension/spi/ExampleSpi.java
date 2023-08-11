package org.com.domainextension.spi;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.gs1.provider.ComtecAccountProvider;
import ro.gs1.provider.ComtecAccountProviderFactory;

public class ExampleSpi implements Spi{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ExampleSpi.class.getName());

	@Override
	public boolean isInternal() {
		logger.info("isInternal()");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		logger.info("getName()");
		// TODO Auto-generated method stub
		return "example";
	}

	@Override
	public Class<? extends Provider> getProviderClass() {
		// TODO Auto-generated method stub
		logger.info("getProviderClass()");
		return ComtecAccountProvider.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class<? extends ProviderFactory> getProviderFactoryClass() {
		// TODO Auto-generated method stub
		logger.info("getProviderFactoryClass");
		return ComtecAccountProviderFactory.class;
	}

}
