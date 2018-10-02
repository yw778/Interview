package multithread;

import java.util.List;
import java.util.concurrent.Callable;

public class Crawler implements Callable<List<String>>{
	private String url;
	public Crawler(String url) {
		this.url = url;
	}
	@Override
	public List<String> call() throws Exception {
		// getChildUrl should be an API provided by the interviewer.
		List<String> urls = getChildUrl(url);
		return urls ;
	}
	
}
