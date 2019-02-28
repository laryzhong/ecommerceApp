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

$res=$db->getAll("e_sort_lists");

$data_list;
while ($row = $res->fetch_assoc()) {
    $data_list[]=array(
    	"id"=>$row["sortId"],
    	"name"=>$row["sortName"]
    	);
}
    $json_data=array(
    'code'=>0,
    'message'=>"OK",
    'data'=>array(    	
    	"list"=>$data_list
        ));
    $json_string =json_encode($json_data);
    // 写入文件
    file_put_contents('../data/sort_list_data.json', $json_string);


    $file_name="http://127.0.0.1/data/sort_list_data.json";
    $file=file_get_contents($file_name);
    echo $file;

?>