<?php


// $goods_id=2;


// function __autoload($a){
//         //将对应的类文件加载进来
//         if(is_file("$a.class.php")){
//             include_once "$a.class.php";
//         }
        
//         else{
//             echo "没有文件";
//         }
//     }
    
// $db =new DB();
// $res_banners=$db->getRow("e_goods_banners","goodsId",$goods_id);
// $res_banners_data;
// while ($row_banner = $res_banners->fetch_assoc()) {
//     $res_banners_data[]=$row_banner["goodsBannersImgUrl"];
// }

// $res_details_menu=$db->getRow("e_goods_details_menu","goodsId",$goods_id);
// $res_details_menu_data;
// while ($row_details_menu = $res_details_menu->fetch_assoc()) {
    
//     $res_details=$db->getRow("e_goods_details","goodsDetailsMenuId",$row_details_menu["goodsDetailsMenuId"]);
//     $res_details_data;
//     while ($row_details = $res_details->fetch_assoc()) {
//         $res_details_data[]=$row_details["goodsDetailsImgUrl"];
//     }
//     $res_details_menu_data[]=array(
//         "name"=>$row_details_menu["goodsDetailsMenuName"],
//         "pictures"=>$res_details_data
//         );
//     unset($res_details_data);
// }

// $res_goods=$db->getRow("e_goods","goodsId",$goods_id);
// $row_goods = $res_goods->fetch_assoc();

// $json_data=array(
// 'code'=>0,
// 'message'=>"OK",
// 'data'=>array(
//     "content"=>"内容描述",
//     "banners"=>$res_banners_data,
//     "tabs"=>$res_details_menu_data,
//     "specifications"=>"规格参数",
//     "inventory"=>"商品中包含了那些东西的清单",
//     "id"=>$row_goods["goodsId"],
//     "name"=>$row_goods["text"],
//     "description"=>$row_goods["desc"],
//     "price"=>$row_goods["price"],
//     "thumb"=>$row_goods["imageUrl"]
//     )
// );


//     $json_string =json_encode($json_data);
//     // 写入文件
//     file_put_contents('../data/goods_detail_data_'.$goods_id.'.json', $json_string);

//     $file_name="http://127.0.0.1/data/goods_detail_data_$goods_id.json";
//     $file=file_get_contents($file_name);
//     echo $file;






header("Content-type: text/html; charset=utf-8");
//将时区设置为上海时间
date_default_timezone_set('Asia/Shanghai');
$date=date("Y/m/d H:i:s");







//------------------------------------------------------------------------------------
// $sortId=0;


// function __autoload($a){
//         //将对应的类文件加载进来
//         if(is_file("$a.class.php")){
//             include_once "$a.class.php";
//         }
//         else{
//             echo "没有文件";
//         }
//     }
    
// $db =new DB();

// $res=$db->getRow("e_sort_tit","sortId",$sortId);

// $data_list;
// while ($row = $res->fetch_assoc()) {
    
//     $res_goods=$db->getRow("e_sort_content","titId",$row["titId"]);
//     $goods_data;
//     while ($row_goods = $res_goods->fetch_assoc()) {
//         $goods_data[]=array(
//             "goods_id"=>$row_goods["sortContentID"],
//             "goods_thumb"=>$row_goods["sortContentImgUrl"],
//             "goods_name"=>$row_goods["sortContentName"]
//             );
//     }
//     $data_list[]=array(
//         "id"=>$row["titId"],
//         "section"=>$row["section"],
//         "goods"=>$goods_data
//         );
//     unset($goods_data);
// }

// $json_data=array(
// 'code'=>0,
// 'message'=>"OK",
// 'data'=>$data_list
// );


//     $json_string =json_encode($json_data);
//     // 写入文件
//     file_put_contents('../data/test.json', $json_string);


//     $file_name="http://127.0.0.1/data/test.json";
//     $file=file_get_contents($file_name);
//     echo $file;


//------------------------------------------------------------------------------
    //使用DB类的对象来访问数据库
    //先加载类文件
    //include_once 'DB.class.php';
        //如果想使用其他类，又需要加载（所以使用魔术函数__autoload来实现类的自动加载）
    //显示的写出魔术函数__autoload
    //参数：需要加载的类的名字
//     function __autoload($a){
//         //将对应的类文件加载进来
//         if(is_file("$a.class.php")){
//             include_once "$a.class.php";
//         }
//         else{
//             echo "没有文件";
//         }
//     }
//     //实例化
//     $db =new DB();

// $res=$db->getAll("e_sort_lists");

// $data_list;
// while ($row = $res->fetch_assoc()) {
//     $data_list[]=$row["sortName"];
// }
//     $json_data=array(
//     'code'=>0,
//     'message'=>"OK",
//     'data'=>array(
//         "list"=>$data_list
//         ));
//     $json_string =json_encode($json_data);
//     // 写入文件
//     file_put_contents('../data/test.json', $json_string);


//     $file_name="http://127.0.0.1/data/test.json";
//     $file=file_get_contents($file_name);
//     echo $file;


