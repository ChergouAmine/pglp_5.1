import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class DAOCompositePersonnel implements DAO<CompositePersonnel>, Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 8290603030362628710L;
  
  private ArrayList<CompositePersonnel> list;

  
  public DAOCompositePersonnel() {
      list = new ArrayList<CompositePersonnel>();
  }
  /**
   * ajouter un CompositePersonnels dans la liste du DAO
   */
  public void add(final CompositePersonnel object) {
      list.add(object);
  }
  
  /**
   * obtenir un CompositePersonnels du DAO
   */
  public CompositePersonnel get(final int id) {
      for (CompositePersonnel p : list) {
          if (p.getId() == id) {
              return p;
          }
      }
      return null;
  }
  /**
   * liste de tout les CompositePersonnel du DAO
   */
  @SuppressWarnings("unchecked")
  public ArrayList<CompositePersonnel> getAll() {
      return (ArrayList<CompositePersonnel>) list.clone();
  }
  
  /**
   * modifier un CompositePersonnel
   */
  @SuppressWarnings("unchecked")
  public void update(CompositePersonnel p,Map<String, Object> params) {
      if (list.contains(p)) {
          if (params.containsKey("personnels")) {
              ArrayList<Personnels> r =
              (ArrayList<Personnels>)
              params.get("personnels");
              p.clear();
              for (Personnels pp : r) {
                  p.add(pp);
              }
          }
      }
  }
  
  /**
   * supprime un CompositePersonnels du DAO
   */
  public void remove(final CompositePersonnel p) {
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
  public static DAOCompositePersonnel deserialize(final String path) {
      ObjectInputStream reader = null;
      DAOCompositePersonnel p = null;
      try {
          FileInputStream file = new FileInputStream(path);
          reader = new ObjectInputStream(file);
          p = (DAOCompositePersonnel) reader.readObject();
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
