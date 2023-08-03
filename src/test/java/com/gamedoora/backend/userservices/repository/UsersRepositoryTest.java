package com.gamedoora.backend.userservices.repository;

import com.gamedoora.model.dao.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest(properties = {"spring.cloud.config.enabled=false"})
//@EnableConfigurationProperties(value= PropertiesConfig.class)
@TestPropertySource("classpath:application.properties")
class UsersRepositoryTest {

 @Autowired private UsersRepository usersRepository;


 private Users user ;
 private UserRole userRole;
 private Roles role;
 private UserSkills userSkills ;

 private Skills skill ;

 @BeforeEach
 void setup(){
  userRole = UserRole.builder().roles(role).build();
  userSkills = UserSkills.builder().skills(skill).build();

  skill = Skills.builder().id(1L).build();

  user = Users.builder()
          .id(1L)
          .firstName("Test")
          .email("test@gmail.com")
          .userRole((Set<UserRole>) userRole)
          .userSkills((Set<UserSkills>) userSkills)
          .providerToken("ok")
          .build();
 }
    @Test
    void findByName() {
        usersRepository.save(user);
        Users userValue = usersRepository.findByFirstName(user.getFirstName());
        assertThat(userValue).isNotNull();
        assertThat(userValue.getFirstName()).isEqualTo("Test");
    }

    @Test
    void findByRole() {
        usersRepository.save(user);
        Users userValue = usersRepository.findByUserRole((UserRole) user.getUserRole());
        assertThat(userValue).isNotNull();
        assertThat(userValue.getUserRole()).isEqualTo(userRole);
    }
    @Test
    void findBySkill() {
        usersRepository.save(user);
        //   Users userValue = usersRepository.findBySkill((UserSkills) user.getUserSkills());
        Users userValue = usersRepository.findByUserSkills((UserSkills) user.getUserSkills());
        assertThat(userValue).isNotNull();
        assertThat(userValue.getUserSkills()).isEqualTo(userSkills);
    }
    @Test
    void findRolesBySkill_SkillsId() {
        usersRepository.save(user);
        List<UserRole> userRoleList = usersRepository.findRolesByuserSkills_SkillsId(skill.getId());
        assertFalse(userRoleList.isEmpty());
    }
    @Test
    void listUsersBySkill_SkillsId() {
        usersRepository.save(user);
        // List<Users> userSkillsList = usersRepository.listUsersBySkill_SkillsId(skill.getId());
        List<Users> userSkillsList = usersRepository.findByUserSkills_SkillsId(skill.getId());
        assertFalse(userSkillsList.isEmpty());
    }
}


