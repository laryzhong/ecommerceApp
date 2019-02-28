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
    
$db =new DB();

$bannres=$db->getPage("e_index_banners","bannersId",1,6);
$res=$db->getPage("e_goods","goodsId",1,50);

$data_bannres;
$data_arr;
while ( $banrow = $bannres->fetch_assoc()) {
	$data_bannres[]=$banrow["imageUrl"];
}
$data_arr[]=array(
	"goodsId"=>0,
	"spanSize"=>4,
	"banners"=>$data_bannres
	);
 while ($row = $res->fetch_assoc()) {
        $data_arr[]=array(
        	    "goodsId"=>$row["goodsId"],
        		"text"=>$row["text"],
        		"imageUrl"=>$row["imageUrl"],
        		"spanSize"=>$row["spanSize"]);
    }
    $json_data=array(
    'code'=>0,
    'message'=>"OK",
    "total"=> 100,
	"page_size"=> 6,
    'data'=>$data_arr
    );


    $json_string =json_encode($json_data);
    // 写入文件
    file_put_contents('../data/index_data.json', $json_string);


    $file_name="http://127.0.0.1/data/index_data.json";
    $file=file_get_contents($file_name);
    echo $file;
?>