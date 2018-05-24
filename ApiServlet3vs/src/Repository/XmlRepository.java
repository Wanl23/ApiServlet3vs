package Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlRepository { 	
	
	static final String SPF_SCHEMA = System.getenv("SPF_SCHEMA");
	private ArrayList<String> paths = new ArrayList<>();
	
	//putting all maps after parsing in one
	public String getSpxTags() {
		getFilePaths(new File(SPF_SCHEMA));
		Map<String, String> dataMap = new HashMap<String, String>();
		for(String path: paths) {
			try {
				dataMap.putAll(getDataFromFile(path));
			} catch (Exception e) {
				//log error
				e.printStackTrace();
			}
		}
		
		return mapToJson(dataMap);
	}
	//convert map to json format	
	private String mapToJson(Map<String, String> map) {		
		String json = "{";
		
		if(!map.isEmpty()) {
			for(Map.Entry<String, String> entry : map.entrySet()) {
			    String uid = entry.getKey();
			    String name = entry.getValue();
			    json += String.format("[uid:%s, name:%s], " + "\n", uid, name);
			}
			json = json.substring(0, json.length() - 3);//del last 3 simvols
		}
		
		return json += "}";
	} 
	
		//parsing xml file
		private Map<String, String> getDataFromFile(String filePath) throws ParserConfigurationException, IOException, SAXException {
        Map<String, String> map = new HashMap<String, String>();

        File xmlFile = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        NodeList nodeList = doc.getElementsByTagName("EnumEnum");
        for(int i = 0; i < nodeList.getLength(); i++) {
            if(nodeList.item(i).getNodeType() == Element.ELEMENT_NODE) {
                Element node = (Element)nodeList.item(i);
                NodeList iObjectList = node.getElementsByTagName("IObject");
                for(int j = 0; j < iObjectList.getLength(); j++) {
                	if(iObjectList.item(j).getNodeType() == Element.ELEMENT_NODE) {
                		Element iObjectElement = (Element)iObjectList.item(j);
                		String uid = iObjectElement.getAttribute("UID");
                        String name = iObjectElement.getAttribute("Name");
                        map.put(uid, name);
                	}
                }                
            }
        }
        return map;
    }
		
	//get absolute paths to all files
	private void getFilePaths(File folder)
	{
	    File[] folderEntries = folder.listFiles();
	    for (File entry : folderEntries)
	    {
	        if (entry.isDirectory())
	        {
	        	getFilePaths(entry);
	            continue;
	        }
	        else {
	        	paths.add(entry.getAbsolutePath());
	        }
	        
	    }
	}
}
