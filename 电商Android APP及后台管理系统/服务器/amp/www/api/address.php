<?php

$user_Id=$_GET['userId'];

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

$res=$db->getRow("e_address","userID",$user_Id);

$users_data;
while ($row = $res->fetch_assoc()) {
	$users_data[]=array(
		"id"=>$row["addressId"],
		"default"=>true,
		"name"=>$row["consignee"],
		"phone"=>$row["phone"],
		"address"=>$row["addressName"]
		);
}

    $json_data=array(
    'code'=>0,
    'message'=>"OK",
    'data'=>$users_data);

    $json_string =json_encode($json_data);
    // 写入文件
    file_put_contents('../data/address.json', $json_string);


    $file_name="http://127.0.0.1/data/address.json";
    $file=file_get_contents($file_name);
    echo $file;


?>