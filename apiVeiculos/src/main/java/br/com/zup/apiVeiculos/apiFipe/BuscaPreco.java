package br.com.zup.apiVeiculos.apiFipe;

public class BuscaPreco {
	    	
	public ObjetoDetalhado definirPreco(String marca, String modelo, String ano) throws Exception {
		
		
		ConsumirApiFipe consumir = new ConsumirApiFipe();
		
		String codigoMarca = consumir.getCodigoMarca(marca);
		
		String codigoModelo = consumir.getCodigoModelo(codigoMarca, modelo);
		
		String codigoAno = consumir.getCodigoAno(codigoMarca, codigoModelo, ano);
		
		ObjetoDetalhado detalhes = consumir.getPreco(codigoMarca, codigoModelo, codigoAno);

		return detalhes;
		
	}
	
	    	
}
