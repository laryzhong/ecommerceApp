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

$res=$db->getRow("e_order_list","userID",$user_Id);

$goods_data;
while ($row = $res->fetch_assoc()) {

    $res_evaluate=$db->getRow("e_order_evaluate","OID",$row["OID"]);
    $row_evaluate=$res_evaluate->fetch_assoc();
    if (is_null($row_evaluate)) {  
        $res_goods=$db->getRow("e_goods","goodsId",$row["goodsId"]);
        $row_goods = $res_goods->fetch_assoc();
        $goods_data[]=array(
            "title"=>$row_goods["text"],
            "id"=>$row_goods["goodsId"],
            "price"=>$row_goods["price"],
            "thumb"=>$row_goods["imageUrl"],
            "time"=>$row["orderTime"]
        );
    }
}

    $json_data=array(
    'code'=>0,
    'message'=>"OK",
    'data'=>$goods_data);

    $json_string =json_encode($json_data);
    // 写入文件
    file_put_contents('../data/order_evaluate.json', $json_string);


    $file_name="http://127.0.0.1/data/order_evaluate.json";
    $file=file_get_contents($file_name);
    echo $file;

    ?>