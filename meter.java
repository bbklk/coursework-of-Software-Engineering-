public class meter{

	private int eleUsage;
	private int gasUsage;
	private double eleTariff;
	private double gasTariff;

	public meter(){
		this.eleTariff = 1234.4;
		this.gasTariff = 4321.1;
		this.eleUsage = 100;
		this.gasUsage = 200;
	}

	public int getEleTariff(){
		return eleTariff;
	}
	public int getGasTariff(){
		return gasTariff;
	}
	public void sendeleUsage(){
		System.out.print("The usage of electricity has been sent to energy provider!\n");
	}
	public void sendgasUsage(){
		System.out.print("The usage of gas has been sent to energy provider!\n");
	}
}