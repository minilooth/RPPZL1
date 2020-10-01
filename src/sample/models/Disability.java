package sample.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name="disability")
public class Disability implements Serializable {
    private int disabilityId;
    private String disabilityName;

    @OneToMany(mappedBy = "disabiblityId", fetch = FetchType.EAGER)
    private Collection<Client> clients;

    @Id
    @Column(name = "DisabilityId", nullable = false)
    public int getDisabilityId() {
        return disabilityId;
    }

    public void setDisabilityId(int disabilityId) {
        this.disabilityId = disabilityId;
    }

    @Basic
    @Column(name = "DisabilityName", nullable = true, length = 50)
    public String getDisabilityName() {
        return disabilityName;
    }

    public void setDisabilityName(String disabilityName) {
        this.disabilityName = disabilityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disability that = (Disability) o;
        return disabilityId == that.disabilityId &&
                Objects.equals(disabilityName, that.disabilityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disabilityId, disabilityName);
    }
}
