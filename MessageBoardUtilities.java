package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//共通処理のメソッドと設定値を一括で保持する
public class MessageBoardUtilities {
	
	//ログイン認証に使うユーザネームとパスワード
	static final String username = "poweruser";
	static final String password = "always123@";
	
	//ファイルアップロード用ディレクトリ
	static final String fileUploadPath = "/tmp/";
	
	//現在時刻を取得するメソッド
	public static String  currentDateTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String ctime = dtFormatter.format(currentTime);
		return ctime;
	}
}
