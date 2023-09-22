package egov.com.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CustomCommonResolver extends CommonsMultipartResolver {


    final private long maxUploadSize = 100000000;
    //허용할 확장자
    final private String[] allowedFileExtensions = {"jpg", "jpeg", "png", "gif"};

    // 허용할 MIME 타입 목록 설정
    final private String[] allowedMIMETypes = {"image/jpeg", "image/png", "image/gif","image/vnd.microsoft.icon"};


    @Override
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {

        MultipartHttpServletRequest resolveMultipart = super.resolveMultipart(request);

        if(mimeTypeTest(resolveMultipart)){
            log.info("custom Resolver resolveMultipart do");
            MultipartFile file = resolveMultipart.getFile("file");
            long fileSize = file.getSize();
            String fileNm = file.getOriginalFilename();
            log.info("size : {}", fileSize);
            log.info("fileNm : {}", fileNm);

            if (maxUploadSize < fileSize){
                throw new MultipartException("e");
            }
        }else{
            throw new MultipartException("e");
        };

        return resolveMultipart;
    }

    private boolean mimeTypeTest(MultipartHttpServletRequest request) {
        MultipartFile file = request.getFile("file");

        // 파일명 가져오기
        String uploadedFileName = getUploadedFileName(file);
        assert uploadedFileName != null;

        // 클라이언트로부터 업로드된 파일 확장자가 허용된 확장자인지 확인
        if (isFileExtensionAllowed(uploadedFileName, allowedFileExtensions)) {
            // 확장자가 허용되면 MIME 타입 검사를 추가로 수행할 수 있음
            return getMIMETypeOfUploadedFile(file);
        }
        return false;
    }


    private String getUploadedFileName(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            return file.getOriginalFilename();
        }

        return null;
    }

    private boolean getMIMETypeOfUploadedFile(MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                Tika tika = new Tika();
                String uploadedFileMIME = tika.detect(file.getInputStream());

                // 허용된 MIME 타입인지 확인하여 불리언 값을 반환
                return isMimeTypeAllowed(uploadedFileMIME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean isMimeTypeAllowed(String mimeType) {
        // 클라이언트로부터 업로드된 파일의 MIME 타입이 허용된 MIME 타입인지 확인
        for (String allowedMIME : allowedMIMETypes) {
            if (allowedMIME.equals(mimeType)) {
                return true; // 허용된 MIME 타입이면 true 반환
            }
        }
        return false; // 허용되지 않는 MIME 타입이면 false 반환
    }


    private boolean isFileExtensionAllowed(String fileName, String[] allowedExtensions) {
        // 파일 확장자를 추출 (예: "example.jpg"에서 "jpg"를 추출)
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        // 클라이언트로부터 업로드된 파일 확장자가 허용된 확장자 목록에 포함되어 있는지 확인
        for (String extension : allowedExtensions) {
            if (extension.equals(fileExtension)) {
                return true; // 확장자가 허용되면 true 반환
            }
        }
        return false; // 확장자가 허용되지 않으면 false 반환
    }


}
