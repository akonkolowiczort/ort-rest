package uy.edu.ort.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.ort.model.Persona;

/**
 * If you want to use spring-data Dao, you need to comment out [uy.edu.ort.dao.PersonaDaoHibernateImpl]'s annotations.
 */
public interface PersonaDaoSpringData extends JpaRepository<Persona, Long> {

}
