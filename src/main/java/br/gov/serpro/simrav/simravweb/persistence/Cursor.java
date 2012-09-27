package br.gov.serpro.simrav.simravweb.persistence;

public class Cursor {
private final static String MSG_TAMANHO_STRING_INVALIDO = "Tamanho de texto inválido.";
	
	private final static String MSG_FIM_DO_TEXTO = "Tamanho de token extrapola o tamano do texto."; 
	
	private final static String MSG_NUMERICO_INVALIDO = "Numérico inválido.";

	/** Atual posição do cursor. */
	private int position;

	/** Texto que será percorrido pelo cursor. */
	private String text;

	/**
	 * Construtor. Pode receber várias possibilidades de tamanhos para validar o
	 * lenght do texto. Caso o tamanho nenhuma delas fechar com o tamanho do
	 * texto uma exceção é lançada.
	 * 
	 * @param texto
	 * @param tamanhos
	 * @throws IllegalArgumentException
	 */
	public Cursor(String text, int... sizes) {
		if (sizes.length == 0 || validateSize(text, sizes)) {
			this.text = text;
		} else {
			System.out.println("**> text.length=" + text.length() + " size[0]=" + sizes[0]);
			System.out.println("**>");
			System.out.println(text);
			System.out.println("**>");
			throw new java.lang.IllegalArgumentException(MSG_TAMANHO_STRING_INVALIDO);
		}
	}

	

	/**
	 * Captura a próxima substring de tamanho definida por 'tamanho' da String <br>
	 * texto a partir da posição do cursor.<br>
	 * 
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public String nextToken(int size) throws RuntimeException {
		if (text.length() < position + size) {
			throw new RuntimeException( MSG_TAMANHO_STRING_INVALIDO );
		} else {
			String substring = text.substring(position, position + size);
			updatePosition(size);
			return substring;
		}
	}

	/**
	 * Idem método nextToken, porém realizando a operação de trim.<br>
	 * No caso de o resultado do trim() for um texto em branco, retorna null. <br>
	 * 
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	
	/**
	public String nextTokenTrim(int size) throws RuntimeException {
		String texto = StringUtils.trim(nextToken(size));
		if (StringUtils.isEmpty(texto)) {
			return null;
		}
		return texto;
	}
    */

	/**
	 * Captura a próxima substring de tamanho definida por 'tamanho' da String
	 * texto a partir da posição do cursor. Valida se a string é composta
	 * somente por dígitos.
	 * 
	 * @param size
	 * @return
	 * @throws RuntimeException
	 */
	public String nextTokenNumeric(int size) throws RuntimeException {
		
		String substring;
		
		if (text.length() < position + size) {
			throw new RuntimeException(MSG_TAMANHO_STRING_INVALIDO);
		} else {
			
			substring = text.substring(position, position + size);

			// trata os espaços em branco.
			String stringNumerica = substring.trim();
			// trata numéricos com sinal.
			// a função isNumeric usaba a seguir não aceita sinal.
			
			System.out.println("stringNumerica1: " + stringNumerica);
			
			if (stringNumerica.startsWith("+") || stringNumerica.startsWith("-")) {
				stringNumerica = stringNumerica.substring(1,size);
				System.out.println("stringNumerica2: " + stringNumerica);
			}
			
			// a função isNumeric não aceita sinal.
			// ("^[0-9]*$")
			
			if(!stringNumerica.matches("[0-9]*")){
				throw new RuntimeException(MSG_NUMERICO_INVALIDO + " Valor: " + substring);
			}
						
			updatePosition(size);
			
			System.out.println("stringNumerica3: " + stringNumerica);			
			
		}
		
		return substring;
	}

	/**
	 * Atualiza a posição do cursor.
	 * 
	 * @return
	 */
	public void updatePosition(int size) {
		position += size;		
	}
	
	/**
	 * Verifica se existe mais caracteres a serem processados pelo
	 * interpretador. Caso o texto seja null retorna false.
	 * 
	 * @return boolean retorna true se existir mais caracteres a serem
	 *         processados no texto. Retorna false caso o texto seja null ou já
	 *         tenha sido todo processado.
	 */
	public boolean hasNext() {
		if (text == null) {
			return false; // o texto não foi inicializado
		} else if (text.length() <= position) {
			return false; // Todo texto já foi processado
		} else {
			return true; // Ainda existe caracteres em texto para serem processados.
		}
	}
	
	/**
	 * Avançar o número de posições definidas em size.
	 * @param newPosition
	 * @throws RuntimeException
	 */
	public void goForwardPositon(int size)
			throws RuntimeException {
		if (text.length() < position + size) {
			throw new RuntimeException(MSG_FIM_DO_TEXTO);
		} else {
			updatePosition(size);
		}
	}
	
	/**
	 * Retorna a atual posição do cursor.
	 * 
	 * @return
	 */
	public int getPosicao() {
		return position;
	}
	
	public void setPosicao(int posicao){
		this.position = posicao;
	}
	
	/** Verifica se a String possui um tamanhos especificados. Uma exceção é 
	 * lançada caso a String seja null ou o tamanho não conferir com algum espeficicado.
	 * @param String str 
	 * @param int tamanhos
	 * @param String mensagemExcecao
	 * @exception IllegalArgumentException
	 * @return
	 */
	private static boolean validateSize(String str, int ... tamanhos) throws IllegalArgumentException {
		for( int tamanho : tamanhos){
			if (str.length() == tamanho){
				return true;
			}
		}		
		return false;		
	}
	
	/**
	 * Retorna o tamanho do texto manipulado pelo cursor.
	 * @return
	 */
	public Integer getTextSize(){
		return text.length();
				
	}
}
