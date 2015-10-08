package gaej.controller.permissions;

import java.io.IOException;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;

import gaej.DriveService;

public class InsertController extends Controller {
    static final Logger logger = Logger.getLogger(InsertController.class. getName());

    @Override
    protected Navigation run() throws Exception {
        if(isGet()) {
            return doGet();
        }
        if(isPost()) {
            return doPost();
        }
        
        return null;
    }

    private Navigation doGet() {
        return null;
    }
    
    private Navigation doPost() throws IOException {
        Drive driveService = DriveService.getDriveService();
        
        String fileId = this.asString("fileId");
        String domain = this.asString("domain");
        String role = this.asString("role");
        
        Permission permission = new Permission();
        permission.setValue(domain);
        permission.setRole(role);
        permission.setType("domain");
        
        Permission result = null;
        try {
            result = driveService.permissions().insert(fileId, permission).execute();
        } catch(IOException e) {
            logger.warning(e.getMessage());
            return null;
        }
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.getWriter().println("<p>Permission ID: " + result.getId() + "</p>");
        response.flushBuffer();
        
        return null;
    }
}