//-----------------------------------------------------------------------------------------------
// $res=$db->getPage("e_goods","goodsId",1,10);
// $bannres=$db->getPage("e_index_banners","bannersId",1,3);

// $data_arr;
// $data_bannres;
// while ( $banrow = $bannres->fetch_assoc()) {
//     $data_bannres[]=$banrow["imageUrl"];
// }
// $data_arr[]=array(
//     "goodsId"=>0,
//     "spanSize"=>4,
//     "banners"=>$data_bannres
//     );
//  while ($row = $res->fetch_assoc()) {
//         $data_arr[]=array(
//                 "goodsId"=>$row["goodsId"],
//                 "text"=>$row["text"],
//                 "imageUrl"=>$row["imageUrl"],
//                 "spanSize"=>$row["spanSize"]);
//     }
//     $json_data=array(
//     'code'=>0,
//     'message'=>"OK",
//     "total"=> 100,
//     "page_size"=> 6,
//     'data'=>$data_arr
//     );


//     $json_string =json_encode($json_data);
//     // 写入文件
//     file_put_contents('../data/index_data.json', $json_string);


//     $file_name="http://127.0.0.1/data/index_data.json";
//     $file=file_get_contents($file_name);
//     echo $file;












    // $res=$db->getAll("e_user");

    // while ($row = $res->fetch_assoc()) {
    //     // var_dump($row);
    //     echo $row["userID"];
    // }
 
    

    // $data=array("008");
    // $term=array("userid");
    // $res=$db->insert("e_user",$term,$data);

    // $data="id='003'";
    // $res=$db->delete("order",$data);
    
    // $data="id='001',name='zxcv'";
    // $where="id='002'";
    // $res=$db->update("order",$data,$where);
    

    // if (is_null($res)) {
    //     echo "结果为空";
    // }
    // else{
    //     echo $res;
    // }
    

//------------------------------下面为参考代码-----------------------------------------------
    
// $mysql_conf = array(
//     'host'    => '127.0.0.1:3306', 
//     'db'      => 'users', 
//     'db_user' => 'root', 
//     'db_pwd'  => '123456', 
//     );

// $mysqli = @new mysqli($mysql_conf['host'], $mysql_conf['db_user'], $mysql_conf['db_pwd']);
// if ($mysqli->connect_errno) {
//     die("could not connect to the database:\n" . $mysqli->connect_error);//诊断连接错误
// }

// $mysqli->query("set names 'utf8';");//编码转化
// $select_db = $mysqli->select_db($mysql_conf['db']);
// if (!$select_db) {
//     die("could not connect to the db:\n" .  $mysqli->error);
// }


// $sql = "select password from users where username='2';";
// $res = $mysqli->query($sql);
// if (!$res) {
//     die("sql error:\n" . $mysqli->error);
// }
//  while ($row = $res->fetch_assoc()) {
//         var_dump($row);
//     }

// $res->free();
// $mysqli->close();    
// 
// 
// 



// 生成一个PHP数组
// $data = array();
// $data[0] = array('1','吴者然','onestopweb.cn');
// $data[1] = array('2','何开','iteye.com');
// // 把PHP数组转成JSON字符串
// $json_string = json_encode($data);



// {
//     "code": 0,
//     "message": "OK",
//     "data": {
//         "userId":1,
//         "name": "陆小果",
//         "avatar": "",
//         "gender": "男",
//         "address": "重庆"
//     }
// }

//------------------------------json文件生成-------------------------------------------
// $data = array('list'=>
//     array(0=>
//     array('images'=>array("https://a.com/1.jpg"),'id'=>1,'title'=>"标题1"),1=>
//     array('images'=>array("https://a.com/2.jpg"),'id'=>1,'title'=>"标题2"),2=>
//     array('images'=>array("https://a.com/3.jpg"),'id'=>1,'title'=>"标题3")));
// $data=array('code'=>0,
//     'message'=>"OK",
//     'data'=>array('userid'=>'1','name'=>'asdf'));
// $json_string =json_encode($data);
// // 写入文件
// file_put_contents('../data/test.json', $json_string);
    



//-------------------------------登录功能--------------------------------------------
// $user_email="123456@qq.com";
// $user_password='123456';
// $res=$db->getRow("e_user","UserEmail",$user_email);
// $row = $res->fetch_assoc();

// if ($row["password"]==$user_password) {
//     $json_data=array(
//     'code'=>0,
//     'message'=>"OK",
//     'data'=>array(
//         'userId'=>$row["userID"],
//         'username'=>$row["username"],
//         'UserEmail'=>$row["UserEmail"],
//         'UserPhone'=>$row["UserPhone"],
//         'password'=>$row["password"]
//         ));
//     $json_string =json_encode($json_data);
//     // 写入文件
//     file_put_contents('../data/user_profile.json', $json_string);


//     $file_name="http://127.0.0.1/data/user_profile.json";
//     $file=file_get_contents($file_name);
//     echo $file;
// }
// else{    
//     echo false;
// }

?>

