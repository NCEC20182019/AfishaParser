package parsers.updates_for_events;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

public class TweetsSearcher {
    private static final String TWITTER_SEARCH_RESULT_FILENAME = "TwitterSearchResult.html";
    private static final String JS_SCROLL_SCRIPT = "window.scrollTo(0, document.body.scrollHeight)";
    private static final Logger logger = LoggerFactory.getLogger(TweetsSearcher.class);
    private static final String TWITTER_SEARCH_URL = "https://twitter.com/search?q=";
    private TweetsSearcher(){}

    public static void search(String hashtag) {
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
//class="js-tweet-text-container"
//class="content"

        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        //webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //webClient.getCurrentWindow().setInnerHeight(60000);
        try {
            HtmlPage page = webClient.getPage(TWITTER_SEARCH_URL + hashtag);

            synchronized (page) {
                page.executeJavaScript(JS_SCROLL_SCRIPT);
                page.wait(250);
            }
            try (FileWriter writer = new FileWriter(TWITTER_SEARCH_RESULT_FILENAME, false)) {
                writer.write(page.asXml());

            } catch (IOException e) {
                logger.error("something wrong while writting " + TWITTER_SEARCH_RESULT_FILENAME, e);
                throw new RuntimeException();
            }
      		
            logger.info("End of browsing." + TWITTER_SEARCH_RESULT_FILENAME + "ready for parsing");
        } catch (Exception e) {
            logger.error("something wrong while browsing " + TWITTER_SEARCH_URL + hashtag, e);
            throw new RuntimeException();
        }
        webClient.close();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        TweetsSearcher.search("%23Voronezh");
        /*

        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
//class="js-tweet-text-container"
//class="content"

        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        //webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        //webClient.getCurrentWindow().setInnerHeight(60000);
        HtmlPage page = webClient.getPage("https://twitter.com/search?q=%23Воронеж");



        synchronized (page) {
            page.executeJavaScript(JS_SCROLL_SCRIPT);
            page.wait(250);
        }


        try(FileWriter writer = new FileWriter(TWITTER_SEARCH_RESULT_FILENAME, false)) {
            writer.write(page.asXml());
        }
        catch (IOException e){
            e.printStackTrace();
        }

        // Закрываем headless-браузер, освобождаем память
        webClient.close();
*/
/*
//Пробуем selenium
        WebDriver driver = new FirefoxDriver();
        driver.get("https://twitter.com/search?f=tweets&vertical=default&q=%23voronezh&src=typd");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,1000)", "");
        new WebDriverWait(driver, 10);
        System.out.println(driver.toString());
        driver.quit();
        */
    }
}
