package gaej;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential.Builder;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;

public class DriveService {
    static final Logger logger = Logger.getLogger(DriveService.class.getName());
    
    public static AppIdentityCredential authorize() throws IOException {
        AppIdentityService service = AppIdentityServiceFactory.getAppIdentityService();
        Builder builder = new AppIdentityCredential.Builder(Arrays.asList(DriveScopes.DRIVE_FILE));
        builder.setAppIdentityService(service);
        return builder.build();
    }
    
    public static Drive getDriveService() throws IOException {
        HttpTransport httpTransport = new UrlFetchTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        AppIdentityCredential credential = authorize();
        return new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName("Drive API Test Application").build();
    }
}
