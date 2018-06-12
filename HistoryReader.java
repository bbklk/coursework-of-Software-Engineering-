/*
 * @author Peize Zhao
 * @version 1.0
 * @date 16/05/2018
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

import java.io.File;
import java.util.Calendar;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

interface HistoryReaderI {

    /*
     * @param mode Electric/Gas/...
     * @param aspc Aspect: Usage/Cost...
     */
    static void getMonthEHistory(String userID, String month){
        String History = null;
        try{
            Document doc = loadXMLFiles(userID);

            Element root = doc.getDocumentElement();//Search root node
            NodeList electricRecord = root.getElementsByTagName("ERecord").item(0).getChildNodes();//root -> ERecord
            ArrayList<Node> filteredElectricRecord = new ArrayList<Node>();

            for (int i = 0; i < electricRecord.getLength(); i++){
                if(electricRecord.item(i) instanceof Element){
                    System.out.println(electricRecord.item(i).getNodeName());
                    if (electricRecord.item(i).getElementsByTagName("Month").item(0).getTextContent().equals(month)) {
                    	filteredElectricRecord.add(electricRecord.item(i));
                    }
                }
            }//Filter #text out: #text means the gap between XML labels!!!, which need to be filtered out
            System.out.println("The history usage of electricity in" + month + ":\n");
            for (int i = 0; i<filteredElectricRecord.size(); i++){
            	System.out.println("Usage\tCost\tTariff\n" + filteredElectricRecord.get(i).getFirstChild().getTextContent() + "\t" 
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\t"
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void getDayEHistory(String userID, String month, String day){
        try{
            Document doc = loadXMLFiles(userID);

            Element root = doc.getDocumentElement();//Search root node
            NodeList electricRecord = root.getElementsByTagName("ERecord").item(0).getChildNodes();//root -> ERecord
            ArrayList<Node> filteredElectricRecord = new ArrayList<Node>();

            for (int i = 0; i < electricRecord.getLength(); i++){
                if(electricRecord.item(i) instanceof Element){
                    System.out.println(electricRecord.item(i).getNodeName());
                    if ((electricRecord.item(i).getElementsByTagName("Day").item(0).getTextContent().equals(day)) && 
                        (electricRecord.item(i).getElementsByTagName("Month").item(0).getTextContent().equals(month))){
                    filteredElectricRecord.add(electricRecord.item(i));
                    }
                }
            }//Filter #text out: #text means the gap between XML labels!!!, which need to be filtered out
            System.out.println("The history usage of electricity on" + month + "." + day + ":\n");
            for (int i = 0; i<filteredElectricRecord.size(); i++){
                System.out.println("Usage\tCost\tTariff\n" + filteredElectricRecord.get(i).getFirstChild().getTextContent() + "\t" 
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\t"
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void getWeekEHistory(String userID, String week){
        try{
            Document doc = loadXMLFiles(userID);

            Element root = doc.getDocumentElement();//Search root node
            NodeList electricRecord = root.getElementsByTagName("ERecord").item(0).getChildNodes();//root -> ERecord
            ArrayList<Node> filteredElectricRecord = new ArrayList<Node>();

            for (int i = 0; i < electricRecord.getLength(); i++){
                if(electricRecord.item(i) instanceof Element){
                    System.out.println(electricRecord.item(i).getNodeName());
                    if ((electricRecord.item(i).getElementsByTagName("Week").item(0).getTextContent().equals(week))){
                        filteredElectricRecord.add(electricRecord.item(i));
                    } 
                }
            }//Filter #text out: #text means the gap between XML labels!!!, which need to be filtered out
            System.out.println("The history usage of electricity on" + week + "week:\n");
            for (int i = 0; i<filteredElectricRecord.size(); i++){
                System.out.println("Usage\tCost\tTariff\n" + filteredElectricRecord.get(i).getFirstChild().getTextContent() + "\t" 
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\t"
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    static void getMonthGHistory(String userID, String month, String day, String week){
        String History = null;
        try{
            Document doc = loadXMLFiles(userID);

            Element root = doc.getDocumentElement();
            NodeList gasRecord = root.getElementsByTagName("GRecord").item(0).getChildNodes();
            ArrayList<Node> filteredGasRecord = new ArrayList<Node>();


            for (int i = 0; i < gasRecord.getLength(); i++){
                if(gasRecord.item(i) instanceof Element){
                    System.out.println(gasRecord.item(i).getNodeName());
                    if (gasRecord.item(i).getElementsByTagName("Month").item(0).getTextContent().equals(month)){
                    	filteredGasRecord.add(gasRecord.item(i));
                    } 
                }
            }
            System.out.println("The history usage of gas in" + month + ":\n");
            for (int i = 0; i<filteredGasRecord.size(); i++){
            	System.out.println("Usage\tCost\tTariff\n" + filteredElectricRecord.get(i).getFirstChild().getTextContent() + "\t" 
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\t"
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\n");
            } 
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void getDayGHistory(String userID, String month, String day, String week){
        try{
            Document doc = loadXMLFiles(userID);

            Element root = doc.getDocumentElement();//Search root node
            NodeList gasRecord = root.getElementsByTagName("GRecord").item(0).getChildNodes();//root -> ERecord
            ArrayList<Node> filteredGasRecord = new ArrayList<Node>();

            for (int i = 0; i < gasRecord.getLength(); i++){
                if(gasRecord.item(i) instanceof Element){
                    System.out.println(gasRecord.item(i).getNodeName());
                    if ((gasRecord.item(i).getElementsByTagName("Day").item(0).getTextContent().equals(day)) && 
                        (gasRecord.item(i).getElementsByTagName("Month").item(0).getTextContent().equals(month))){
                        filteredGasRecord.add(gasRecord.item(i));
                    } 
                }
            }//Filter #text out: #text means the gap between XML labels!!!, which need to be filtered out
            System.out.println("The history usage of gas on" + month + "." + day + ":\n");
            for (int i = 0; i<filteredGasRecord.size(); i++){
                System.out.println("Usage\tCost\tTariff\n" + filteredElectricRecord.get(i).getFirstChild().getTextContent() + "\t" 
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\t"
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void getWeekGHistory(String userID, String month, String day, String week){
        try{
            Document doc = loadXMLFiles(userID);

            Element root = doc.getDocumentElement();//Search root node
            NodeList gasRecord = root.getElementsByTagName("GRecord").item(0).getChildNodes();//root -> ERecord
            ArrayList<Node> filteredGasRecord = new ArrayList<Node>();

            for (int i = 0; i < gasRecord.getLength(); i++){
                if(gasRecord.item(i) instanceof Element){
                    System.out.println(gasRecord.item(i).getNodeName());
                    if ((gasRecord.item(i).getElementsByTagName("Week").item(0).getTextContent().equals(week))){
                        filteredGasRecord.add(gasRecord.item(i));
                    }
                }
            }//Filter #text out: #text means the gap between XML labels!!!, which need to be filtered out
            System.out.println("The history usage of gas on" + week + "week:\n");
            for (int i = 0; i<filteredGasRecord.size(); i++){
                System.out.println("Usage\tCost\tTariff\n" + filteredElectricRecord.get(i).getFirstChild().getTextContent() + "\t" 
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\t"
                                                           + filteredElectricRecord.get(i).getNextSibling().getTextContent() + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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

public class HistoryReader implements HistoryReaderI
{
	public static void main(String[] args) 
	{
		HistoryReaderI.getDayEHistory("00000000v2","5","9");
	}
}