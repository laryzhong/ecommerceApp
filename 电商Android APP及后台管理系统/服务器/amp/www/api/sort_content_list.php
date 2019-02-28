<?php
$sortId=$_GET['contentId'];


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

$res=$db->getRow("e_sort_tit","sortId",$sortId);

$data_list;
while ($row = $res->fetch_assoc()) {
    
    $res_goods=$db->getRow("e_sort_content","titId",$row["titId"]);
    $goods_data;
    while ($row_goods = $res_goods->fetch_assoc()) {
        $goods_data[]=array(
            "goods_id"=>$row_goods["sortContentID"],
            "goods_thumb"=>$row_goods["sortContentImgUrl"],
            "goods_name"=>$row_goods["sortContentName"]
            );
    }
    $data_list[]=array(
        "id"=>$row["titId"],
        "section"=>$row["section"],
        "goods"=>$goods_data
        );
    unset($goods_data);
}

$json_data=array(
'code'=>0,
'message'=>"OK",
'data'=>$data_list
);


    $json_string =json_encode($json_data);
    // 写入文件
    file_put_contents('../data/sort_content_data.json', $json_string);


    $file_name="http://127.0.0.1/data/sort_content_data.json";
    $file=file_get_contents($file_name);
    echo $file;
?>