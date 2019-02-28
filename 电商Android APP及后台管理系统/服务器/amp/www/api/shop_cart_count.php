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
$goods_count;
$add_sub=$_POST["addSub"];

$res=$db->getCustomRow("e_shop_cart","goodsId=$goods_id and userID='$user_id'");
$row_data = $res->fetch_assoc();
$goods_count=$row_data["goodsCount"];

if ($add_sub==0) {
	$goods_count=$goods_count-1;
}
elseif ($add_sub==1) {
	$goods_count=$goods_count+1;
}

$data="goodsCount=$goods_count";
$where="goodsId=$goods_id and userID='$user_id'";
$res=$db->update("e_shop_cart",$data,$where);


$file_name="http://127.0.0.1/data/add_shop_data.json";
$file=file_get_contents($file_name);
echo $file;
?>