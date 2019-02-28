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
$user_id=$_POST['userId'];
$goods_id=$_POST['goodsId'];
$count=$_POST['count'];

header("Content-type: text/html; charset=utf-8");
//将时区设置为上海时间
date_default_timezone_set('Asia/Shanghai');
$date_time=date("Y/m/d H:i:s");

$term=array("goodsId","userID","count","orderTime");
$data=array($goods_id,$user_id,$count,$date_time);
$db->insert("e_order_list",$term,$data);

$res_oid=$db->getCustomRow("e_order_list","goodsId=$goods_id and userID='$user_id' and orderTime='$date_time'");
$row_order = $res_oid->fetch_assoc();

$json_data=array(
'code'=>0,
'message'=>"OK",
'result'=>$row_order['OID']
);

$json_string =json_encode($json_data);
// 写入文件
file_put_contents('../data/add_result.json',$json_string);

$file_name="http://127.0.0.1/data/add_result.json";
$file=file_get_contents($file_name);
echo $file;
?>