package edu.sgu.delicatessen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sgu.delicatessen.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    
}
