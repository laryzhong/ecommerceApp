<?php
$user_Id=$_POST['userId'];
$user_name=$_POST['name'];
$user_address=$_POST['address'];
$user_phone=$_POST['phone'];

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

$term=array("userID","addressName","consignee","phone");
$data=array($user_Id,$user_address,$user_name,$user_phone);
$res=$db->insert("e_address",$term,$data);
if (!$res) {
    echo "插入失败";
}
else{
	$file_name="http://127.0.0.1/data/add_shop_data.json";
    $file=file_get_contents($file_name);
    echo $file;
}

?>