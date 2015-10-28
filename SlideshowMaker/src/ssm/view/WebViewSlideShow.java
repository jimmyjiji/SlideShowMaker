
package ssm.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import ssm.LanguagePropertyType;
import ssm.StartupConstants;
import ssm.error.ErrorHandler;
import ssm.model.Slide;

/**
 *
 * @author Jimmy
 */
public class WebViewSlideShow extends Stage{
    
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    private String title;
    private SlideShowMakerView ui;
    private ObservableList<Slide> slides;
    
    public WebViewSlideShow (String title, SlideShowMakerView ui, ObservableList<Slide> slides) {
        this.title= title;
        this.ui = ui;
        this.slides = slides;
    }
    
    
     public void start() throws FileNotFoundException, MalformedURLException  {
        setWidth(1200);
        setHeight(8000);
        Scene scene = new Scene(new Group());
        VBox root = new VBox();     

        
        File html = new File(StartupConstants.PATH_DATA + "sites/" +title+ "/index.html");
        File js = new File (StartupConstants.PATH_DATA + "sites/" +title+ "/JavaScript/javascript.js");
        try {
            jswriter(js);
            htmlwriter(html);
        } catch (IOException ex) {
        ErrorHandler eH = ui.getErrorHandler();
        eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
        } 
        
        String htmlz = html.toURI().toURL().toExternalForm();
        webEngine.load(htmlz);

        root.getChildren().addAll(browser);
        scene.setRoot(root);

        setScene(scene);
        this.show();

    }
    public void jswriter(File jsFile) throws IOException {
      FileWriter fileWriter = new FileWriter(jsFile);
      BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
      bufferWriter.write (""
              + "var images = [];");
      bufferWriter.newLine();
      for (int i = 0; i < slides.size(); i++) {
          bufferWriter.write("images.push(\"Images/image " + i+ ".jpg\");");
          bufferWriter.newLine();
      }
      bufferWriter.newLine();
      bufferWriter.write("var captions = [];");
      bufferWriter.newLine();
      for (int i = 0; i < slides.size(); i++) {
          bufferWriter.write("captions.push(\"" + slides.get(i).getCaption() + "\");");
          bufferWriter.newLine();
      }
      
      bufferWriter.newLine();
      
      bufferWriter.write(" var currentSlide = 0;\n" +
"      var isPlaying = true;\n" +
"      var slidePicture = document.getElementById(\"picture\");\n" +
"      var slideCaption = document.getElementById(\"caption\");\n" +
"      var pausePlay = document.getElementById(\"pause\");\n" +
"      var timer;\n" +
"      \n" +
"      \n" +
"    \n" +
"      \n" +
"      timer = setInterval(\"next()\", 4000);\n" +
"     \n" +
"     \n" +
"\n" +
"       function next() {\n" +
"        //slideCaption.innerHTML = \"i hate javascript\";\n" +
"        \n" +
"          if (currentSlide !== images.length) {\n" +
"              currentSlide++;\n" +
"              slidePicture.setAttribute(\"src\", images[currentSlide]);\n" +
"              slideCaption.innerHTML = captions[currentSlide];\n" +
"          } else if (currentSlide === images.length) {\n" +
"              currentSlide = 0;\n" +
"              slidePicture.setAttribute(\"src\", images[currentSlide]);\n" +
"              slideCaption.innerHTML = captions[currentSlide];\n" +
"          } else {\n" +
"              //do nothing\n" +
"            \n" +
"          }\n" +
"          \n" +
"      };\n" +
"\n" +
"      \n" +
"      function prev() {\n" +
"          if (currentSlide !== 0) {\n" +
"              currentSlide--;\n" +
"              slidePicture.setAttribute(\"src\", images[currentSlide]);\n" +
"              slideCaption.innerHTML = captions[currentSlide];\n" +
"          }\n" +
"\n" +
"    \n" +
"      }; \n" +
"\n" +
"\n" +
"      function pause() {\n" +
"        var pausePlay = document.getElementById(\"playPause\");\n" +
"        if (isPlaying) {\n" +
"          pausePlay.src = \"../../../images/icons/play.png\"; \n" +
"          isPlaying = false;\n" +
"          clearInterval(timer);\n" +
"        } else {\n" +
"          isPlaying = true;\n" +
"          pausePlay.setAttribute(\"src\", \"../../../images/icons/pause.png\");\n" +
"          timer = setInterval(\"next()\", 4000);\n" +
"        }\n" +
"      };");
      
      bufferWriter.close();
     
     
    }
     public void htmlwriter(File htmlFile) throws IOException {
      FileWriter fileWriter = new FileWriter(htmlFile);
      BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
      bufferWriter.write("<html>\n" +
"    <head>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/slideCSS.css\"/>\n" +
"    </head>\n" +
"    <body>");
      
      bufferWriter.write(" <h1 id = \"title\">" +title+ "</h1>");
      bufferWriter.write("<img id = \"picture\" src=\"Images/image 0.jpg\" alt=\" End of Slide Show \">\n" +
"        <p id = \"caption\">" + slides.get(0).getCaption() + "</p>");
      
      bufferWriter.write("<form>\n" +
"            <button type = \"button\" onclick= \"prev()\"> <img src=\"../../../images/icons/Previous.png\"/> </button>\n" +
"            <button type = \"button\" onclick = \"pause()\" > <img id = \"playPause\" src= \"../../../images/icons/pause.png\"/> </button>\n" +
"            <button type = \"button\" onclick= \"next()\"> <img src=\"../../../images/icons/Next.png\"/> </button>\n" +
"            \n" +
"        </form>\n" +
"        <script src=\"JavaScript/javascript.js\"></script>\n" +
"    </body>\n" +
"</html>");
       bufferWriter.close();
     }
    
}
