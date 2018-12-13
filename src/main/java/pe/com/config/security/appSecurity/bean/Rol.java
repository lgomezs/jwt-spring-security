package pe.com.config.security.appSecurity.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "Roles")
public class Rol {

	@Id
	private String id;
	private Integer rolCod;
	private String noRol;
	private String deRol;
	private String tiRol;
	private int stRol;
	private int stRegi;

	public Rol() {}
}
