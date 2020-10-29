package grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo 
{
	//representamos el grafo por su matriz de adyacencia
//	private boolean [][] A;
	private int [][] A;

	
	//la cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices) 
	{
		A = new int [vertices][vertices];
		
		for(int i = 0; i < vertices; i++) 
		{
			for(int j = 0; j < vertices; j++) 
			{
				A[i][j] = -1;
			}
		}
	}

	
	//Agregado de aristas
//	public void agregarArista(int i, int j) 
//	{ 
//		verificarVerticeValido(i);
//		verificarVerticeValido(j);
//		verificarDistintos(i, j);
//		
//		A[i][j] = true;
//		A[j][i] = true;
//	}
//
//	//Eliminacion de aristas
//	public void eliminarArista(int i, int j) 
//	{
//		verificarVerticeValido(i);
//		verificarVerticeValido(j);
//		verificarDistintos(i, j);
//
//		A[i][j] = false;
//		A[j][i] = false;
//	}
	
	
	//Agregado de aristas
	public void agregarArista(int i, int j, int peso) 
	{
		verificarVerticeValido(i);
		verificarVerticeValido(j);
		verificarDistintos(i, j); 

		A[i][j] = peso;
		A[j][i] = peso;
	}

	//Eliminacion de aristas
	public void eliminarAristaConPeso(int i, int j) 
	{
		verificarVerticeValido(i);
		verificarVerticeValido(j);
		verificarDistintos(i, j);

		A[i][j] = -1;  //no me deja poner null
		A[j][i] = -1;
	}

	//Informa si existe la arista especificada
	public boolean existeArista(int i , int j) 
	{
		verificarVerticeValido(i);
		verificarVerticeValido(j);
		verificarDistintos(i, j);
		return A[i][j] > 0;    //este simbolo me estaba complicando la vida jajajajajajaj :')
	}
	
	
	public int pesoDeArista(int i, int j) 
	{
		return A[i][j];
	}
	
	
	//vecinos de un vertice. es mejor usar set porque es una interfaz y luego dentro usar hashset que es
	//una implementacion de la interfaz set
//	public Set<Integer> vecinos (int i)
//	{
//		verificarVerticeValido(i);
//		
//		Set<Integer> ret = new HashSet<Integer>();
//		for(int j = 0; j< tamano(); ++j) if(i != j)
//		{
//			if( existeArista(i,j))
//				ret.add(j);
//		}
//		return ret;
//	}
	
	
	public ArrayList<Integer> vecinos (int i)
	{
		verificarVerticeValido(i);
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int j = 0; j< tamano(); ++j) if(i != j)
		{
			if( existeArista(i,j))
				ret.add(j);
		}
		return ret;
	}
	
	public int tamano() 
	{ 
		return A.length;
	}
	
	
	//--------------------------------------------------------------------------------------------
	private void verificarVerticeValido(int i) {
		if(i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		if(i >= A.length)
			throw new IllegalArgumentException("Los vertiices deben estar entre 0 y |V| " + i);
	}
	
	private void verificarDistintos(int i, int j) {
		if(i == j)
			throw new IllegalArgumentException("No se permiten loops");
	}
	
}
