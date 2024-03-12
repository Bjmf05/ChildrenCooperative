package cr.ac.una.tareaprogra.model;

/**
 *
 * @author PC
 */
public class Account {
    private int id;
    private String Name;
    
    public Account() {
    }

    public Account(int id, String Name) {
        this.id = id;
        this.Name = Name;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


    
    
}
