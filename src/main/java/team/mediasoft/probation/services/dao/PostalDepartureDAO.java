package team.mediasoft.probation.services.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import team.mediasoft.probation.postalserver.actiondeparture.Action;
import team.mediasoft.probation.postalserver.postaldeparture.TypePostalDeparture;
import team.mediasoft.probation.services.entity.*;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Repository postal departure.
 */
@Repository("departureDAO")
public class PostalDepartureDAO {

    /**
     * SessionFactory hibernate.
     */
    private SessionFactory sessionFactory
            = AccessSessionFactory.getSessionFactory();

    /**
     * Method getting postal departure by id.
     * Read Only Session
     * @param id - id postal departure
     * @return object PostalDeparture
     */
    public PostalDepartureEntity getPostalDeparture(Integer id) {

        PostalDepartureEntity postalDeparture = null;

        if (id != null) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaQuery<PostalDepartureEntity> query
                    = session.getCriteriaBuilder()
                    .createQuery(PostalDepartureEntity.class);

            Root<PostalDepartureEntity> employee
                    = query.from(PostalDepartureEntity.class);

            query.where(session.getCriteriaBuilder()
                    .equal(employee.get("id"), id));

            postalDeparture
                    = session.createQuery(query).getSingleResult();

            if (postalDeparture != null) {
                Hibernate.initialize(postalDeparture.getActionsDeparture());

                TypePostalDeparture typePostalDeparture
                        = new TypesEntity().getEnumType(
                                postalDeparture.getIdType());

                postalDeparture.setTypeEntity(
                        new TypesEntity(typePostalDeparture));
                postalDeparture.setType(
                        postalDeparture.getTypeEntity().getType());

                for (ActionDepartureEntity actionDeparture
                        : postalDeparture.getActionsDeparture()) {

                    Action actionEnum = new ActionsEntity()
                            .getEnumActionById(actionDeparture.getIdAction());

                    actionDeparture.setActionEntity(new ActionsEntity(actionEnum));
                    actionDeparture.setActionString(
                            actionDeparture.getActionEntity().getActionText()
                    );

                }

            }

            session.close();
        }

        return postalDeparture;

    }

    /**
     * Method addition new postal departure.
     * @param postalDeparture object PostalDeparture
     * @return created PostalDeparture
     */
    public PostalDepartureEntity addPostalDeparture(
            PostalDepartureEntity postalDeparture) {

        PostalDepartureEntity savedDepatrure = null;

        if (postalDeparture != null) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(postalDeparture);
            session.getTransaction().commit();
            session.close();

            session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaQuery<PostalDepartureEntity> query
                    = session.getCriteriaBuilder()
                    .createQuery(PostalDepartureEntity.class);

            Root<PostalDepartureEntity> employee
                    = query.from(PostalDepartureEntity.class);

            query.select(employee);

            query.orderBy(session.getCriteriaBuilder()
                    .desc(employee.get("id")));

            savedDepatrure
                    = session.createQuery(query)
                    .setMaxResults(1).getSingleResult();

            session.getTransaction().commit();
            session.close();

        }

        return savedDepatrure;

    }

}
