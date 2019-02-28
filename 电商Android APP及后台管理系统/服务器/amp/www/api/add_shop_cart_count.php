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

$goods_id=$_POST['goods_id'];
$user_id=$_POST['userId'];

$db =new DB();
$term=array("goodsId","userID");
$data=array($goods_id,$user_id);
$res_cart_content=$db->getCustomRow("e_shop_cart","goodsId=$goods_id and userID='$user_id'");
$row_data = $res_cart_content->fetch_assoc();
if (is_null($row_data)) {
	$db->insert("e_shop_cart",$term,$data);
}

$file_name="http://127.0.0.1/data/add_shop_data.json";
$file=file_get_contents($file_name);
echo $file;

$goods_count=$_POST['count']+1;
$data="goodsCount=$goods_count";
$where="goodsId=$goods_id and userID='$user_id'";
$res=$db->update("e_shop_cart",$data,$where);

?>