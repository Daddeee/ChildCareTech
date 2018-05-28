package ChildCareTech.model.entities;

import ChildCareTech.model.iEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Represents a row of the User table saved in the database.
 * <p>
 * This class is mapped by Hibernate (basing on JPA annotations) on the User table in the database.
 */
@Entity
public class User implements iEntity<User, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 3, message = "Il nome utente deve avere almeno 3 caratteri")
    @Column(unique = true, nullable = false, length = 30)
    private String userName;

    @Column(nullable = false)
    private String password;

    /**
     * This constructor is used by Hibernate to build the entities, it should not be used elsewhere.
     */
    public User() {}

    /**
     * Create a User entity with the provided parameters and id=0.
     * <p>
     * Should be used to create a new entity to be saved in the database.
     *
     * @param userName the user's name.
     * @param password the user's password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Create a Person entity with the provided id and parameters.
     * <p>
     * Should be used to create an entity that is already saved in the database.
     *
     * @param id the id of the row in the database.
     * @param userName the user's name.
     * @param password the user's password
     */
    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Integer getPrimaryKey() {
        return getId();
    }

    /**
     * @return the entity's id.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        return this.userName.equals(((User) o).userName);
    }

    @Override
    public int hashCode() {
        return this.userName.hashCode();
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
