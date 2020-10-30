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
		//marcados.add(3);
		//marcados.add(4);
		//System.out.println("marcados " + marcados);
		vecinosPendientes = agregarVecinosPendientes(grafo, 0, vecinosPendientes, marcados);
		//System.out.println("menor es " + buscarVecinoMenorPeso(vecinosPendientes,marcados));
		//System.out.println(vecinosPendientes.get(1)[2]);
		
		for(int i = 1; i < grafo.tamano(); i++) //for(int i = 1; i < grafo.tamano(); i++) 
		{
			
			int indice = buscarVecinoMenorPeso(vecinosPendientes, marcados); //el indice tiene la posicion del arraylist 
																	//vecinosPendientes del vecino con menor peso
			System.out.println("indice " + indice);
			System.out.println("marcados " + marcados);
			imprimirVecinos(vecinosPendientes);
			System.out.println("tamano vecino pendientes " + vecinosPendientes.size());
			
			grafoNuevo.agregarArista(vecinosPendientes.get(indice)[0], vecinosPendientes.get(indice)[1],
						vecinosPendientes.get(indice)[2]);
			
			marcados.add(vecinosPendientes.get(indice)[0]);
			System.out.println("se eligio el vertice " + vecinosPendientes.get(indice)[0]);
			
			
			

			vecinosPendientes = agregarVecinosPendientes(grafo, vecinosPendientes.get(indice)[0], vecinosPendientes, marcados);
			vecinosPendientes = eliminarMarcados(grafo, vecinosPendientes, marcados);
			//vecinosPendientes.remove(indice);
			
			System.out.println("alcanzables ciclo " + BFS.alcanzables(grafoNuevo, 0));
			
			
		}
		
		//System.out.println("vecinos " + grafoNuevo.vecinos(1));
		
		
		
		
		//agregarVecinosPendientes()
		System.out.println("marcados " + marcados);

		System.out.println("tamanio grafo nuevo " + grafoNuevo.tamano());
		return grafoNuevo;
	}
	//------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	
	
	
	
	
	private static ArrayList<int[]> agregarVecinosPendientes(Grafo grafo, int vertice, ArrayList<int[]> vPendientes, ArrayList<Integer> marcados) 
	{
		for(Integer it : grafo.vecinos(vertice)) 
		{
			//if(!marcados.contains(vPendientes.get(it)[0])) {
			int[] array = new int[3];   //creo el arreglo que despues se va a guardar en el arraylist
			array[0]= it;
			array[1]= vertice;
			array[2]= grafo.pesoDeArista(it, vertice);
			vPendientes.add(array);
		//}
		} 
		
//		for(int i = 0; i < grafo.vecinos(vertice).size(); i++) 
//		{
//			if(!marcados.contains(vPendientes.get(i)[0])) {
//				int[] array = new int[3];   //creo el arreglo que despues se va a guardar en el arraylist
//				array[0]= i;
//				array[1]= vertice;
//				array[2]= grafo.pesoDeArista(i, vertice);
//				vPendientes.add(array);
//			}
//		}

		return vPendientes;
	}
	
	
	private static ArrayList<int[]> eliminarMarcados(Grafo grafo, ArrayList<int[]> vPendientes, ArrayList<Integer>marcados)
	{
		ArrayList<int[]> temporal = new ArrayList<int[]>();
		 int rango = vPendientes.size();
		for(int i = 0; i < rango; i++) 
		{
			if(!marcados.contains(vPendientes.get(i)[0])) 
			{
				temporal.add(vPendientes.get(i));
			}
		}
		return temporal;
	}
	 
	
	
	@SuppressWarnings("unlikely-arg-type")
	private static int buscarVecinoMenorPeso(ArrayList<int[]> vecinosPendientes, ArrayList<Integer> marcados) 
	{
		//int[] array = vecinosPendientes.get(0);
		int indice = 0;
		int menorPeso = vecinosPendientes.get(0)[2];
		//System.out.println("Menor peso " + menorPeso);
		for(int i = 0; i < vecinosPendientes.size()-1; i++)  //si el tamano es uno devolver ese
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
			System.out.println("menor " + menorPeso);
			if(vecinosPendientes.get(i)[2] <= vecinosPendientes.get(i+1)[2]) // i < i+1
			{
				if(!marcados.contains(vecinosPendientes.get(i)[0]) )  //i no esta marcado
				{
					if(vecinosPendientes.get(i)[2] <= menorPeso){
					menorPeso = vecinosPendientes.get(i)[2];
					indice = i;
					}
				} 
				else {
					if(vecinosPendientes.get(i+1)[2] <= menorPeso) {
					menorPeso = vecinosPendientes.get(i+1)[2];     //si i esta marcado ya agrego i+1
					indice = i+1;
					}
				}
				
			}
			else 
			{
				if(!marcados.contains(vecinosPendientes.get(i+1)[0]) ) // si i+1 no esta marcado
				{
					if(vecinosPendientes.get(i+1)[2] <= menorPeso) {
					menorPeso = vecinosPendientes.get(i+1)[2];
					indice = i+1;
					}
				}
				//////
				else {
					if(vecinosPendientes.get(i)[2] <= menorPeso) 
					{
						menorPeso = vecinosPendientes.get(i)[2];
						indice = i;
					}
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
		
		g = arbolGeneradorMinimo(grafo);
		System.out.println("alcanzables " + BFS.alcanzables(g, 5));
		
		int del = 1;
		System.out.println("vecinos " + del +" g " + g.vecinos(del));
		System.out.println("vecinos "+del  + " grafo " + grafo.vecinos(del));
		
		
	 
	}
}
