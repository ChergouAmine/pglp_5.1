import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CompositePersonnel implements Personnels, Serializable{
	
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  int id;
  public ArrayList<Personnels> personnes = new ArrayList<Personnels>();
  
  
  public CompositePersonnel(int i) {
      id = i;
  }
  
  public ArrayList<Personnels> getPersonnels(){
      return personnes;
  }
  public void print() {
          System.out.println(id);
  }
  
  public void add(Personnels groupe) {
      personnes.add(groupe);
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
  public static CompositePersonnel deserialize(final String path) {
      ObjectInputStream reader = null;
      Personnel p = null;
      try {
          FileInputStream file = new FileInputStream(path);
          reader = new ObjectInputStream(file);
          p = (Personnel) reader.readObject();
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
