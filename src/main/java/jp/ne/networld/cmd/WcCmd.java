package jp.ne.networld.cmd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WcCmd {

	public static void main(String[] args) {

		// 	ファイル指定なしはエラー	
		if (args.length == 0) {
			System.err.println("Usage: java WC [OPTIONS] FILE");
			System.err.println("詳しくは 'wc --help' を実行して下さい。");
			return;
		}

		// オプションを格納するリスト
		List<String> optionList = new ArrayList<>();
		// ファイル名を格納するリスト
		List<String> fileList = new ArrayList<>();

		// 引数をオプションとファイル名に分割
		for (String arg : args) {
			if (arg.startsWith("-")) { // オプションの場合
				optionList.add(arg);
			} else { // ファイル名の場合
				fileList.add(arg);
			}
		}
		
		// ファイル数確認
		if(fileList.size() <= 2) {
			System.err.println("指定できるファイルは1つです。");
		}

		// optionのチェック 
		boolean countBytes = false;
		boolean countChars = false;
		boolean countLines = false;
		boolean countWords = false;

		for (String option : optionList) {
			switch (option) {
			case "-c":
			case "--bytes":
				countBytes = true;
				break;
			case "-m":
			case "--chars":
				countChars = true;
				break;
			case "-l":
			case "--lines":
				countLines = true;
				break;
			case "-w":
			case "--words":
				countWords = true;
				break;
			case "--help":
				System.out.println("使用法: wc [OPTION]... [FILE]...\n");
				System.out.println("ファイルの指定がない場合はエラー(Usage: java WC [OPTIONS] FILE)を応答します。");
				System.out.println("ファイルの文字コードはUTF_8にしてください。\n");
				System.out.println("下記のオプションを使って、何を数えて表示するかを選択できます。");
				System.out.println("\t-c,\t--bytes\t\t\tバイト数を表示する");
				System.out.println("\t-m,\t--chars\t\t\t文字数を表示する");
				System.out.println("\t-l,\t--lines\t\t\t改行の数を表示する");
				System.out.println("\t-w,\t--words\t\t\t単語数を表示する");
				System.out.println("\t\t--help\t\t\tこの使い方を表示して終了する");
				System.out.println("\t\t--version\t\tバージョン情報を表示して終了する\n");
				System.out.println("下記は未実装です。");
				System.out.println(
						"\t\t--files0-from=F\t\t入力として NULL 文字で区切られたファイル F を使用する。\n\t\t\t\t\tF が - の場合は名前を標準入力から読み込む");
				System.out.println("\t-L,\t--max-line-length\t最も長い行の長さを表示する");
				return;
			case "--version":
				System.out.println("WC Version 1.0");
				return;
			default:
				System.err.println(String.format("wc: 無効なオプション %s: ", option));
				System.err.println("詳しくは 'wc --help' を実行して下さい。");
				return;
			}
		}

		// wcのカウント処理   
		try {
			int byteCount = 0;
			int charCount = 0;
			int lineCount = 0;
			int wordCount = 0;

			// ファイルの有無をチェック
			File file = new File(fileList.get(0));
			if (!file.isFile() || !file.exists()) {
				System.err.println("ファイルが存在しないかディレクトリです。");
				return;
			}

			// BufferedReaderを使用してファイルを読み込む
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line;
			while ((line = br.readLine()) != null) {
				byteCount += line.getBytes(StandardCharsets.UTF_8).length;
				charCount += line.length();
				lineCount++;
				String[] words = line.split("\\s+");
				wordCount += words.length;
			}

			br.close(); // ファイルを閉じる

			// 結果出力
			StringBuilder sb = new StringBuilder();

			if (countLines)
				sb.append(String.format("%d ", lineCount));
			if (countWords)
				sb.append(String.format("%d ", wordCount));
			if (countChars)
				sb.append(String.format("%d ", charCount));
			if (countBytes)
				sb.append(String.format("%d ", byteCount));
			if (optionList.size() == 0) {
				sb.append(String.format("%d %d %d ", lineCount, wordCount, byteCount));
			}
			sb.append(fileList.get(0));
			System.out.println(sb.toString());

		} catch (IOException e) {
			System.out.println("ファイルの読み込みエラー: ");
			e.printStackTrace();
		}
	}

}
