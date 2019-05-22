## Java課題
- JSP説明
    - add_todo.jsp（todo_textの追加画面）
    - back.jsp（todo_textの削除失敗画面）
    - change_password.jsp（user_idのパスワード変更画面）
    - delete_Todo.jsp（todo_textの削除画面）
    - delete_User.jsp（user_idの削除画面）
    - edit_Todo.jsp（todo_textの編集画面）
    - index.jsp（ログイン画面）
    - member.jsp（一覧画面）
    - new_ID.jsp（新規登録画面）
    - Result.jsp（エラー画面）
- Servlet説明
    - EscapeCharacter.java（記号変換）
    - ModeServlet.java（MODE選択）
    - TodoBeans.java（SQL実行）
    - TodoServlet.java（SQL指令生成）
    - UserBeans.java（SQL実行）
    - UserServlet.java（SQL指令生成）
    
## ログイン画面機能
二つテキストがあります。入力制限は20文字以内、英数と指定記号を使えます。<br>
新規登録ボタンとログインボタンがあります。<br>
新規登録ボタンを押すと、新規画面へ遷移します。<br>
ログインボタンを押すと、ServletでIDとPASSWORDを確認します。<br>
IDとPASSWORDは正しいなら、一覧画面へ進みます。<br>
IDとPASSWORDは違うなら、エラー画面が表します。<br>

## 新規画面機能
三つテキストがあります。入力制限は20文字以内、英数と指定記号を使えます。<br>
PASSWORDは二回入力するのは必要です。<br>
存在したIDを入力したら、エラー画面へ遷移します。<br>
二つPASSWORDが違う場合はアカウントの登録ができません。<br>

## 一覧画面機能
使用者のIDとデータが表します。<br>
使用者使える機能はアカウントの削除、PASSWORDの変更、Todo一覧の操作です。<br>
アカウントを削除したい時、削除確認画面へ遷移します。<br>
確認すると、アカウントを削除します。<br>
PASSWORDの変更はPASSWORDだけ変更できます。<br>
Todo一覧はテキストを押すと、編集画面へ遷移します。<br>
checkboxを選ぶと、削除ができます。<br>
もし何も選ばない場合は削除失敗画面へ遷移します。<br>
追加ボタンを押すと、追加画面へ遷移します。<br>



    
