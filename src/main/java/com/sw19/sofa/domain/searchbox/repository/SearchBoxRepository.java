package com.sw19.sofa.domain.searchbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sw19.sofa.domain.linkcard.entity.LinkCard;

@Repository
public interface SearchBoxRepository extends JpaRepository<LinkCard, Long>, SearchBoxCustomRepository {
}
