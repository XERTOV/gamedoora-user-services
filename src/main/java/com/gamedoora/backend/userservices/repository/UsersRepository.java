package com.gamedoora.backend.userservices.repository;

import java.util.List;

import com.gamedoora.model.dao.UserRole;
import com.gamedoora.model.dao.UserSkills;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gamedoora.model.dao.Users;

@Repository
//@EntityScan("com.gamedoora.model.dao.*")
public interface UsersRepository extends JpaRepository<Users, Long> {

	List<Users> findByEmailContaining(String name);

	Users findByFirstName(String name);
	Users findByUserRole(UserRole role);

	Users findByUserSkills(UserSkills skill);
	// Users findBySkill(UserSkills skill);

	List<UserRole> findRolesByuserSkills_SkillsId(Long skillsId);
	// multiple roles with one skill

	List<Users> findByUserSkills_SkillsId(Long skillsId);
	//List<Users> listUsersBySkill_SkillsId(Long skillsId);



}
