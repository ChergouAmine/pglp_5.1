
public class DAOFabrique {

  
  private DAOFabrique() {
  }
  
  
  public static DAO<Personnel> getDaoPersonnel(final String deserialize) {
      if (deserialize == null) {
          return new DAOPersonnel();
      } else {
          return (DAO<Personnel>) DAOPersonnel.deserialize(deserialize);
      }
  }
  
  public static DAO<CompositePersonnel>
  getDaoCompositePersonnels(final String deserialize) {
      if (deserialize == null) {
          return new DAOCompositePersonnel();
      } else {
          return DAOCompositePersonnel.deserialize(deserialize);
      }
  }
  
  public static DAO<Groupe>
  getDaoAfficheParGroupe(final String deserialize) {
      if (deserialize == null) {
          return new DAOGroupe();
      } else {
          return DAOGroupe.deserialize(deserialize);
      }
  }
}
