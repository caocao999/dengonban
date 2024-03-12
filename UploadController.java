package com.example.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;




//ファイルアップロードのためのコントローラー
@Controller
public class UploadController {

	//アップロード画面
	@GetMapping("/upload")
	public ModelAndView  init(ModelAndView mav) {
		mav.addObject("uploadForm", new UploadForm());
		String ctime = MessageBoardUtilities.currentDateTime();
		mav.addObject("ctime",ctime);
		mav.setViewName("upload");
		return mav;
	}
	
	//アップロード結果画面
	@PostMapping("/upload")
	public ModelAndView  upload(UploadForm uploadForm,ModelAndView mav) {
		MultipartFile multipartfile = uploadForm.getMultipartFile();
		uploadAction(multipartfile);
		String ctime = MessageBoardUtilities.currentDateTime();
		mav.addObject("ctime",ctime);
		mav.setViewName("upload_success");
		return mav;
	}
	
    //アップロード実行処理
    private void uploadAction(MultipartFile multipartFile) {
        //アップロードファイル名取得
        String fileName = multipartFile.getOriginalFilename();
        Path filePath = Paths.get(MessageBoardUtilities.fileUploadPath + fileName);
        try {
            byte[] bytes  = multipartFile.getBytes();
            int len = bytes.length;
            if(len > 1000000) {
            	throw new IOException();
            }
            OutputStream stream = Files.newOutputStream(filePath);
        	//書き込み処理
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
