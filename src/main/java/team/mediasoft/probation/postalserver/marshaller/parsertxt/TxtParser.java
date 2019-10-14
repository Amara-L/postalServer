package team.mediasoft.probation.postalserver.marshaller.parsertxt;

import team.mediasoft.probation.postalserver.marshaller.Marshaller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.exists;

/**
 * Parser for serialization/deserialization java-objects in TXT-file.
 * @param <T> type object
 */
//@Service("marshaller")
public class TxtParser<T> implements Marshaller {

    /**
     * Variable path to catalog.
     */
    private String path =
            Paths.get(".").toAbsolutePath().normalize()
                .toString()
                + "\\src\\main\\java\\team\\mediasoft\\"
                    + "probation\\postalserver\\txtfiles\\";

    /**
     * The method of saving a class object to a file.
     * \The name of the class is taken as the folder name.
     * Variables are stored in the sequence obtained from
     * getOrdinaryVariable() and getObjectVariable().
     * @param object - received object
     * @return - the path to the file
     */
    private File saveObject(final SerializableTXT object) {

            String folderName = object.getClass().getName();

            File folder = new File(path + folderName);
            if (!folder.exists()) {
                folder.mkdir();
            }

            try {

                return serializable(folder, object);

            } catch (ClassIdentifierException e) {
                e.printStackTrace();
            }


        return null;

    }

    /**
     * The method of writing an object to a file.
     * @param folder - the path to the folder
     * @param object - received object
     * @return - the path to the file
     * @throws ClassIdentifierException - exception caused by the absence
     * of an identifier in the class
     */
    private File serializable(final File folder, final SerializableTXT object)
            throws ClassIdentifierException {

        //Проверка на наличие идентификатора класса
        if (object.getIdentifier() == null) {
            throw new ClassIdentifierException("Missing Class Id");
        }

        File file = new File(folder,
                (object.getIdentifier().toString()
                        + ".txt"));

        PrintWriter fileWriter = null;

        try {

            fileWriter = new PrintWriter(file);

            if (object.getOrdinaryVariable() != null) {

                for (Object currentObject : object.getOrdinaryVariable()) {

                    if (currentObject != null) {
                        fileWriter.println(currentObject.toString());
                    } else {
                        fileWriter.println(null + "");
                    }
                }

            }

            if (object.getObjectVariable() != null) {

                definitionAndWriteObject(
                        object.getObjectVariable(), fileWriter);

            }

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        } finally {

            if (fileWriter != null) {

                fileWriter.close();
            }

        }

        return file;

    }

    /**
     * The method of reading data from a file and writing it to an java-object.
     * @param file - the path to the file
     * @return created java-object
     */
    private T createObject(final File file) {

        String fileName = file.toString();
        String[] flagName = (fileName.split("\\\\"));
        String className = flagName[flagName.length - 2];
        Object classObject = null;

        try {
            classObject = Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (classObject != null) {

            flagName = flagName[flagName.length - 1].split("\\.");

            exists(Paths.get(fileName));
            try {
                ((SerializableTXT) classObject).setIdentifier(
                        Integer.parseInt(flagName[flagName.length - 2]));
                ((SerializableTXT) classObject)
                        .serializationConstructor(deserializable(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return (T) classObject;
    }

    /**
     * The method of reading data from a file.
     * @param file - the path to the file
     * @return list of objects
     */
    private List<Object> deserializable(final File file) {

        List list = new ArrayList();

        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(file.getAbsoluteFile()));

            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {

                    if (line.equals("{")) {

                        List interiorList = new ArrayList();

                        while (!(line
                                = bufferedReader.readLine()).equals("}")) {

                            interiorList.add(definitionObjectForGet(line));

                        }

                        list.add(interiorList);

                    } else {

                        list.add(definitionObjectForGet(line));

                    }
                }
            } finally {
                bufferedReader.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;

    }

    /**
     * Object definition method.
     * @param list - list of objects
     * @param fileWriter - file write stream
     * @throws IOException - read / write exception
     */
    private void definitionAndWriteObject(
            final List list, final PrintWriter fileWriter)
            throws IOException {

        for (Object object: list) {

            if (object instanceof Number || object instanceof String) {

                fileWriter.println(object.toString());

            } else if (object instanceof List) {

                fileWriter.println("{");

                definitionAndWriteObject((List) object, fileWriter);

                fileWriter.println("}");

            } else {
                if (object instanceof SerializableTXT) {

                    File file1 = (new TxtParser())
                            .saveObject((SerializableTXT) object);
                    String[] flagName = file1.toString().split("\\\\");
                    fileWriter.println(flagName[flagName.length - 2]
                            + "\\" + flagName[flagName.length - 1]);
                }

            }

        }

    }

    /**
     * Object definition method.
     * @param object - object to define
     * @return object for recording
     */
    private Object definitionObjectForGet(final String object) {

        if (object != null) {
            String[] arrayObject = (object.split("\\."));

            if (arrayObject != null) {

                if (arrayObject[arrayObject.length - 1].equals("txt")) {

                    return createObject(new File(path + object));

                }

            }
        }

        return object;

    }

    /**
     * Method to save object to file.
     * @param object received object
     */
    public final void saveObjectInFile(final Object object) {

        if (object != null) {
           saveObject((SerializableTXT) object);
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

            if (nameFile.endsWith(".txt")) {

                return createObject(new File(path
                        + nameClass + "\\" + nameFile));

            } else if (new File(
                    path + nameClass + "\\" + nameFile + ".txt").exists()) {

                return createObject(new File(path + nameClass
                        + "\\" + nameFile + ".txt"));
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

                    list.add(createObject(new File(
                            path + nameClass + "\\" + file)));

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

            if (file.toString().endsWith(".txt")) {

                try {
                    classObject = Class.forName(
                            receivedClass.getName()).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (classObject != null) {

                    String nameFile = file.toString();
                    String[] arrayPathFile = nameFile.split("\\\\");
                    arrayPathFile = nameFile.split("\\.");

                    exists(Paths.get(nameFile));
                    try {
                        ((SerializableTXT) classObject).setIdentifier(
                                Integer.parseInt(
                                        arrayPathFile[
                                                arrayPathFile.length - 2]));
                        ((SerializableTXT) classObject)
                                .serializationConstructor(
                                        deserializable(file));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

                return name.endsWith(".txt");

            }

        });
    }

}
