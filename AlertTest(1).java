package SEGP_0;

public class AlertTest {

	public static void main(String[] args) {
		HouseHold hou1 = new HouseHold();
		hou1.setEleUsage(100);
		//hou1.setEleCostBudget(1000);
		hou1.setEleSignal();
		if(hou1.getEleSignal()==true){
			System.out.println("Green");
		}
		else{
			System.out.println("Red");
		}

	}

}
