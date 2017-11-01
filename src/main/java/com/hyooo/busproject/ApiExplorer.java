package com.hyooo.busproject;
 
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/info"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("routeId","UTF-8") + "=" + URLEncoder.encode("229000111", "UTF-8")); /*�뼱 ID*/
        
        // http://openapi.gbis.go.kr/ws/rest/busrouteservice/info?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&routeId=229000111
        
        // http://openapi.gbis.go.kr/ws/rest/busrouteservice/info?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&routeId=100100282
        // "ServiceKey" %2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D 
        // "URL" http://openapi.gbis.go.kr/ws/rest/busrouteservice/line 
        // "routedId" ...
        // http://openapi.gbis.go.kr/ws/rest/busrouteservice/info?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&routeId=100100282
        
        //http://openapi.gbis.go.kr/ws/rest/busrouteservice?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&keyword=M7426
        
        
        // 번스번호를 입력받고, 버스번호의 Id를 조회
        // 노선번호목록조회
        // http://openapi.gbis.go.kr/ws/rest/busrouteservice?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&keyword=M7426
        // keyword&routeId&routeName
        
        // 조회한 버스번호Id를 가지고 시작정류장과 끝정류장에 대한 정보를 가져온다. 
        // 노선정보항목조회
        // http://openapi.gbis.go.kr/ws/rest/busrouteservice/info?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&routeId=229000111
        // routeId&routeName&startStationId&startStationName&startMobileNo&endStationId&endStationName&endStaionNo
        
        // 조회한 버스번호Id를 가지고 해당 정류소목록을 가져온다. 
        // 경유정류소목록조회
        // http://openapi.gbis.go.kr/ws/rest/busrouteservice/station?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&routeId=229000111
        // routeId&BusRouteStation&stationId&stationSeq&stationName&mobileNo
        
        
        // 버스번호를 조회한 시간을 servertime 그대로 자동으로 입력받는다.
        // 시간에 가장 가까운 시간표 네개를 뿌려준다.
        
        // 시간표 
        // 전체 시간표
        // 입력받은 시간에 가까이 위치한 시간표
        
        // 시작시간, 끝나는 시간 ( 첫차, 막차)
        
        
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
    
    
    /*@Test
    public void test() throws UnsupportedEncodingException {
    	StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/line"); URL
        urlBuilder.append("?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D"); Service Key
        urlBuilder.append("&routeId=100100282"); �뼱 ID
        
        
        // http://openapi.gbis.go.kr/ws/rest/busrouteservice/info?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D&routeId=100100282
        // "ServiceKey" %2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D 
        // "URL" http://openapi.gbis.go.kr/ws/rest/busrouteservice/line 
        // "routedId" ...
        
        
    	try {
			System.out.println(getUrlContent(urlBuilder.toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private String getUrlContent(String strUrl) throws Exception {  
        URL u = new URL(strUrl);  
        URLConnection uc = u.openConnection();  
      
        uc.setDoOutput(true);  
        uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
          
        String headerType = uc.getContentType();  
          
        BufferedReader in;  
        if (headerType.toUpperCase().indexOf("UTF-8") != -1){  
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));  
        } else {  
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"EUC-KR"));  
        }  
      
        StringBuffer sb = new StringBuffer();  
        String thisLine = null;  
        while( (thisLine = in.readLine())!=null ){  
            sb.append(thisLine);  
            sb.append("\n");  
        }  
        in.close();       
          
        return sb.toString();  
    }  */


}
 

