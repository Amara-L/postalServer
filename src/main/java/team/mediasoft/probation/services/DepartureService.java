package team.mediasoft.probation.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mediasoft.probation.postalserver.actiondeparture.Action;
import team.mediasoft.probation.postalserver.postaldepartment.PostalDepartment;
import team.mediasoft.probation.postalserver.postaldeparture.TypePostalDeparture;
import team.mediasoft.probation.services.dao.ActionsDepartureDAO;
import team.mediasoft.probation.services.dao.PostalDepartmentDAO;
import team.mediasoft.probation.services.dao.PostalDepartureDAO;
import team.mediasoft.probation.services.entity.ActionDepartureEntity;
import team.mediasoft.probation.services.entity.PostalDepartureEntity;
import team.mediasoft.probation.services.entity.TypesEntity;

/**
 * Service postal departure.
 */
@Service("departureServise")
@Transactional
public class DepartureService {

    /**
     * Repository postal departures.
     */
    @Getter
    @Autowired
    @Qualifier("departureDAO")
    private PostalDepartureDAO postalDepartureDAO;

    /**
     * Repository postal department.
     */
    @Getter
    @Autowired
    @Qualifier("departmentDAO")
    private PostalDepartmentDAO postalDepartmentDAO;

    /**
     * Repository actions departures.
     */
    @Getter
    @Autowired
    @Qualifier("actionsDAO")
    private ActionsDepartureDAO actionsDepartureDAO;

    /**
     * Method getting Postal Departure by id.
     * @param id - id postal departure
     * @return object PostalDeparture
     */
    @Transactional(readOnly = true)
    public PostalDepartureEntity getDeparture(Integer id) {

        return postalDepartureDAO.getPostalDeparture(id);

    }

    /**
     * Method addition new Postal Departure.
     * @param type - type departure
     * @param index - index postal department recipient
     * @param name - surname name patronymic recipient
     * @param adress - address recipient
     * @param indexPostalDepartment - index postal department sender
     * @return object PostalDeparture
     */
    @Transactional
    public PostalDepartureEntity addNewDeparture(
            String type,
            String index,
            String name,
            String adress,
            String indexPostalDepartment) {

       PostalDepartment postalDepartment
               = postalDepartmentDAO.getPostalDepartment(
                       indexPostalDepartment);

       PostalDepartureEntity postalDeparture = null;

        if ((postalDepartment != null)
                && (!type.equals(""))
                && (!index.equals(""))
                && (!name.equals(""))
                && (!adress.equals(""))
        ) {

            TypePostalDeparture typePostalDeparture
                    = new TypesEntity(type).getEnumType();

            postalDeparture
                    = new PostalDepartureEntity(
                    index,
                    adress,
                    name,
                    typePostalDeparture);

            postalDeparture.setTypeEntity(
                    new TypesEntity(typePostalDeparture));

            postalDeparture.setType(postalDeparture.getTypeEntity().getType());

            ActionDepartureEntity actionDeparture =
                    new ActionDepartureEntity(
                            Action.registrationNewDeparture,
                            postalDepartment,
                            postalDeparture);

            postalDepartureDAO.addPostalDeparture(postalDeparture);

            actionsDepartureDAO.addAction(actionDeparture);

        }

        return postalDeparture;

    }

}
