package co.edu.icesi.demobanco.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class InfoCuentaPublicaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//En la transaciión de la cuenta, no me trae el cliente
	//private String nombreCliente;
	
	private long idCliente;
	
	private String numeroCuenta;
	
	private BigDecimal saldo;
	
	private String activacion;

	public InfoCuentaPublicaDTO( Long idCliente, String numeroCuenta, BigDecimal saldo,
			String estadoCuenta) {
		super();
		this.idCliente = idCliente;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.activacion = estadoCuenta;
	}

	public InfoCuentaPublicaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

//	public String getNombreCliente() {
//		return nombreCliente;
//	}
//
//	public void setNombreCliente(String nombreCliente) {
//		this.nombreCliente = nombreCliente;
//	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getActivacion() {
		return activacion;
	}

	public void setEstadoCuenta(String activacion) {
		this.activacion = activacion;
	}

	@Override
	public String toString() {
		return "Id Cliente=" + idCliente + ", numero Cuenta="
				+ numeroCuenta + ", saldo=" + saldo + ", activacion=" + activacion;
	}
	
	
	
	

}
