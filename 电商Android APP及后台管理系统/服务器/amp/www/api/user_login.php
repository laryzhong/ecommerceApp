<?php
$user_email=$_POST['email'];
$user_password=$_POST['password'];

function __autoload($a){
        //将对应的类文件加载进来
        if(is_file("$a.class.php")){
            include_once "$a.class.php";
        }
        else{
            echo "没有文件";
        }
    }
    
$db =new DB();

$res=$db->getRow("e_user","UserEmail",$user_email);
$row = $res->fetch_assoc();
if ($row["password"]==$user_password) {
    $json_data=array(
    'code'=>0,
    'message'=>"OK",
    'data'=>array(
        'userId'=>$row["userID"],
        'username'=>$row["username"],
        'UserEmail'=>$row["UserEmail"],
        'UserPhone'=>$row["UserPhone"],
        'password'=>$row["password"],
        'UserSex'=>$row["UserSex"],
        'HeadPortrait'=>$row["HeadPortrait"],
        'UserBirth'=>$row["UserBirth"]
        ));
    $json_string =json_encode($json_data);
    // 写入文件
    file_put_contents('../data/user_profile.json', $json_string);


    $file_name="http://127.0.0.1/data/user_profile.json";
    $file=file_get_contents($file_name);
    echo $file;
}
else{    
    echo false;
}
?>