package Persistance;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RessourceManager {

    /**
     * Permet d'ecrire des donnees dans un fichier
     * @param data : donnees ecrite dans le fichier
     * @param fileName : Nom du fichier
     * @throws Exception
     */
    public static void save(Serializable data, String fileName) throws Exception{
        try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream( Paths.get(fileName)))){
            out.writeObject(data);
        }
    }

    /**
     * Permet de lire des donnees dans un fichier
     * @param fileName : Nom du fichier
     * @return : retourne les donnees lu
     * @throws Exception
     */
    public static Object load(String fileName) throws Exception{
        try(ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))){
            return in.readObject();
        }
    }
}
