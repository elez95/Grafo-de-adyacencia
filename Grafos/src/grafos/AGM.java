package grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.sound.midi.Synthesizer;

public class AGM {
	//------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	public static Grafo arbolGeneradorMinimo(Grafo grafo) 
	{
		//creo que primero tengo que preguntar si es conexo
		Grafo grafoNuevo = new Grafo(grafo.tamano());
		
		ArrayList<Integer> marcados = new ArrayList<Integer>();
		
		ArrayList<int[]> vecinosPendientes = new ArrayList<int[]>(); 
		
		marcados.add(0);
		marcados.add(3);
		marcados.add(5);
		System.out.println("marcados " + marcados);
		vecinosPendientes = agregarVecinosPendientes(grafo, 1, vecinosPendientes);
		System.out.println("menor es " + buscarVecinoMenorPeso(vecinosPendientes,marcados));
		//System.out.println(vecinosPendientes.get(1)[2]);
		
//		for(int i = 1; i < grafo.tamano(); i++) 
//		{
//			
//			int indice = buscarVecinoMenorPeso(vecinosPendientes, marcados); //el indice tiene la posicion del arraylist 
//																	//vecinosPendientes del vecino con menor peso
//			grafoNuevo.agregarArista(vecinosPendientes.get(indice)[0], vecinosPendientes.get(indice)[1],
//						vecinosPendientes.get(indice)[2]);
//			
//		}
		
		
		imprimirVecinos(vecinosPendientes);
		
		
		
		//agregarVecinosPendientes()
		
		return grafoNuevo;
	}
	//------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	
	
	
	
	
	private static ArrayList<int[]> agregarVecinosPendientes(Grafo grafo, int vertice, ArrayList<int[]> vPendientes) 
	{
		for(Integer it : grafo.vecinos(vertice)) 
		{
			int[] array = new int[3];   //creo el arreglo que despues se va a guardar en el arraylist
			array[0]= it;
			array[1]= vertice;
			array[2]= grafo.pesoDeArista(it, vertice);
			vPendientes.add(array);
		} 

		return vPendientes;
	}
	 
	
	
	private static int buscarVecinoMenorPeso(ArrayList<int[]> vecinosPendientes, ArrayList<Integer> marcados) 
	{
		//int[] array = vecinosPendientes.get(0);
		int indice = 0;
		int menorPeso = 10;
		System.out.println("Menor peso " + menorPeso);
		for(int i = 0; i < vecinosPendientes.size()-1; i++)  
		{
//			System.out.println("vecinosPendientes.get(i)[2] " + vecinosPendientes.get(i)[2]);
//			if(vecinosPendientes.get(i)[2] < menorPeso /*&& !marcados.contains(vecinosPendientes.get(i)[0])*/) 
//			{
//				//Integer in = vecinosPendientes.get(i)[0];
//				//System.out.println("in " + in);
//				//System.out.println(marcados);
//				if(!marcados.contains(vecinosPendientes.get(i)[0])){
//					menorPeso = vecinosPendientes.get(i)[2];
//					indice = i;
//				}
//				else {}
//			}
			
			if(vecinosPendientes.get(i)[2] <= vecinosPendientes.get(i+1)[2]) 
			{
				if(!marcados.contains(vecinosPendientes.get(i)[0])) 
				{
					menorPeso = vecinosPendientes.get(i)[2];
					indice = i;
				} 
				else if (!marcados.contains(vecinosPendientes.get(i+1)[0])) 
				{
					menorPeso = vecinosPendientes.get(i+1)[2];
					indice = i+1;
				}
				
			}
			else 
			{
				if(!marcados.contains(vecinosPendientes.get(i+1)[0])) 
				{
					menorPeso = vecinosPendientes.get(i+1)[2];
					indice = i+1;
				}
			}
		}
		
		return indice;
	}
	
	
	
	
	
	
	
	private static void imprimirVecinos (ArrayList<int[]> vecinosPendientes) 
	{
		for(int j = 0; j < vecinosPendientes.size(); j++) { 
			int[] prueba = vecinosPendientes.get(j);
			for(int i = 0; i < prueba.length; i++) 
			{
				System.out.print(prueba[i] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		Grafo grafo = new Grafo(6);
		grafo.agregarArista(0, 1, 2);
		grafo.agregarArista(0, 2, 4);
		grafo.agregarArista(1, 2, 9);
		grafo.agregarArista(2, 3, 5);
		grafo.agregarArista(1, 3, 3);
		grafo.agregarArista(1, 5, 7);
		grafo.agregarArista(3, 4, 6);
		grafo.agregarArista(4, 5, 4);
		
		Grafo g = new Grafo (6);
		//System.out.println(grafo.vecinos(0));
		
		arbolGeneradorMinimo(grafo);
		
	 
	}
}
