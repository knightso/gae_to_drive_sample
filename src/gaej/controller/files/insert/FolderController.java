package gaej.controller.files.insert;

import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import gaej.DriveService;

public class FolderController extends Controller {
    static final Logger logger = Logger.getLogger(FolderController.class. getName());

    @Override
    protected Navigation run() throws Exception {
        if(isPost()) {
            return doPost();
        }
        
        return null;
    }
    
    private Navigation doPost() throws Exception {
        String title = this.asString("title");
        
        File content = new File();
        content.setMimeType("application/vnd.google-apps.folder");      // ファイルではなく、フォルダを作ることを報せる。
        content.setTitle(title);
        
        Drive service = DriveService.getDriveService();
        File file;
        try {
            file = service.files().insert(content).execute();
        }catch(Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().println("<p>File(folder) ID: " + file.getId() + "</p>");
        response.flushBuffer();
        
        return null;
    }
}
