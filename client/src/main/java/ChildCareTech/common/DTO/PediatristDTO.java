package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * This class provides a Data Transfer Object encapsulation for {@link ChildCareTech.model.entities.Pediatrist Pediatrist} entity.
 */
public class PediatristDTO extends AdultDTO implements Serializable {
    private Set<KidDTO> kids;

    public PediatristDTO(int id, PersonDTO person, Set<KidDTO> contacts, Set<KidDTO> kids) {
        super(id, person, contacts);
        this.kids = kids == null ? Collections.EMPTY_SET : kids;
    }

    public Set<KidDTO> getKids() {
        return kids;
    }

    public void setKids(Set<KidDTO> kids) {
        this.kids = kids;
    }
}