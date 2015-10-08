package gaej.controller.blob;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import gaej.meta.UploadUrlDTOMeta;
import gaej.model.UploadUrlDTO;

public class UploadController extends Controller {
    public static final String PATH = "/blob/upload";
    static final Logger logger = Logger.getLogger(UploadController.class. getName());

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
    
    /**
     * BlobのアップロードURL生成
     * @return
     * @throws Exception
     */
    private Navigation doGet() throws Exception {
        String url = BlobstoreServiceFactory.getBlobstoreService().createUploadUrl(UploadController.PATH);
        UploadUrlDTO dto = new UploadUrlDTO();
        dto.setUploadUrl(url);
        
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(UploadUrlDTOMeta.get().modelToJson(dto));
        response.flushBuffer();
        
        return null;
    }

    /**
     * Blobstoreのコールバック。(ファイルアップロードのコールバック)
     * @return
     * @throws Exception
     */
    private Navigation doPost() throws Exception {
        BlobstoreService blobService = BlobstoreServiceFactory.getBlobstoreService();
        Map<String, List<BlobKey>> uploads = blobService.getUploads(request);
        Entry<String, List<BlobKey>> entry = uploads.entrySet().iterator().next();
        BlobKey blobKey = entry.getValue().get(0);
        BlobInfo blobInfo = new BlobInfoFactory().loadBlobInfo(blobKey);
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<p>BlobKey: " + blobInfo.getBlobKey().getKeyString() + "</p>");
        writer.println("<strong>Keyをメモして下さい。</strong>");
        response.flushBuffer();
        
        return null;
    }
}
