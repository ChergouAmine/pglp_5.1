import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Personnel implements Personnels, Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String nom;
  private String prenom;
  private String fonction;
  private java.time.LocalDateTime date_naissance;
  private ArrayList<Integer> num_tel;
  
  public static class Builder{
      private String nom;
      private String prenom;
      private String fonction = "fonction";
      private java.time.LocalDateTime date_naissance;
      private ArrayList<Integer> num_tel;
      
      public Builder(String n, String p,java.time.LocalDateTime date_n) {
          nom = n;
          prenom = p;
          date_naissance = date_n;
          num_tel = new ArrayList<Integer>();
      }
      
      public Builder function(String f) {
          this.fonction = f;
          return this;
      }
      
      public Builder add_tel(int t) {
          this.num_tel.add(t);
          return this;
      }
      
      public Personnels build() {
          return new Personnel(this);
      }
  }
  public Personnel(Builder builder) {
      nom = builder.nom;
      prenom = builder.prenom;
      fonction = builder.fonction;
      num_tel = builder.num_tel;
      date_naissance = builder.date_naissance;
  }
  
  public void print() {
      System.out.println("nom : "+nom+"\nprenom : "+prenom+"\nfonction : "+fonction+"\nnuméro : "+num_tel+"\n Date de Naissance : "+date_naissance);
  }

  public ArrayList<Personnels> getPersonnels() {
    // TODO Auto-generated method stub
    return null;
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
  public static Personnel deserialize(final String path) {
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
