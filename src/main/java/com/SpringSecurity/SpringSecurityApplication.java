package com.SpringSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {

			PermissionsEntity createPermission = new PermissionsEntity();
			createPermission.setName("CREATE");


			PermissionsEntity readPermission = new PermissionsEntity();
			readPermission.setName("READ");


			PermissionsEntity updatePermission = new PermissionsEntity();
			updatePermission.setName("UPDATE");


			PermissionsEntity deletePermission = new PermissionsEntity();
			deletePermission.setName("DELETE");


			PermissionsEntity refactorPermission = new PermissionsEntity();
			refactorPermission.setName("REFACTOR");



			RolesEntity roleAdmin = new  RolesEntity();
			roleAdmin.setRole(Roles.ADMIN);
			roleAdmin.setPermisosList(Set.of(createPermission, readPermission, updatePermission, deletePermission));


			RolesEntity roleUser = new RolesEntity();
			roleUser.setRole(Roles.USER);
			roleUser.setPermisosList(Set.of(createPermission, readPermission));


			RolesEntity roleInvited =  new RolesEntity();
			roleInvited.setRole(Roles.MODERATOR);
			roleInvited.setPermisosList(Set.of(readPermission));

			RolesEntity roleDeveloper = new RolesEntity();
			roleDeveloper.setRole(Roles.DEVELOPER);
			roleDeveloper.setPermisosList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission));


			UserEntity userSantiago = new UserEntity();
			userSantiago.setName("Santiago");
			userSantiago.setPassword("$2b$12$voobczNcWkj/QpvtAADIZOpsyWnulUbLpNoz0ad0zy9nw2mZyBRBG");
			userSantiago.setEnabled(true);
			userSantiago.setAccountNonExpired(true);
			userSantiago.setAccountNonLocked(true);
			userSantiago.setCredentialsNonExpired(true);
			userSantiago.setRoles(Set.of(roleInvited));

			UserEntity userFrancisco = new UserEntity();
			userFrancisco .setName("Francisco");
			userFrancisco .setPassword("$2b$12$voobczNcWkj/QpvtAADIZOpsyWnulUbLpNoz0ad0zy9nw2mZyBRBG");
			userFrancisco .setEnabled(true);
			userFrancisco .setAccountNonExpired(true);
			userFrancisco .setAccountNonLocked(true);
			userFrancisco .setCredentialsNonExpired(true);
			userFrancisco .setRoles(Set.of(roleDeveloper));

			UserEntity userKevin = new UserEntity();
			userKevin.setName("Kevin");
			userKevin.setPassword("$2b$12$voobczNcWkj/QpvtAADIZOpsyWnulUbLpNoz0ad0zy9nw2mZyBRBG");
			userKevin.setEnabled(true);
			userKevin.setAccountNonExpired(true);
			userKevin.setAccountNonLocked(true);
			userKevin.setCredentialsNonExpired(true);
			userKevin.setRoles(Set.of(roleUser));


			UserEntity userMartina = new UserEntity();
			userMartina.setName("Martina");
			userMartina.setPassword("$2b$12$voobczNcWkj/QpvtAADIZOpsyWnulUbLpNoz0ad0zy9nw2mZyBRBG");
			userMartina.setEnabled(true);
			userMartina.setAccountNonExpired(true);
			userMartina.setAccountNonLocked(true);
			userMartina.setCredentialsNonExpired(true);
			userMartina.setRoles(Set.of(roleAdmin));

			userRepository.saveAll(List.of(userSantiago, userFrancisco , userKevin, userMartina));
		};
	}*/

}
