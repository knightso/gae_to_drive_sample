package gaej.controller.files;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import gaej.DriveService;

public class ListController extends Controller {
    static final Logger logger = Logger.getLogger(ListController.class.getName());

    @Override
    protected Navigation run() throws Exception {
        if(isGet()) {
            return doGet();
        }
        
        return null;
    }
    
    private Navigation doGet() throws Exception {
        Drive service = DriveService.getDriveService();
        List<File> list = new ArrayList<File>();
        com.google.api.services.drive.Drive.Files.List request = service.files().list();
        
        do {
            try {
                FileList files = request.execute();
                list.addAll(files.getItems());
                request.setPageToken(files.getNextPageToken());
            }catch(IOException e) {
                logger.info(e.getMessage());
                request.setPageToken(null);
                return null;
            }
        }while(request.getPageToken() != null && request.getPageToken().length() > 0);
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>Success</h1>");
        writer.write("<ul>");
        for(File f:list) {
            writer.write("<li><strong>ID:</strong> " + f.getId() + " <strong>title:</strong> " + f.getTitle() + "</li>");
        }
        writer.write("</ul>");
        response.flushBuffer();
        
        return null;
    }
    
}
