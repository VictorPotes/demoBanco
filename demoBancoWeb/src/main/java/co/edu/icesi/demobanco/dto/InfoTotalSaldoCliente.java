package co.edu.icesi.demobanco.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class InfoTotalSaldoCliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long cliId;
	
	private BigDecimal suma;

	public InfoTotalSaldoCliente(long cliId, BigDecimal suma) {
		super();
		this.cliId = cliId;
		this.suma = suma;
	}

	public InfoTotalSaldoCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCliId() {
		return cliId;
	}

	public void setCliId(long cliId) {
		this.cliId = cliId;
	}

	public BigDecimal getSuma() {
		return suma;
	}

	public void setSuma(BigDecimal suma) {
		this.suma = suma;
	}
	
	

}
