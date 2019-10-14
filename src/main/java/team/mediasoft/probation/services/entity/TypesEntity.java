package team.mediasoft.probation.services.entity;

import lombok.Getter;
import lombok.Setter;
import team.mediasoft.probation.postalserver.postaldeparture.TypePostalDeparture;

import javax.persistence.*;

/**
 * Class-entity types departure.
 */
@Entity
@Table(name = "types")
public class TypesEntity implements EntityInterface {


    /**
     * Variable id.
     */
    @Id
    @Setter
    @Getter
    private int id;

    /**
     * Variable type.
     */
    @Setter
    @Getter
    private String type;

    /**
     * Constructor default.
     */
    public TypesEntity() {
    }

    /**
     * Constructor with parameter TypePostalDeparture.
     * @param typePostalDepartment enum TypePostalDeparture
     */
    public TypesEntity(TypePostalDeparture typePostalDepartment) {
        if (typePostalDepartment == TypePostalDeparture.letter) {
            type = "letter";
            id = 1;
        } else if (typePostalDepartment == TypePostalDeparture.parcel) {
            type = "parcel";
            id = 2;
        } else if (typePostalDepartment == TypePostalDeparture.postcard) {
            type = "postcard";
            id = 3;
        } else if (typePostalDepartment == TypePostalDeparture.wrapper) {
            type = "wrapper";
            id = 4;

        }
    }

    /**
     * Constructor with text parameter.
     * @param typePostalDepartment text type departure
     */
    public TypesEntity(String typePostalDepartment) {
        if (typePostalDepartment.equals("letter")) {
            type = "letter";
            id = 1;
        }

        if (typePostalDepartment.equals("parcel")) {
            type = "parcel";
            id = 2;
        }

        if (typePostalDepartment.equals("postcard")) {
            type = "postcard";
            id = 3;
        }

        if (typePostalDepartment.equals("wrapper")) {
            type = "wrapper";
            id = 4;
        }

    }

    /**
     * Get Enum TypePostalDeparture.
     * @return enum TypePostalDeparture
     */
    public TypePostalDeparture getEnumType() {
        if (id == 1) {
            return TypePostalDeparture.letter;
        } else if (id == 2) {
            return TypePostalDeparture.parcel;
        } else if (id == 3) {
            return TypePostalDeparture.postcard;
        } else if (id == 4) {
            return TypePostalDeparture.wrapper;
        } else {
            return null;
        }
    }

    /**
     * Get Enum TypePostalDeparture.
     * @param idType id type
     * @return enum TypePostalDeparture
     */
    public TypePostalDeparture getEnumType(int idType) {
        if (idType == 1) {
            return TypePostalDeparture.letter;
        } else if (idType == 2) {
            return TypePostalDeparture.parcel;
        } else if (idType == 3) {
            return TypePostalDeparture.postcard;
        } else if (idType == 4) {
            return TypePostalDeparture.wrapper;
        } else {
            return null;
        }
    }

}
