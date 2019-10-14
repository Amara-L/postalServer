package team.mediasoft.probation.services.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import team.mediasoft.probation.postalserver.actiondeparture.Action;
import team.mediasoft.probation.services.entity.ActionDepartureEntity;
import team.mediasoft.probation.services.entity.ActionsEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Repository actions on postal departure.
 */
@Repository("actionsDAO")
public class ActionsDepartureDAO {

    /**
     * SessionFactory hibernate.
     */
    private SessionFactory sessionFactory
            = AccessSessionFactory.getSessionFactory();

    /**
     * Method add action on postal departure.
     * @param action - enum Action
     * @return result
     */
    public boolean addAction(ActionDepartureEntity action) {

        ActionDepartureEntity savedAction = null;

        if (action != null) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(action);
            session.getTransaction().commit();
            session.close();

            session = sessionFactory.openSession();
            session.beginTransaction();


            CriteriaQuery<ActionDepartureEntity> query
                    = session.getCriteriaBuilder()
                    .createQuery(ActionDepartureEntity.class);

            Root<ActionDepartureEntity> employee
                    = query.from(ActionDepartureEntity.class);

            query.select(employee);

            query.orderBy(session.getCriteriaBuilder()
                    .desc(employee.get("id")));

            savedAction = session.createQuery(query)
                    .setMaxResults(1).getSingleResult();

            Action actionEnum = new ActionsEntity()
                    .getEnumActionById(savedAction.getIdAction());

            savedAction.setActionEntity(new ActionsEntity(actionEnum));
            savedAction.setActionString(
                    savedAction.getActionEntity().getActionText());

        }

        return (savedAction != null);

    }

}
