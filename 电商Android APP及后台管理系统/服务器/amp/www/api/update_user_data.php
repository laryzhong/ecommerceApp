<?php

function __autoload($a){
        //将对应的类文件加载进来
        if(is_file("$a.class.php")){
            include_once "$a.class.php";
        }
        
        else{
            echo "没有文件";
        }
    }

$user_id=$_POST["userId"];
$key=$_POST['key'];
$user_head_path=null;
$user_name=null;
$user_sex=null;
$user_birth=null;

if ($key==1) {
	$user_head_path=$_POST['avatar'];
}

if ($key==2) {
	$user_name=$_POST['userName'];
}

if ($key==3) {
	$user_sex=$_POST['userSex'];
}

if ($key==4) {
	$user_birth=$_POST['userBirth'];
}

$db =new DB();

$where="userID='$user_id'";
$data;
if (!is_null($user_head_path)) {
	$data="HeadPortrait='$user_head_path'";
}

if (!is_null($user_name)) {
	$data="username='$user_name'";
}

if (!is_null($user_sex)) {
	$data="UserSex='$user_sex'";
}

if (!is_null($user_birth)) {
	$data="UserBirth='$user_birth'";
}

$res=$db->update("e_user",$data,$where);

$file_name="http://127.0.0.1/data/add_shop_data.json";
$file=file_get_contents($file_name);
echo $file;

?>