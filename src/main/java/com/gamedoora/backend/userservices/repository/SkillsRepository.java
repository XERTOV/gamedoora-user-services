package com.gamedoora.backend.userservices.repository;

import java.util.List;

import com.gamedoora.model.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamedoora.model.dao.Skills;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {

	List<Skills> findByNameContaining(String name);
    List<Users> findByuserSkills_Id(Long SkillsId);
//	List<Users> findByUsers_UserId(Long userId);
}
