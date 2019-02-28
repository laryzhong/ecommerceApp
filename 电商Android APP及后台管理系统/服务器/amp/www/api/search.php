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


$key=$_GET['key'];
$db =new DB();

$res=$db->getCustomRow("e_goods","text LIKE '%$key%'");

$data_arr;
 while ($row = $res->fetch_assoc()) {
        $data_arr[]=array(
        	    "goodsId"=>$row["goodsId"],
        		"text"=>$row["text"],
        		"imageUrl"=>$row["imageUrl"],
        		"spanSize"=>2);
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