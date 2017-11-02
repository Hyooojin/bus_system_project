package com.hyooo.busproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@RestController
public class BusRestController {
	
@RequestMapping(value="getkeyword", method=RequestMethod.GET)		
	public List <StationVO> inputKeyword(@RequestParam("busname") String busNo) {
		// Scanner sc = new Scanner(System.in);
		// System.out.print("버스번호: ");
		// String bus_keyword = sc.nextLine();
		System.out.println(busNo);
		String routeId = null;
		List <StationVO> stationlist = null;
		try {
			routeId = getId(busNo);
			System.out.println(routeId);
			
			stationlist = getBusStation(routeId);
			for(StationVO stationVO:stationlist) {
				System.out.println(stationVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stationlist;
	}


	
	public static String getId(String busNo) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice");
		urlBuilder.append(
				"?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
		urlBuilder.append("&keyword=" + busNo);
		//System.out.println(busNo);

		Document doc = builder.parse(urlBuilder.toString());

		NodeList list = doc.getElementsByTagName("routeId");

		int i = 0;
		Element element;
		String contents = null;

		while (list.item(i) != null) {
			element = (Element) list.item(i);
			contents = element.getTextContent();
			//System.out.println(contents);
			
			i++; 
		}
		return contents;
	}
	

	
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList busList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		//NodeList busList = doc.getElementsByTagName("busRouteStationList");
		Node busValue = (Node) busList.item(0);
		if(busValue == null)
			return null;
		return busValue.getNodeValue();
	}
	
	
	public static List <StationVO> getBusStation(String routeId) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/station");
		urlBuilder.append(
				"?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
		urlBuilder.append("&routeId=" +routeId);
		
		Document doc = builder.parse(urlBuilder.toString());
		NodeList list = doc.getElementsByTagName("busRouteStationList");
		
		int i = 0;
		String contents = null;
		List <StationVO> stationlist = new ArrayList<StationVO>();
		for(i = 0; i < list.getLength(); i++){		
			Node nNode = list.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				StationVO stationVO = new StationVO();
				Element eElement = (Element) nNode;
				System.out.println("######################");
				//System.out.println(eElement.getTextContent());
				stationVO.setStationName(getTagValue("stationName", eElement));
				stationVO.setStationSeq(getTagValue("stationSeq", eElement));
				stationVO.setX(getTagValue("x", eElement));
				stationVO.setY(getTagValue("y", eElement));
/*				System.out.println("상품명 : " + getTagValue("fin_prdt_nm", eElement));
				System.out.println("연평균 수익률  : " + getTagValue("avg_prft_rate", eElement));
				System.out.println("공시 이율  : " + getTagValue("dcls_rate", eElement));*/
				stationlist.add(stationVO);
			}	// for end
		}	// if end
		return 	stationlist;
	}
	
	
}
