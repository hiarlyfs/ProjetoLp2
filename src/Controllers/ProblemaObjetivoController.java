package Controllers;



import Entidades.Objetivo;
import Entidades.Problema;
import Repositorios.ObjetivosRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Validador;
public class ProblemaObjetivoController {
	private ObjetivosRepositorio objetivosRepositorio;
	private ProblemasRepositorio problemasRepositorio;
	private Validador validador;

	public ProblemaObjetivoController(ObjetivosRepositorio objetivosRepositorio,
			ProblemasRepositorio problemasRepositorio) {
		this.problemasRepositorio = problemasRepositorio;
		this.objetivosRepositorio = objetivosRepositorio;
		this.validador = new Validador();
	}

	public String cadastraProblema(String descricao, int viabilidade) {

		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "P" + String.valueOf(problemasRepositorio.getContadorProblemas());
		Problema problema = new Problema(descricao, viabilidade, codigo);
		problemasRepositorio.put(codigo, problema);
		problemasRepositorio.incrementaContador();
		return codigo;
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		validador.validar(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");

		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}

		validador.validarPontuacao(aderencia, "Valor invalido de aderencia");

		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "O" + objetivosRepositorio.getContadorObjetivos();
		Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade, codigo);
		objetivosRepositorio.put("O" + objetivosRepositorio.getContadorObjetivos(), objetivo);
		objetivosRepositorio.incrementaContador();
		return codigo;
	}

	public String exibeProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		Problema problema = problemasRepositorio.getProblema(codigo);
		return problema.toString();

	}

	public String exibeObjetivo(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Objetivo objetivo = objetivosRepositorio.getObjetivo(codigo);
		return objetivo.toString();

	}

	public void apagarProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		problemasRepositorio.remove(codigo);
	}

	public void apagarObjetivo(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		objetivosRepositorio.remove(codigo);

	}

}