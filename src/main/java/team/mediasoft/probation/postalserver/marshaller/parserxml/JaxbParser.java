package team.mediasoft.probation.postalserver.marshaller.parserxml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for serialization/deserialization java-objects in XML-file.
 * @param <T> type object
 */
public class JaxbParser<T> implements
        team.mediasoft.probation.postalserver.marshaller.Marshaller {

    /**
     * Variable path to catalog.
     */
    private String path = "C:\\Users\\Amara\\IdeaProjects\\PostalServer2\\"
            + "src\\main\\java\\team\\mediasoft\\probation\\"
            + "postalserver\\xmlfiles\\";

    /**
     * Deserialization.
     * @param file - data file
     * @param derivedClass - class object
     * @return object necessary class
     * @throws JAXBException exception
     */
    private T getObject(
            final File file,
            final Class derivedClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(derivedClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);

        return (T) object;
    }

    /**
     * Serialization.
     * @param file - data file
     * @param object - object serialization class
     * @return path the file
     * @throws JAXBException exception
     */
    private File saveObject(
            final File file,
            final Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(object, file);
        return file;
    }

    /**
     * Method to save object to file.
     * @param object received object
     */
    public final void saveObjectInFile(final Object object) {

        if (object != null) {

            String folderName = object.getClass().getName();

            File folder = new File(path + folderName);

            if (!folder.exists()) {
                folder.mkdir();
            }

            try {

                saveObject(new File(folder + "\\"
                                + ((SerializableXML) object)
                                .getIdentifier() + ".xml"),
                        object);

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Reading the object from the file.
     * Reading is carried out according to the class name (folder)
     * and identifier (file name) + file format.
     * @param nameClass - used to refer to a folder
     * @param nameFile - used to name the file
     * @return created object
     */
    public final T readObjectOfFile(
            final String nameClass,
            final String nameFile) {

        if ((nameClass != null) && (!nameClass.equals(""))) {

            try {

                if (nameFile.endsWith(".xml")) {

                    return getObject(new File(path
                                    + nameClass + "\\" + nameFile),
                            Class.forName(nameClass));

                } else if (new File(
                        path + nameClass + "\\"
                                + nameFile + ".xml").exists()) {

                    return getObject(
                            new File(path + nameClass
                                    + "\\" + nameFile + ".xml"),
                            Class.forName(nameClass));
                }

            } catch (JAXBException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    /**
     * The method of reading all objects in the directory
     * by the specified class name.
     * @param nameClass - used to refer to a folder
     * @return list created objects
     */
    public final List<T> readObjectsOfFolder(final String nameClass) {

        List<T> list = null;

        if (nameClass != null) {

            String[] arrayFiles
                    = getArrayFilesInFolder(new File(path + nameClass));

            if (arrayFiles != null) {

                list = new ArrayList<T>();

                for (String file : arrayFiles) {

                    try {

                        list.add(getObject(new File(
                                        path + nameClass + "\\" + file),
                                Class.forName(nameClass)));

                    } catch (JAXBException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

        return list;
    }

    /**
     * Method for loading an object from a third-party file.
     * @param file - the path to the file
     * @param receivedClass class from create object
     * @return created object
     */
    public final T loadObjectFromOutside(
            final File file,
            final Class receivedClass) {

        Object classObject = null;

        if ((file != null) && (receivedClass != null)) {

            if (file.toString().endsWith(".xml")) {

                try {
                    classObject = getObject(file, receivedClass);

                } catch (JAXBException e) {

                    e.printStackTrace();
                }

            }

        }

        return (T) classObject;
    }

    /**
     * The method of getting the list of files in the directory.
     * @param receivedPath directory path
     * @return array name the files
     */
    public final String[] getArrayFilesInFolder(final File receivedPath) {

        return receivedPath.list(new FilenameFilter() {

            public boolean accept(final File folder, final String name) {

                return name.endsWith(".xml");

            }

        });
    }
}
