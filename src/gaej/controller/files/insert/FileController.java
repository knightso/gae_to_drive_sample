package gaej.controller.files.insert;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import gaej.DriveService;

public class FileController extends Controller {
    static final Logger logger = Logger.getLogger(FileController.class. getName());
    
    @Override
    protected Navigation run() throws Exception {
        if(isPost()) {
            return doPost();
        }
        
        return null;
    }
    
    /**
     * Blobstoreのファイルを、Google Driveにアップロードする
     * @return
     * @throws Exception
     */
    private Navigation doPost() throws Exception {
        String blobKeyString = this.asString("blobstoreKey");
        String parentId = this.asString("parentId");
        BlobKey blobKey = new BlobKey(blobKeyString);
        BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobKey);
        
        File content = new File();
        content.setTitle(blobInfo.getFilename());
        
        if (parentId.length() > 0) {
            content.setParents(Arrays.asList(new ParentReference().setId(parentId)));
        }
        
        BlobstoreService blobService = BlobstoreServiceFactory.getBlobstoreService();
        AbstractInputStreamContent byteArrayContent = new ByteArrayContent(blobInfo.getContentType(), blobService.fetchData(blobKey, 0L, blobInfo.getSize()));
        
        Drive driveService = DriveService.getDriveService();
        File uploaded = null;
        try {
            uploaded = (File)driveService.files().insert(content, byteArrayContent).execute();
        } catch (Exception e) {
            logger.warning(e.getMessage());
            return null;
        }
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<p>File Title: " + uploaded.getTitle() + "</p>");
        writer.println("<p>File ID: " + uploaded.getId() + "</p>");
        writer.println("<strong>IDをメモしておいて下さい。</strong>");
        response.flushBuffer();
        
        return null;
    }
}
