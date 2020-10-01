package sample.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "citizenship")
public class Citizenship implements Serializable {
    private int citizenshipId;
    private String citizenshipName;


    @OneToMany(mappedBy = "citizenshipId", fetch = FetchType.EAGER)
    private Collection<Client> clients;

    @Id
    @Column(name = "CitizenshipId", nullable = false)
    public int getCitizenshipId() {
        return citizenshipId;
    }

    public void setCitizenshipId(int citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    @Basic
    @Column(name = "CitizenshipName", nullable = true, length = 50)
    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizenship that = (Citizenship) o;
        return citizenshipId == that.citizenshipId &&
                Objects.equals(citizenshipName, that.citizenshipName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(citizenshipId, citizenshipName);
    }
}
