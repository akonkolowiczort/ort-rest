package uy.edu.ort.model;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public class EntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Column
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return Objects.equals(id, ((EntityClass) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
