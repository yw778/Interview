package multithread;

import java.util.*;
import java.util.concurrent.*;

public class WebCrawler {
	
	private static final int NTHREAD = 10;
	private static final int SLEEPTIME = 1000;
	private static final int CRAWLIMIT = 100000;
	private ExecutorService executorService;
	private List<Future<List<String>>> waitList;
	private Set<String> visit = new HashSet<>();
	
	
	public WebCrawler() {
		executorService = Executors.newFixedThreadPool(NTHREAD);
		waitList = new ArrayList<>();
	}
	
	public List<String> crawlUrl(String url) {
		submitUrl(url);
		while(visit.size() < CRAWLIMIT) {
			try {
				Thread.sleep(SLEEPTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Iterator<Future<List<String>>> it = waitList.iterator();
			List<String> nextUrls = new ArrayList<>();
			while(it.hasNext()) {
				Future<List<String>> future = it.next();
				if (future.isDone()) {
					try {
						List<String> crawledUrl = future.get();
						for (String nextUrl : crawledUrl) {
							if (visit.add(nextUrl)) {
								nextUrls.add(nextUrl);
							}
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					it.remove();
				}
			}
			// Avoid concurrent modification exception.
			// Since submitUrl will modify waitList.
			for (String nextUrl : nextUrls) {
				submitUrl(nextUrl);
			}
		}
		
		return new ArrayList<>(visit);
	}
	
	private void submitUrl(String url) {
		Crawler c = new Crawler(url);
		Future<List<String>> future = executorService.submit(c);
		waitList.add(future);
	}
}
