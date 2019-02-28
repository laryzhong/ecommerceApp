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
$goods_id=$_POST['goodsId'];
$user_id=$_POST['userId'];


$data="istake='yes'";
$where="goodsId=$goods_id and userID='$user_id'";
$res=$db->update("e_order_list",$data,$where);





$file_name="http://127.0.0.1/data/add_shop_data.json";
$file=file_get_contents($file_name);
echo $file;
?>