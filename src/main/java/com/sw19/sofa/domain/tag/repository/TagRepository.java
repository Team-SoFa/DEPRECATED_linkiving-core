package com.sw19.sofa.domain.tag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sw19.sofa.domain.tag.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	List<Tag> findAllByNameIn(List<String> nameList);

	List<Tag> findAllByIdIn(List<Long> ids);

	@Query("SELECT t FROM Tag t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Tag> findAllByNameContainingIgnoreCase(@Param("keyword") String keyword);
}
