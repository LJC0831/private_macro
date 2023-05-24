package study;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class Test {
    public static void main(String[] args) throws URISyntaxException {
        /*
            Document 클래스 : 연결해서 얻어온 HTML 전체 문서
            Element 클래스  : Documnet의 HTML 요소
            Elements 클래스 : Element가 모인 자료형
        */       
        String url = "https://cafe.naver.com";
        String subUrl = "/cafe.naver.com/ArticleList.nhn?search.clubid=20486145&search.menuid=128&search.boardtype=L"; //충청도 iframe 주소
        String subUrl2 = "/cafe.naver.com/ArticleList.nhn?search.clubid=20486145&search.menuid=127&search.boardtype=L"; //서울 iframe 주소
        String subUrl3 = "/cafe.naver.com/ArticleList.nhn?search.clubid=20486145&search.menuid=129&search.boardtype=L"; // 강원 
        String final_url = url + subUrl;
        String final_url2 = url + subUrl2;
        String final_url3 = url + subUrl3;
        String selector = ".inner_number";
        
        //inner_number
        Document doc = null;    
       //  충청게시판 현 max값
        try {
            doc = Jsoup.connect(final_url).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Elements eleNum = doc.select(selector);
        int maxNumber = Integer.parseInt(eleNum.get(0).text().split(" ")[0]); //현재 맥스글번호를 입력(충청도)
        
        // 서울게시판 현 max값
        try {
            doc = Jsoup.connect(final_url2).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Elements eleNum2 = doc.select(selector); // -- 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
        
        int maxNumber2 = Integer.parseInt(eleNum2.get(0).text().split(" ")[0]);
        
        // 강원게시판 현 max값
        try {
            doc = Jsoup.connect(final_url3).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Elements eleNum3 = doc.select(selector); // -- 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
        int maxNumber3 = Integer.parseInt(eleNum3.get(0).text().split(" ")[0]);
        
        // 루프실행
        while (true) {
        	// 충청도 처리
        	try {
                doc = Jsoup.connect(final_url).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
            Elements titles = doc.select(selector); // -- 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
            
            int boardNumber = Integer.parseInt(titles.get(0).text().split(" ")[0]);
            
            if (boardNumber > maxNumber) {
            	System.out.println("충청)새글생성됨");
            	try {
        			Desktop.getDesktop().browse(new URI("https://cafe.naver.com/chocammall?iframe_url=/ArticleList.nhn%3Fsearch.clubid=20486145%26search.menuid=128%26search.boardtype=L"));
        			break;
        		} catch (IOException e) {
        			e.printStackTrace();
        		} 
            } else {
            	System.out.println("충청)아직 새글 생성 X");
            }
            
            
            // 서울처리 
            
            try {
                doc = Jsoup.connect(final_url2).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.
                //System.out.println(doc);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
            Elements titles2 = doc.select(selector); // -- 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
//            System.out.println("111111111111111" + titles);
            
            int boardNumber2 = Integer.parseInt(titles2.get(0).text().split(" ")[0]);
//            System.out.println("22222222222222" + boardNumber);
            
            if (boardNumber2 > maxNumber2) {
            	System.out.println("서울)새글생성됨");
            	try {
        			Desktop.getDesktop().browse(new URI("https://cafe.naver.com/chocammall?iframe_url=/ArticleList.nhn%3Fsearch.clubid=20486145%26search.menuid=127%26search.boardtype=L"));
        			break;
        		} catch (IOException e) {
        			e.printStackTrace();
        		} 
            } else {
            	System.out.println("서울)아직 새글 생성 X");
            }
            
            // 강원처리 
            
            try {
                doc = Jsoup.connect(final_url3).get(); // -- 1. get방식의 URL에 연결해서 가져온 값을 doc에 담는다.
                //System.out.println(doc);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
            Elements titles3 = doc.select(selector); // -- 2. doc에서 selector의 내용을 가져와 Elemntes 클래스에 담는다.
//            System.out.println("111111111111111" + titles);
            
            int boardNumber3 = Integer.parseInt(titles3.get(0).text().split(" ")[0]);
//            System.out.println("22222222222222" + boardNumber);
            
            if (boardNumber3 > maxNumber3) {
            	System.out.println("강원도)새글생성됨");
            	try {
        			Desktop.getDesktop().browse(new URI("https://cafe.naver.com/chocammall?iframe_url=/ArticleList.nhn%3Fsearch.clubid=20486145%26search.menuid=129%26search.boardtype=L"));
        			break;
        		} catch (IOException e) {
        			e.printStackTrace();
        		} 
            } else {
            	System.out.println("강원도)아직 새글 생성 X");
            }
        }
    }
}
  
