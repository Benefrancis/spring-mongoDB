package br.com.benefrancis.empregados.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document("empregado")
public class Empregado {

	@Id
	private String id;

	@Field(targetType = FieldType.STRING)
	private Pessoa pessoa;

	@Indexed
	@Field(targetType = FieldType.STRING)
	private Setor setor;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dataAdmissao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dataDemissao;


	@NotNull
	@Email
	@Indexed(unique = true)
	private String email;
}
