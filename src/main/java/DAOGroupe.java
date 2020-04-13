import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class DAOGroupe implements DAO<Groupe>, Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 6826094352248252306L;
  
  private ArrayList<Groupe> list;


  public DAOGroupe() {
      list = new ArrayList<Groupe>();
  }
  
  /**
   * ajoute un Groupe dans la liste du DAO.
   */
  public void add(final Groupe p) {
      list.add(p);
  }
  
  /**
   * obtenir un Groupe du DAO.
   */
  public Groupe get(int id) {
      for (Groupe p : list) {
          if (p.getId() == id) {
              return p;
          }
      }
      return null;
  }
  
  
  /**
   * obtenir liste de tout les Groupe du DAO
   */
  @SuppressWarnings("unchecked")
  public ArrayList<Groupe> getAll() {
      return (ArrayList<Groupe>) list.clone();
  }
  
  
  /**
   * modifier un Groupe
   */
  @SuppressWarnings("unchecked")
  public void update(Groupe p,Map<String, Object> params) {
      /*
       * 
       * 
       * A REMPLIR
       * 
       * 
       */
  }
  /**
   * supprime un Groupe du DAO
   */
  public void remove(final Groupe p) {
      list.remove(p);
  }
  
  /**
   * serialize vers fichier
   * @param path du fichier vers lequel serializer
   */
  public void serialize(final String path) {
      ObjectOutputStream writer = null;
      try {
          FileOutputStream file = new FileOutputStream(path);
          writer = new ObjectOutputStream(file);
          writer.writeObject(this);
          writer.flush();
          writer.close();
      } catch (IOException e) {
          System.err.println(
          "serialization to \""
          + path + " failed\"");
      }
      try {
          if (writer != null) {
              writer.flush();
              writer.close();
          }
      } catch (IOException e2) {
          e2.printStackTrace();
      }
  }
  
  /**
   * deserialize vers fichier 
   * @param path du fichier pour deserializer
   * @return l'instance de classe créée avec deserialization
   */
  public static DAOGroupe deserialize(final String path) {
      ObjectInputStream reader = null;
      DAOGroupe p = null;
      try {
          FileInputStream file = new FileInputStream(path);
          reader = new ObjectInputStream(file);
          p = (DAOGroupe) reader.readObject();
      } catch (IOException e) {
          System.err.println(
          "deserialization to \""
          + path + " failed\"");
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
      try {
          if (reader != null) {
              reader.close();
          }
      } catch (IOException e2) {
          e2.printStackTrace();
      }
      return p;
  }

}
