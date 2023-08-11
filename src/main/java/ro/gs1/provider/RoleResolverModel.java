package ro.gs1.provider;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.keycloak.models.RoleModel;
import org.keycloak.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pt roluri sa le gasesc in fremarker
 *
 * @date $Date: Oct 23, 2020$
 * @lastChangedBy $LastChangedBy:$
 * @author Grigoras Cristinel
 *
 */
public class RoleResolverModel {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(RoleResolverModel.class);

	private UserModel user;

	public RoleResolverModel(UserModel user) {
		super();
		this.user = user;
	}

	public boolean hasRole(String roleName) {
		logger.debug("hasRole(String) - start"); //$NON-NLS-1$

		ArrayList<RoleModel> list = new ArrayList<>();
		list.addAll(user.getRoleMappingsStream().collect(Collectors.toList()));
		user.getGroupsStream().forEach(gm -> list.addAll(gm.getRoleMappingsStream().collect(Collectors.toList())));
		Optional<RoleModel> findFromGroup = list.stream().filter(aa -> aa.getName().equals(roleName)).findFirst();
		if (findFromGroup.isPresent()) {
			if (logger.isDebugEnabled()) {
				logger.debug("hasRole(String) - end"); //$NON-NLS-1$
			}
			return true;
		}

		logger.debug("hasRole(String) - end"); //$NON-NLS-1$
		return false;
	}
}
