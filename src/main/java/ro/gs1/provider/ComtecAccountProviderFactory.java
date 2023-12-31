package ro.gs1.provider;

import java.util.HashMap;
import java.util.Map;

import org.keycloak.Config;
import org.keycloak.forms.account.AccountProvider;
import org.keycloak.forms.account.AccountProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ServerInfoAwareProviderFactory;
import org.keycloak.theme.FreeMarkerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComtecAccountProviderFactory implements AccountProviderFactory, ServerInfoAwareProviderFactory {

   /**
    * Logger for this class
    */
   private static final Logger logger = LoggerFactory.getLogger(ComtecAccountProviderFactory.class);

   private FreeMarkerUtil freeMarker;

   @Override
   public AccountProvider create(KeycloakSession session) {
      logger.info("Am suprascris providerul pentru consola.");
      return new ComtecAccountProvider(session, freeMarker);
   }

   @Override
   public void init(Config.Scope config) {
      freeMarker = new FreeMarkerUtil();
   }

   @Override
   public void postInit(KeycloakSessionFactory factory) {
   }

   @Override
   public void close() {
      freeMarker = null;
   }

   @Override
   public String getId() {
      return "comtec";
   }

   @Override
   public Map<String, String> getOperationalInfo() {
      Map<String, String> retval = new HashMap<>();
      retval.put("provider", "ComtecAccountProvider");
      return retval;
   }
}
