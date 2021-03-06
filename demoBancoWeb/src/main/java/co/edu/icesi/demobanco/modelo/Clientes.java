package co.edu.icesi.demobanco.modelo;
// Generated 21/05/2018 03:27:45 PM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clientes generated by hbm2java
 */
@Entity
@Table(name = "clientes", schema = "public")
public class Clientes implements java.io.Serializable {

	private long cliId;
	private TiposDocumentos tiposDocumentos;
	private String cliNombre;
	private String cliDireccion;
	private String cliTelefono;
	private String cliMail;
	private String cliActivo;
	private Set<Cuentas> cuentases = new HashSet<Cuentas>(0);
	private Set<Retiros> retiroses = new HashSet<Retiros>(0);
	private Set<Transferencias> transferenciases = new HashSet<Transferencias>(0);

	public Clientes() {
	}

	public Clientes(long cliId, TiposDocumentos tiposDocumentos, String cliNombre, String cliDireccion,
			String cliTelefono, String cliActivo) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
		this.cliActivo = cliActivo;
	}

	public Clientes(long cliId, TiposDocumentos tiposDocumentos, String cliNombre, String cliDireccion,
			String cliTelefono, String cliMail, String cliActivo, Set<Cuentas> cuentases, Set<Retiros> retiroses,
			Set<Transferencias> transferenciases) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
		this.cliMail = cliMail;
		this.cliActivo = cliActivo;
		this.cuentases = cuentases;
		this.retiroses = retiroses;
		this.transferenciases = transferenciases;
	}

	@Id

	@Column(name = "cli_id", unique = true, nullable = false, precision = 10, scale = 0)
	public long getCliId() {
		return this.cliId;
	}

	public void setCliId(long cliId) {
		this.cliId = cliId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tdoc_codigo", nullable = false)
	public TiposDocumentos getTiposDocumentos() {
		return this.tiposDocumentos;
	}

	public void setTiposDocumentos(TiposDocumentos tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}

	@Column(name = "cli_nombre", nullable = false, length = 50)
	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	@Column(name = "cli_direccion", nullable = false, length = 50)
	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	@Column(name = "cli_telefono", nullable = false, length = 50)
	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	@Column(name = "cli_mail", length = 50)
	public String getCliMail() {
		return this.cliMail;
	}

	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}

	@Column(name = "cli_activo", nullable = false, length = 2)
	public String getCliActivo() {
		return this.cliActivo;
	}

	public void setCliActivo(String cliActivo) {
		this.cliActivo = cliActivo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
	public Set<Cuentas> getCuentases() {
		return this.cuentases;
	}

	public void setCuentases(Set<Cuentas> cuentases) {
		this.cuentases = cuentases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
	public Set<Retiros> getRetiroses() {
		return this.retiroses;
	}

	public void setRetiroses(Set<Retiros> retiroses) {
		this.retiroses = retiroses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
	public Set<Transferencias> getTransferenciases() {
		return this.transferenciases;
	}

	public void setTransferenciases(Set<Transferencias> transferenciases) {
		this.transferenciases = transferenciases;
	}

}
