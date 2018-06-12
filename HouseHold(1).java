package SEGP_0;

public class HouseHold {
	
	private int eleUsage;
	private int eleUsageBudget;
	private double eleTariff;
	private double eleCostBudget;
	private boolean eleSignal;
	
	public HouseHold(){
		this.eleUsage=0;
		this.eleUsageBudget=200;
		this.eleTariff=14.60;
		this.eleCostBudget=2000;
		this.eleSignal=true;
	}
	
	public int getEleUsage(){
		return eleUsage;
	}
	
	public int getEleUsageBudget(){
		return eleUsageBudget;
	}
	
	public double getEleCostBudget(){
		return eleCostBudget;
	}
	
	public boolean getEleSignal(){
		return eleSignal;
	}
	
	public void setEleUsage(int eleUsage){
		this.eleUsage=eleUsage;
	}
	
	public void setEleUsageBudget(int eleUsageBudget){
		this.eleUsageBudget=eleUsageBudget;
	}
	
	public void setEleCostBudget(double eleCostBudget){
		this.eleCostBudget=eleCostBudget;
	}
	
	public void setEleSignal(){
		this.eleSignal=checkEleBudget();
	}
	
	public boolean checkEleBudget(){
		if(this.eleUsage>=this.eleUsageBudget||this.eleUsage*this.eleTariff>=this.eleCostBudget)
			return false;
		else
			return true;
	}

}
