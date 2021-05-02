
//classe parametrizada
public class Fila<T> {
	private No<T> topo;
	
	
	//metodo para inserir no topo da pilha (inicio)
	public void push(T info) {
		No<T> aux = new No<T>(info);
		if(isEmpty()) { // retornar true se estiver vazia ou false caso contrario
			topo = aux;
		} else {
			topo.esq = aux;
			aux.dir = topo;
			topo = aux;
		}
		
				
	}
	
	// metodo para verificar so o topo da pilha é igual a null
	public boolean isEmpty() {
		return topo == null? true : false;
	}

	// metodo para remover e retornar o elemento do topo --> nao pode remover se estiver vazia
	public T pop() {
		T elemento = null;
		No<T> aux = topo;
		
		if(!isEmpty()) {
			if(size() == 1) {
				topo = null;
			} else {
				topo = aux.dir;
				aux.dir.esq = null;
				aux.dir = null;
			}
			elemento = aux.info;
			aux = null;

		}
		return elemento;
	}

	// metodo para retornar o tamanho da pilha (qtd de elementos) --> size()
	public int size() {
		No<T> aux = topo;
		int qtd = 0;
		while( aux != null) {
			qtd++;
			aux = aux.dir;
		}
		return qtd;
	}
	
	// metodo para retornar o elemento armazenado no topo, se a pilha não estiver vazia
	public T top() {
		if(isEmpty()) {
			return null;
		}
		return topo.info;
		
	}
	
}
