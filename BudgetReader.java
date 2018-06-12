package Users;
/*
 * @author Zhaoxiong Chen
 * @version 1.0
 * @date 08/05/2018
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public interface BudgetReader {

    /*
     * @param mode Electric/Gas/...
     * @param aspc Aspect: Usage/Cost...
     */
    static String getBudget(String userID, String mode){
        String Budget = null;
        try{
            Document doc = UserWriter.loadXMLFiles(userID);

            Element root = doc.getDocumentElement();//Search root node
            NodeList budgetTypes = root.getElementsByTagName("Budget").item(0).getChildNodes();//root -> budget
            ArrayList<Node> filteredBudgetTypes = new ArrayList<Node>();
            for (int i = 0; i < budgetTypes.getLength(); i++){
                if(budgetTypes.item(i) instanceof Element){
                    System.out.println(budgetTypes.item(i).getNodeName());
                    filteredBudgetTypes.add(budgetTypes.item(i));
                }
            }//Filter #text out: #text means the gap between XML labels!!!, which need to be filtered out
            Node typeSelection = null;

            switch (mode){
                case "eU":
                    typeSelection = filteredBudgetTypes.get(0);
                    break;
                case "eC":
                    typeSelection = filteredBudgetTypes.get(1);
                    break;
                case "gU":
                    typeSelection = filteredBudgetTypes.get(2);
                    break;
                case "gC":
                    typeSelection = filteredBudgetTypes.get(3);
                    break;
                default:
                    break;
            }
            return typeSelection.getTextContent();//Return the final result
        }catch (Exception e){
            e.printStackTrace();
        }
        return Budget;
    }
}
