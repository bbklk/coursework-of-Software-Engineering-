package Users;

/*
 * Provided basic prototype to create and fill in the information.
 * @author Jiuchen Guo
 * @date
 * @version 1.0
 *
 * Totally modified the prototype and make the program satisfied the need of the scenario.
 * @author Zhaoxiong Chen
 * @date 07/05/2018
 * @version 2.0
 *
 * Modified the structure to fix the logical bug of saving historical record.
 * @author Zhaoxiong Chen
 * @date 17/05/2018
 * @version 2.2
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public interface UserWriter {

    static void createBudgetFrame(String userID){
        try{
            File file = new File(userID);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element root = doc.createElement("User");//Create root node called userID

            Element Budget = doc.createElement("Budget");//Create node called Budget
            root.appendChild(Budget);//Set Budget as child node of Root

            Element ElectricUsageBudget = doc.createElement("ElectricUsageBudget");//Create node called ElectricUsage
            Element ElectricCostBudget = doc.createElement("ElectricCostBudget");//Create node called ElectricCost
            Element GasUsageBudget = doc.createElement("GasUsageBudget");//Create node called GasUsage
            Element GasCostBudget = doc.createElement("GasCostBudget");//Create node called GasCost
            Budget.appendChild(ElectricUsageBudget);//Set as child of Budget
            Budget.appendChild(ElectricCostBudget);//Set as child of Budget
            Budget.appendChild(GasUsageBudget);//Set as child of Budget
            Budget.appendChild(GasCostBudget);//Set as child of Budget

            doc.appendChild(root);//Set root node as doc's root node
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer tf = tFactory.newTransformer();
            //tf.setOutputProperty(OutputKeys.INDENT, "yes");//Control format
            tf.transform(new DOMSource(doc), new StreamResult(file + ".xml"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void setBudget(String userID, String mode, double usageBudget){
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
            typeSelection.setTextContent(Double.toString(usageBudget));
            //Block of Updating XML files
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer tf = tFactory.newTransformer();
            tf.transform(new DOMSource(doc), new StreamResult(new File(userID + ".xml")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    static void addERecord(String userID, int eUsage, double eTariff){
        try{
            Document doc = UserWriter.loadXMLFiles(userID);

            Element root = (Element) doc.getElementsByTagName("User").item(0);//Search root node
            Element ERecord = doc.createElement("ERecord");//Create node called ERecord
            root.appendChild(ERecord);//Set ERecord as child node of Root

            Element Timestamp = doc.createElement("Timestamp");//Create node called Timestamp to record time
            ERecord.appendChild(Timestamp);//Set Timestamp as child node of ERecord

            Calendar Cal = Calendar.getInstance();

            UserWriter.timestampFill(Cal, doc, Timestamp);//Fill in time information

            Element Usage = doc.createElement("Usage");//Create node called Usage
            ERecord.appendChild(Usage);//Set Usage as child node of ERecord
            //Block of adding Element ElectricUsage to Usage and record usage
            Element ElectricUsage = doc.createElement("ElectricUsage");//Create Element ElectricUsage
            ElectricUsage.setTextContent(Integer.toString(eUsage));//Write current usage
            Usage.appendChild(ElectricUsage);//Add new element to Usage
            //Block of adding Element ElectricCost to Usage and record usage
            Element ElectricCost = doc.createElement("ElectricCost");//Create Element ElectricCost
            ElectricCost.setTextContent(Double.toString(eUsage * eTariff));//Write current usage
            Usage.appendChild(ElectricCost);//Add new element to Usage
            //Block of adding Element ElectricTariff to Usage and record current Tariff of Electric
            Element ElectricTariff = doc.createElement("ElectricTariff");//Create Element ElectricTariff
            ElectricTariff.setTextContent(Double.toString(eTariff));//Write current tariff
            Usage.appendChild(ElectricTariff);//Add new element to Usage

            //Block of Updating XML files
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer tf = tFactory.newTransformer();
            //tf.setOutputProperty(OutputKeys.INDENT, "yes");//Control the format of final output
            tf.transform(new DOMSource(doc), new StreamResult(new File(userID + ".xml")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void addGRecord(String userID, double gUsage, double gTariff){
        try{
            Document doc = UserWriter.loadXMLFiles(userID);

            Element root = (Element) doc.getElementsByTagName("User").item(0);//Search root node
            Element GRecord = doc.createElement("GRecord");//Create node called GRecord
            root.appendChild(GRecord);//Set Record as child node of Root

            Element Timestamp = doc.createElement("Timestamp");//Create node called Timestamp to record time
            GRecord.appendChild(Timestamp);//Set Timestamp as child node of Record

            Calendar Cal = Calendar.getInstance();

            UserWriter.timestampFill(Cal, doc, Timestamp);

            Element Usage = doc.createElement("Usage");//Create node called Usage
            GRecord.appendChild(Usage);//Set Usage as child node of GRecord
            //Block of adding Element GasUsage to Usage and record usage
            Element GasUsage = doc.createElement("GasUsage");//Create Element GasUsage
            GasUsage.setTextContent(Double.toString(gUsage));//Write current usage
            Usage.appendChild(GasUsage);//Add new element to Usage
            //Block of adding Element GasCost to Usage and record usage
            Element GasCost = doc.createElement("GasCost");//Create Element GasCost
            GasCost.setTextContent(Double.toString(gUsage * gTariff));//Write current usage
            Usage.appendChild(GasCost);//Add new element to Usage
            //Block of adding Element GasTariff to Usage and record current Tariff of Gas
            Element GasTariff = doc.createElement("GasTariff");//Create Element GasTariff
            GasTariff.setTextContent(Double.toString(gTariff));//Write current tariff
            Usage.appendChild(GasTariff);//Add new element to Usage

            //Block of Updating XML files
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer tf = tFactory.newTransformer();
            //tf.setOutputProperty(OutputKeys.INDENT, "yes");//Control the format of final output
            tf.transform(new DOMSource(doc), new StreamResult(new File(userID + ".xml")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void timestampFill(Calendar Cal, Document doc, Element Timestamp){
        //Block of adding Element Year to Timestamp and write current time
        Element Year = doc.createElement("Year");//Create Element Year
        Year.setTextContent(String.valueOf(Cal.get(Calendar.YEAR)));//Write current time
        Timestamp.appendChild(Year);//Add new element to Timestamp
        //Block of adding Element Month to Timestamp and write current time
        Element Month = doc.createElement("Month");//Create Element Month
        Month.setTextContent(String.valueOf(Cal.get(Calendar.MONTH)+1));//Write current time
        Timestamp.appendChild(Month);//Add new element to Timestamp
        //Block of adding Element Day to Timestamp and write current time
        Element Day = doc.createElement("Day");//Create Element Day
        Day.setTextContent(String.valueOf(Cal.get(Calendar.DAY_OF_MONTH)));//Write current time
        Timestamp.appendChild(Day);//Add new element to Timestamp
        //Block of adding Element Time to Timestamp and write current time
        Element Time = doc.createElement("Time");//Create Element Time
        Time.setTextContent(clockGen(Cal));//Write current time
        Timestamp.appendChild(Time);//Add new element to Timestamp
        //Block of adding Element Week to Timestamp and write current time
        Element Week = doc.createElement("Week");//Create Element Week
        Week.setTextContent(String.valueOf(Cal.get(Calendar.WEEK_OF_YEAR)));//Write current time
        Timestamp.appendChild(Week);//Add new element to Timestamp
    }

    private static String clockGen(Calendar Cal){
        StringBuffer timeValue = new StringBuffer();
        if(Cal.get(Calendar.HOUR) < 10){
            timeValue.append("0" + String.valueOf(Cal.get(Calendar.HOUR)) + ":");
        }
        else{
            timeValue.append(String.valueOf(Cal.get(Calendar.HOUR)) + ":");
        }
        if(Cal.get(Calendar.MINUTE) < 10){
            timeValue.append("0" + String.valueOf(Cal.get(Calendar.MINUTE)) + ":");
        }
        else{
            timeValue.append(String.valueOf(Cal.get(Calendar.MINUTE)) + ":");
        }if(Cal.get(Calendar.SECOND) < 10){
            timeValue.append("0" + String.valueOf(Cal.get(Calendar.SECOND)));
        }
        else{
            timeValue.append(String.valueOf(Cal.get(Calendar.SECOND)));
        }
        return timeValue.toString();
        //Set timeValue format to storage only hours, minutes and seconds
    }

    static Document loadXMLFiles(String name){
        Document doc = null;
        try{
            //Open and load the xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(name + ".xml");
            doc.setXmlStandalone(true);//Declare XML, set standalone as true to make standalone disappear
        }catch (Exception e){
            e.printStackTrace();
        }
        return doc;
    }
}
