import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class DAOPersonnel implements DAO<Personnel>, Serializable{


  /**
   * 
   */
  private static final long serialVersionUID = 1L;
 
  
  private ArrayList<Personnel> list;
  
  public DAOPersonnel() {
      list = new ArrayList<Personnel>();
  }
  
  /**
   * ajout d'un Personnel dans la liste du DAO.
   */
  public void add(final Personnel p) {
      list.add(p);
  }
  /**
   * get un personnel du DAO
   */
  public Personnel get(String a,String b) {
      for (Personnel p : list) {
          if (p.getNom().equals(a) && p.getPrenom().equals(b)) {
              return p;
          }
      }
      return null;
  }
  /**
   *liste de tout les personnel du DAO.
   */
  @SuppressWarnings("unchecked")
  public ArrayList<Personnel> getAll() {
      return (ArrayList<Personnel>) list.clone();
  }
  
  /**
   * modifier un personnel
   */
  @SuppressWarnings("unchecked")
  public void update(Personnel p,Map<String, Object> params) {
      if (list.remove(p)) {
          String nom = "";
          if (params.containsKey("nom")) {
              nom = (String) params.get("nom");
          } else {
              nom = p.getNom();
          }
          String prenom = "";
          if (params.containsKey("prenom")) {
              prenom = (String) params.get("prenom");
          } else {
              prenom = p.getPrenom();
          }
          java.time.LocalDateTime dateN;
          if (params.containsKey("dateN")) {
            dateN = (java.time.LocalDateTime) params.get("dateN");
          } else {
            dateN = p.getDate();
          }
          ArrayList<Integer> num;
          if (params.containsKey("num")) {
            ArrayList<Integer> tmp;
              tmp = (ArrayList<Integer>) params.get("num");
              num = (ArrayList<Integer>) tmp.clone();
          } else {
              num = p.getNum();
          }
          Personnel pp = (Personnel) new Personnel.Builder(
              nom, prenom, dateN).build();
          list.add(p);
      }
  }
  /**
   * supprimer un personnel du DAO
   */
  public void remove(final Personnel object) {
      list.remove(object);
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
  public static DAOPersonnel deserialize(final String path) {
      ObjectInputStream reader = null;
      DAOPersonnel p = null;
      try {
          FileInputStream file = new FileInputStream(path);
          reader = new ObjectInputStream(file);
          p = (DAOPersonnel) reader.readObject();
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

  public Personnel get(int id) {
    // TODO Auto-generated method stub
    return null;
  }

 
}
