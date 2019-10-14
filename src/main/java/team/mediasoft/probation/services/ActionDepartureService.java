package team.mediasoft.probation.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mediasoft.probation.postalserver.actiondeparture.Action;
import team.mediasoft.probation.postalserver.postaldepartment.PostalDepartment;
import team.mediasoft.probation.postalserver.postaldeparture.PostalDeparture;
import team.mediasoft.probation.services.dao.ActionsDepartureDAO;
import team.mediasoft.probation.services.dao.PostalDepartmentDAO;
import team.mediasoft.probation.services.dao.PostalDepartureDAO;
import team.mediasoft.probation.services.entity.ActionDepartureEntity;

/**
 * Service actions postal departure.
 */
@Service("departureService")
@Transactional
public class ActionDepartureService {

    /**
     * Repository postal departures.
     */
    @Getter
    @Autowired
    @Qualifier("departureDAO")
    private PostalDepartureDAO postalDepartureDAO;

    /**
     * Repository actions departures.
     */
    @Getter
    @Autowired
    @Qualifier("actionsDAO")
    private ActionsDepartureDAO actionsDepartureDAO;

    /**
     * Repository postal departments.
     */
    @Getter
    @Autowired
    @Qualifier("departmentDAO")
    private PostalDepartmentDAO postalDepartmentDAO;

    /**
     * Method registration action departure.
     * @param action - Enum Action
     * @param indexPostalDepartment - index Postal Department
     * @param idPostalDeparture - id Postal Departure
     * @return boolean value - result registration
     */
    public boolean registrationActionDeparture(
            Action action,
            String indexPostalDepartment,
            String idPostalDeparture) {

        PostalDeparture postalDeparture
                = postalDepartureDAO.getPostalDeparture(
                        Integer.parseInt(idPostalDeparture));

        PostalDepartment postalDepartment
                = postalDepartmentDAO
                .getPostalDepartment(indexPostalDepartment);

        ActionDepartureEntity actionDeparture = null;

        if ((postalDeparture != null)
                && (postalDepartment != null)
                && (action != null)) {

            actionDeparture
                    = new ActionDepartureEntity(
                            action, postalDepartment, postalDeparture);
        }

         return actionsDepartureDAO.addAction(actionDeparture);
    }

}
