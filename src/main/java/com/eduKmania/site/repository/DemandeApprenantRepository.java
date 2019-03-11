package com.eduKmania.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduKmania.site.model.DemandeApprenant;

/*
 * C'est tout ce que vous devez faire dans la couche référentiel. Vous allez maintenant
 *  être en mesure d'utiliser les méthodes comme de 
 *  JpaRepository save(), findOne(), findAll(), count(), delete()etc.
 */
@Repository
public interface DemandeApprenantRepository extends JpaRepository<DemandeApprenant, Long> {

}
