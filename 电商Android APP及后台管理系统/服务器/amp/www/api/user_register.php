<?php
$user_Id=uniqid();
$user_name=$_POST['name'];
$user_email=$_POST['email'];
$user_phone=$_POST['phone'];
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
$json_data;

$term=array("userId","username","UserEmail","UserPhone","password");
$data=array($user_Id,$user_name,$user_email,$user_phone,$user_password);
$res=$db->insert("e_user",$term,$data);
if (!$res) {
    echo "插入失败";
}
else{
	$json_data=array(
	'code'=>0,
    'message'=>"OK",
    'data'=>array(
    	'userId'=>$user_Id,
    	'username'=>$user_name,
    	'UserEmail'=>$user_email,
    	'UserPhone'=>$user_phone,
    	'password'=>$user_password
    	));
	$json_string =json_encode($json_data);
	// 写入文件
	file_put_contents('../data/user_profile.json', $json_string);


	$file_name="http://127.0.0.1/data/user_profile.json";
	$file=file_get_contents($file_name);
	echo $file;
}

?>