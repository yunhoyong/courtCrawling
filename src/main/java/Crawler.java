import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler  {
	public static void main(String args[]){
		for(int i=2 ; i < 168 ; i++) {
			 try {
		            // 1. 수집 대상 URL
		            String URL = "http://kato.or.kr/court/court2.html?page="+ Integer.toString(i)+"&mode=court_search&colume=addr&word=&in_mode=";
		            
		            // 2. Connection 생성
		            Connection conn = Jsoup.connect(URL);
		 
		            // 3. HTML 파싱.
		            Document html = conn.get(); // conn.post();
		            
		            // 4. HTML 출력
		            log( html.toString(),"crawingLog"+ Integer.toString(i) ); 
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
       
    }
	
	public static void log(String log,String m_FileName) {
        int i                 = 0;
        String stPath         = "";
        String stFileName     = "";   
        FileWriter objfile = null;
        String m_PathName = "F://";  //로그 파일이 저장 될 경로 (요것만 바꿔주면 된답니다.)

        SimpleDateFormat formatter1 = new SimpleDateFormat ("yyyyMMdd");
        SimpleDateFormat formatter2 = new SimpleDateFormat ("HH:mm:ss");
       
        String stDate = formatter1.format(new Date());
        String stTime = formatter2.format(new Date());
        StringBuffer bufLogPath  = new StringBuffer();      
                     bufLogPath.append(m_PathName);
                     bufLogPath.append(m_FileName);
                     bufLogPath.append("_");
                     bufLogPath.append(stDate);
                     bufLogPath.append(".log") ;
        StringBuffer bufLogMsg = new StringBuffer();
            bufLogMsg.append("[");
            bufLogMsg.append(stTime);
            bufLogMsg.append("]\r\n");            
            bufLogMsg.append(log);
                    
        try{
                objfile = new FileWriter(bufLogPath.toString(), true);
                objfile.write(bufLogMsg.toString());
                objfile.write("\r\n");
        }catch(IOException e){
           e.printStackTrace();
        }
        finally
        {
            try{
             objfile.close();
            }catch(Exception e1){e1.printStackTrace();}
        }
    }
}
