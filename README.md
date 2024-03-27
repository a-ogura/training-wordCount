# 〇training-head

# ソースコードの実行方法（ターミナルの実行コマンド）
## gitclone & コンパイル
```
git clone https://github.com/a-ogura/training-wordCount.git
javac -d training-wordCount\target\classes\jp\ne\networld\cmd\ 
training-wordCount\src\main\java\jp\ne\networld\cmd\WcCmd.java
```

## 実行
```
java -cp training-wordCount\target\classes\jp.ne.networld.cmd.WcCmd [option]… <filepath1>
```

## 実行例
```
java -cp training-wordCount\target\classes\jp.ne.networld.cmd.WcCmd -l training-wordCount\src\main\resources\sample.txt
```

### 必須引数
```
<filepath>は任意のファイルのパスを指定してください。
複数ファイルの指定は未対応です。
指定ファイルの文字コードはUTF-8のみとなります。
```
### オプション
```
-c|--bytes: バイト数を表示する
-m|--chars: 文字数を表示する
-l|--lines: 改行の数を表示する
-w|--words: 単語数を表示する
--help: 当コマンドの使い方を表示して終了する
--version:ファイル名非表示モード
```

# 〇工夫した点、アピールポイント等

- wcコマンドの使用経験が少ない、テスターの経験を活かすため、何度もwcコマンドを実行し挙動を理解して、細かい挙動も可能な限り再現しました。
- ファイルの存在チェックのエラーハンドリングを行うために、「java.io.File」クラスを利用しています。
-ファイルの読み込みでは「java.io.BufferedReader」を用いて、バッファリング読み込みを実装しています。
