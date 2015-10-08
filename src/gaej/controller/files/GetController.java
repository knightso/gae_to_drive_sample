package gaej.controller.files;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import gaej.DriveService;

public class GetController extends Controller {
    static final Logger logger = Logger.getLogger(GetController.class. getName());

    @Override
    protected Navigation run() throws Exception {
        if(isGet()) {
            return doGet();
        }
        
        return null;
    }

    private Navigation doGet() throws Exception {
        String fileId = this.asString("fileId");
        
        Drive driveService = DriveService.getDriveService();
        File file = null;
        try {
            file = driveService.files().get(fileId).execute();
        } catch (IOException e) {
            logger.warning(e.getMessage());
            return null;
        }
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<p>File Title: " + file.getTitle() + "</p>");
        writer.println("<p>File ID: " + file.getId() + "</p>");
        writer.println("<br>");
        writer.println("<p>File Thumbnail</p><div><img src='" + file.getThumbnailLink() + "'></div>");
        writer.println("<br>");
        writer.println("<p><a href='" + file.getAlternateLink() + "'>プレビュー表示(画面の情報が見られなくなるので、別のタブで開くことを推奨)</a></p>");
        response.flushBuffer();
        
        return null;
    }
}
