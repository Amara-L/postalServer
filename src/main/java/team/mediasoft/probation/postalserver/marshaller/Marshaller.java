package team.mediasoft.probation.postalserver.marshaller;

import java.io.File;
import java.util.List;

/**
 * Interface describing the class of saving java-objects in a file.
 * @param <T> - type object
 */
public interface Marshaller<T> {

    /**
     * Method to save object to file.
     * @param object received object
     */
    void saveObjectInFile(T object);

    /**
     * Reading the object from the file.
     * Reading is carried out according to the class name (folder)
     * and identifier (file name) + file format.
     * @param nameClass - used to refer to a folder
     * @param nameFile - used to name the file
     * @return created object
     */
    T readObjectOfFile(String nameClass, String nameFile);

    /**
     * The method of reading all objects in the directory
     * by the specified class name.
     * @param nameClass - used to refer to a folder
     * @return list created objects
     */
    List<T> readObjectsOfFolder(String nameClass);

    /**
     * Method for loading an object from a third-party file.
     * Must take file path and class to create an object.
     * @param file - the path to the file
     * @param receivedClass class from create an object
     * @return created object
     */
    T loadObjectFromOutside(File file, Class receivedClass);

}
