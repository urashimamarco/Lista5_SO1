package controller;
import java.util.concurrent.Semaphore;

public class ThreadMultiprocessamento extends Thread{
	
	private Semaphore BD;
	private int idThread;
	private static int posCalc;
	private double tempCalc = 0.0;
	private static int execBD;
	
	public ThreadMultiprocessamento(int idThread, Semaphore BD){
		this.idThread = idThread;
		this.BD = BD;
	}
	
	public void run(){
		calculos();
		try{
			BD.acquire();
			transacaoBD();
		} catch (InterruptedException e){
			e.printStackTrace();
		}finally{
			BD.release();
			concluido();
		}
		
	}
	
	public void calculos(){
		
		if (idThread % 3 == 1) 
		{
			tempCalc = ((Math.random() * 1001.0) + 200.0);
		}
		else if (idThread % 3 == 2)
		{
			tempCalc = ((Math.random() * 1501.0) + 500.0);
		}
		else
		{	
			tempCalc = ((Math.random() * 2001.0) + 1000.0);
		}
		try {
			sleep((long) tempCalc);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		posCalc++;
		System.out.println("#" + idThread + "-> foi a " + posCalc + "a calcular");
	}
	
	public void transacaoBD(){
		System.out.println("#" + idThread + " está executando a transação de BD");
		if (idThread % 3 == 1)
		{
			tempCalc = 1001.0;
		} 
		else if (idThread % 3 == 2)
		{
			tempCalc = 1501.0;
		}
		else
		{
			tempCalc = 1501.0;
		}
		try {
			sleep((long) tempCalc);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void concluido() {
		execBD++;
		System.out.println("#" + idThread + " -> foi a " + execBD +"a concluir a transação.");
	}
}