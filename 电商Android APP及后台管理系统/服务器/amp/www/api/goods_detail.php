<?php
$goods_id=$_GET['goods_id'];


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
$res_banners=$db->getRow("e_goods_banners","goodsId",$goods_id);
$res_banners_data;
while ($row_banner = $res_banners->fetch_assoc()) {
    $res_banners_data[]=$row_banner["goodsBannersImgUrl"];
}

$res_details_menu=$db->getAll("e_goods_details_menu");
$res_details_menu_data;
while ($row_details_menu = $res_details_menu->fetch_assoc()) {    
    $res_details=$db->getCustomRow("e_goods_details","goodsDetailsMenuId=".$row_details_menu["goodsDetailsMenuId"]." and goodsId=$goods_id");
    $res_details_data;
    while ($row_details = $res_details->fetch_assoc()) {
        $res_details_data[]=$row_details["goodsDetailsImgUrl"];
    }
    $res_details_menu_data[]=array(
        "name"=>$row_details_menu["goodsDetailsMenuName"],
        "pictures"=>$res_details_data
        );
    unset($res_details_data);
}

$res_goods=$db->getRow("e_goods","goodsId",$goods_id);
$row_goods = $res_goods->fetch_assoc();

$json_data=array(
'code'=>0,
'message'=>"OK",
'data'=>array(
    "content"=>"内容描述",
    "banners"=>$res_banners_data,
    "tabs"=>$res_details_menu_data,
    "specifications"=>"规格参数",
    "inventory"=>"商品中包含了那些东西的清单",
    "id"=>$row_goods["goodsId"],
    "name"=>$row_goods["text"],
    "description"=>$row_goods["desc"],
    "price"=>$row_goods["price"],
    "thumb"=>$row_goods["imageUrl"]
    )
);


    $json_string =json_encode($json_data);
    // 写入文件
    file_put_contents('../data/goods_detail_data_'.$goods_id.'.json', $json_string);

    $file_name="http://127.0.0.1/data/goods_detail_data_$goods_id.json";
    $file=file_get_contents($file_name);
    echo $file;
 ?>