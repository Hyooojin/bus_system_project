package com.hyooo.busproject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.springframework.beans.factory.support.GenericTypeAwareAutowireCandidateResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TimeTableTest {

	@Test
	public void inputKeyword() {
		Scanner sc = new Scanner(System.in);
		System.out.print("버스번호: ");
		String bus_keyword = sc.nextLine();
		System.out.println(bus_keyword);
		String routeId = null;
		try {
			routeId = getId(bus_keyword);
			System.out.println(routeId);
			getBusStations(routeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getId(String busNo) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice");
		urlBuilder.append(
				"?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
		urlBuilder.append("&keyword=" + busNo);

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
	
	
	public static void getBusStations(String routeId) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/station");
		urlBuilder.append(
				"?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
		urlBuilder.append("&routeId=" +routeId);
		Document doc = builder.parse(urlBuilder.toString());

		String[] elem = {
				 "centerYn", 
				 "districtCd",
					"mobileNo",
					"regionName",
					"stationId",
					"stationName",
					"x",
					"y",
					"stationSeq",
					"turnYn"	
		};
		
		int i = 0;
		String contents = null;
		

		NodeList list = doc.getElementsByTagName("busRouteStationList");
		while (list.item(i) != null) {
			StationVO stationVO = new StationVO();
			for(int e=0; e < elem.length; e++) {
				NodeList elemlist = doc.getElementsByTagName(elem[e]);
				Element element=(Element) elemlist.item(i);
				contents = element.getTextContent();
	
				i++; 
			}
		}
		
	}
	
	
//	 public void ApiBringBusstop() {
//			
//		 StringBuilder urlBuilder = new
//		 StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/station");
//		 urlBuilder.append("?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
//		 urlBuilder.append("&routeId=" + "");
//		
//		 URL url3 = new URL(urlBuilder.toString());
//		 HttpURLConnection conn = (HttpURLConnection) url3.openConnection();
//		 conn.setRequestMethod("GET");
//		 conn.setRequestProperty("Content-type", "application/xml");
//		 System.out.println("Response code: " + conn.getResponseCode());
//		
//		 BufferedReader rd;
//		 if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//		 rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		 } else {
//		 rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//		 }
//		
//		 StringBuilder sb = new StringBuilder();
//		 String line;
//		 while ((line = rd.readLine()) != null) {
//		 sb.append(line);
//		 }
//		 rd.close();
//		 conn.disconnect();
//		 System.out.println(sb.toString());
//		
//		
//		 }
//		
//		 public void getTime() {
//		
//		 }
//		
//		 public void getTimetable() {
//		
//		 }
//	
//	
	
	
	
	
	

	/*private String getId(String xml) throws ParserConfigurationException, SAXException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Document doc = parseXML(is);

		System.out.println(doc.getElementsByTagName("routeId"));

		return null;
	}

	private Document parseXML(InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String ApiBringKeyword(String bus_keyword) throws Exception {

		StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice");
		urlBuilder.append(
				"?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
		urlBuilder.append("&keyword=" + bus_keyword);

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode());

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		return sb.toString();
	}

	// 받아온 값 중에 routeId 파싱 후, 저장, 다른 method에서 사용하기

	// public void ApiBringStartEnd() {
	//
	// StringBuilder urlBuilder = new
	// StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/info");
	// urlBuilder.append("?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
	// urlBuilder.append("&routeId=" + "");
	//
	// URL url2 = new URL(urlBuilder.toString());
	// HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
	// conn.setRequestMethod("GET");
	// conn.setRequestProperty("Content-type", "application/xml");
	// System.out.println("Response code: " + conn.getResponseCode());
	//
	// BufferedReader rd;
	// if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	// rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	// } else {
	// rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	// }
	//
	// StringBuilder sb = new StringBuilder();
	// String line;
	// while ((line = rd.readLine()) != null) {
	// sb.append(line);
	// }
	// rd.close();
	// conn.disconnect();
	// System.out.println(sb.toString());
	//
	//
	// }
	//
	 public void ApiBringBusstop() {
	
	 StringBuilder urlBuilder = new
	 StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/station");
	 urlBuilder.append("?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
	 urlBuilder.append("&routeId=" + "");
	
	 URL url3 = new URL(urlBuilder.toString());
	 HttpURLConnection conn = (HttpURLConnection) url3.openConnection();
	 conn.setRequestMethod("GET");
	 conn.setRequestProperty("Content-type", "application/xml");
	 System.out.println("Response code: " + conn.getResponseCode());
	
	 BufferedReader rd;
	 if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	 rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 } else {
	 rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	 }
	
	 StringBuilder sb = new StringBuilder();
	 String line;
	 while ((line = rd.readLine()) != null) {
	 sb.append(line);
	 }
	 rd.close();
	 conn.disconnect();
	 System.out.println(sb.toString());
	
	
	 }
	
	 public void getTime() {
	
	 }
	
	 public void getTimetable() {
	
	 }


*/
	
}