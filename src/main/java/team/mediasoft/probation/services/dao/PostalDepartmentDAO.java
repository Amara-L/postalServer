package team.mediasoft.probation.services.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import team.mediasoft.probation.postalserver.postaldepartment.PostalDepartment;
import team.mediasoft.probation.services.entity.PostalDepartmentEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Repository postal department.
 */
@Repository("departmentDAO")
public class PostalDepartmentDAO {

    /**
     * SessionFactory hibernate.
     */
    private SessionFactory sessionFactory
            = AccessSessionFactory.getSessionFactory();

    /**
     * Method getting object PostalDepartment by index.
     * Read Only Session
     * @param index - index postal department
     * @return object PostalDepartment
     */
    public PostalDepartment getPostalDepartment(String index) {

        PostalDepartment postalDepartment = null;

        if (!index.equals("")) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<PostalDepartmentEntity> query
                    = builder.createQuery(PostalDepartmentEntity.class);

            Root<PostalDepartmentEntity> employee
                    = query.from(PostalDepartmentEntity.class);

            query.select(employee);

            query.where(builder
                    .equal(employee.get("indexPostDepartment"), index));

            postalDepartment = session.createQuery(query).getSingleResult();

            session.close();
        }

        return postalDepartment;

    }

}
