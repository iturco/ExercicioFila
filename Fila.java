
//classe parametrizada
public class Fila<T> {
	private No<T> fim;
	private No<T> inicio;
	private String fila[];
	private int tamanho;

	// metodo para inserir no final da lista
	public void enqueue(T info) {
		No<T> aux = new No<T>(info);
		if (tamanho == 0) { // verifica se a lista está vazia
			inicio = aux;
			fim = aux;
		} else { // executa a partir do segundo nó da lista
			fim.dir = aux;
			aux.esq = fim;
		}
		fim = aux;
		tamanho++; // incrementando o tamanho da lista
	}

	// metodo para verificar se a fila esta vazia
	public boolean isEmpty() {
		return inicio == fim ? true : false;
	}

	// metodo para remover e retornar o elemento do topo --> nao pode remover se
	// estiver vazia
	public T dequeue() {
		T elemento = null;
		No<T> aux = inicio;

		if (!isEmpty()) {

			inicio = aux.dir;
			aux.dir.esq = null;
			aux.dir = null;

			elemento = aux.info;
			aux = null;

		}
		return elemento;
	}

	// metodo para retornar o tamanho da fila (qtd de elementos) --> size()
	public int size() {
		No<T> aux = inicio;
		int qtd = 0;
		while (aux != null) {
			qtd++;
			aux = aux.dir;
		}
		return qtd;
	}

}
