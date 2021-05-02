
//classe parametrizada
public class Fila<T> {
	private No<T> fim;
	private No<T> inicio;
	private String fila[];
	
	
	//metodo para inserir no topo da pilha (inicio)
	public void push(T info) {
		No<T> aux = new No<T>(info);
		if(isEmpty()) { // retornar true se estiver vazia ou false caso contrario
			inicio = aux;
		} else {
			inicio.esq = aux;
			aux.dir = inicio;
			inicio = aux;
		}
		
				
	}
	
	// metodo para verificar se a fila esta vazia
	public boolean isEmpty() {
		return inicio == null? true : false;
	}

	// metodo para remover e retornar o elemento do topo --> nao pode remover se estiver vazia
	public T pop() {
		T elemento = null;
		No<T> aux = inicio;
		
		if(!isEmpty()) {
			if(size() == 1) {
				inicio = null;
			} else {
				inicio = aux.dir;
				aux.dir.esq = null;
				aux.dir = null;
			}
			elemento = aux.info;
			aux = null;

		}
		return elemento;
	}

	// metodo para retornar o tamanho da fila (qtd de elementos) --> size()
	public int size() {
		No<T> aux = inicio;
		int qtd = 0;
		while( aux != null) {
			qtd++;
			aux = aux.dir;
		}
		return qtd;
	}
	

	
}
