import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;

public class Main
{
	private static String userid = null;
	private static String password = null;
	private static String urlStr = null;
	private static URL url = null;
	private static String urlParams = null;
	private static byte[] urlParamBytes = null;
	private static HttpURLConnection connection = null;


	public static void main(String[] args){
		try{
			urlStr = "http://library.iiitd.edu.in/cgi-bin/koha/opac-user.pl";
			url = new URL(urlStr);
			userid = URLEncoder.encode("katari15045", "UTF-8");
			password = URLEncoder.encode("dummy", "UTF-8");
			urlParams = "userid=" + userid + "&password=" + password;
			urlParamBytes = urlParams.getBytes("UTF-8");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setRequestProperty("Content-Length", String.valueOf(urlParamBytes.length));
	        connection.setDoOutput(true);
	        connection.getOutputStream().write(urlParamBytes);

	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

	        for (int c; (c = in.read()) >= 0;)
	            System.out.print((char)c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}