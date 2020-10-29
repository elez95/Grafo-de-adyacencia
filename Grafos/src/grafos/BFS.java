package grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS 
{

	public static boolean esConexo(Grafo grafo) 
	{
		ArrayList<Integer> pendientes = new ArrayList<Integer>();
		HashSet<Integer> marcados = new HashSet<Integer>();
		pendientes.add(0);
		
		while(pendientes.size() != 0) 
		{
			int actual = pendientes.get(0);
			marcados.add(actual);
			pendientes.remove(0);
			
			for(Integer vecino : grafo.vecinos(actual)) 
			{
				if(! marcados.contains(vecino))
						{
							pendientes.add(vecino);
						}
			}
		}
		if(grafo.tamano() == marcados.size())
			return true; 
		else
			return false;
	}
	
	public static Set<Integer> alcanzables (Grafo grafo, int origen)
	{
		Set<Integer> marcados = new HashSet<Integer>();
		ArrayList<Integer> pendientes = new ArrayList<Integer>();
		pendientes.add(origen);
		
		while(pendientes.size() != 0) 
		{
			int actual = pendientes.get(0); ///////
			marcados.add(actual);
			pendientes.remove(0);
			
			for(Integer v: grafo.vecinos(actual)) 
			{
				if(marcados.contains(v) == false) 
				{
					pendientes.add(v);
				}
			}
		}
		return marcados;
	}
	
}
