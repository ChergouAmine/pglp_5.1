import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Groupe implements Serializable{

 

  /**
   * 
   */
  private static final long serialVersionUID = -5979666566467347313L;


  ArrayList<Personnels> next;
  int id;
  Personnels premier;

  public Groupe(Personnels persAvant,int i) {
      premier = persAvant;
      next = new  ArrayList<Personnels>();
      id=i;
  }
  
  public int getId()
  {
    return id;
  }
  
  private class GroupeIterator implements Iterator<Object>{
      int index =0;
      
      public GroupeIterator() {
          
        next.add(premier);
          
          int i =0;
          
          while(i < next.size()) {
              if(next.get(i) instanceof CompositePersonnel) {
                  for(Personnels p : next.get(i).getPersonnels()) {
                    next.add(p);
                  }
              }
              i++;
          }
          
      }
      
      public boolean hasNext() {
          if(index < next.size()) {
              return true;
          }
          
          return false;
      }

      public Personnels next() {
        Personnels gp = next.get(index);
          index++;
          return gp;
      }
  }
  
  public Iterator<?> getIterator() {
      return new GroupeIterator();
  }

  
  public void clear() {
    next.clear();
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
  public static Groupe deserialize(final String path) {
      ObjectInputStream reader = null;
      Groupe p = null;
      try {
          FileInputStream file = new FileInputStream(path);
          reader = new ObjectInputStream(file);
          p = (Groupe) reader.readObject();
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
