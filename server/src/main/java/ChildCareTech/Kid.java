package ChildCareTech;

import javax.persistence.*;

@Entity
@Table(name="kids")
public class Kid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "fiscal_code", unique = true)
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_tutor_code")
    private Adult firstTutor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_tutor_code")
    private Adult secondTutor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pediatrist_code")
    private Pediatrist pediatrist;

    public Kid(){}
    public Kid(Person person, Adult firstTutor, Adult secondTutor, Pediatrist pediatrist){
        this.person = person;
        this.firstTutor = firstTutor;
        this.secondTutor = secondTutor;
        this.pediatrist = pediatrist;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    private void setPerson(Person person) {
        this.person = person;
    }

    public Adult getFirstTutor() {
        return firstTutor;
    }

    private void setFirstTutor(Adult firstTutor) {
        this.firstTutor = firstTutor;
    }

    public Adult getSecondTutor() {
        return secondTutor;
    }

    private void setSecondTutor(Adult secondTutor) {
        this.secondTutor = secondTutor;
    }

    public Pediatrist getPediatrist() {
        return pediatrist;
    }

    private void setPediatrist(Pediatrist pediatrist) {
        this.pediatrist = pediatrist;
    }
}

