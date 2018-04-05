package ChildCareTech.common.DTO;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

public class PediatristDTO extends AdultDTO implements Serializable {
    private Set<KidDTO> kids;

    public PediatristDTO(PersonDTO person, Set<KidDTO> contacts, Set<KidDTO> kids) {
        super(person, contacts);
        this.kids = kids == null ? Collections.EMPTY_SET : kids;
    }

    public Set<KidDTO> getKids() {
        return kids;
    }
}