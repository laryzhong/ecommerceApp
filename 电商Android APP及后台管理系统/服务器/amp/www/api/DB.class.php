<?php

   // 数据库连接类
class DB{
  private $mysql_conf;
  private $mysqli;
  private $select_db;

  //构造方法
  public function __construct(){
    $this->mysql_conf= array(
    'host'    => '127.0.0.1:3306', 
    'db'      => 'E_Commerce_DB', 
    'db_user' => 'root', 
    'db_pwd'  => '123456', 
    );

    //连接数据库
    $this->mysqli = @new mysqli($this->mysql_conf['host'], $this->mysql_conf['db_user'], $this->mysql_conf['db_pwd']);
    if ($this->mysqli->connect_errno) {
        die("could not connect to the database:\n" . $this->mysqli->connect_error);//诊断连接错误
    } 


    //设置字符集
    $this->mysqli->query("set names 'utf8';");//编码转化


    //选择数据库
    $this->select_db = $this->mysqli->select_db($this->mysql_conf['db']);
    if (!$this->select_db) {
        die("could not connect to the db:\n" .  $this->mysqli->error);
    } 
   }



   /**
    * 查询某个字段
    * @param
    * key_id 对比的关键字
    * comparison_key 比较表中列
    * select_key 需要查找的关键字
    * table_name 查找的表名
    * @return string or int
    *
    * eg:-----------------------------------------------
    * $db =new DB();
    *$res=$db->getOne("order","name","id","006");
    *while ($row = $res->fetch_assoc()) {
    *   var_dump($row);
    *}
    */
    public function getOne($table_name,$select_key,$comparison_key,$key_id){
     $db_tmp=$this->mysql_conf['db'];
      $sql = "select $select_key from $db_tmp.$table_name where $comparison_key='$key_id';";
      $res = $this->mysqli->query($sql);
      if (!$res) {
          die("sql error:\n" . $this->mysqli->error);
      }
     return $res;
    }

    //获取一行记录,return array 一维数组
    //
    // function __autoload($a){
    // 将对应的类文件加载进来
    //     if(is_file("$a.class.php")){
    //         include_once "$a.class.php";
    //     }
    //     else{
    //         echo "没有文件";
    //     }
    // }
    //实例化
    // $db =new DB();
    // 
    // eg：---------------------------------------------
    // $res=$db->getRow("order","id","006");
    // while ($row = $res->fetch_assoc()) {
    //     var_dump($row);
    // }
    //
    public function getRow($table_name,$comparison_key,$key_id){
     $db_tmp=$this->mysql_conf['db'];
      $sql = "select * from $db_tmp.$table_name where $comparison_key='$key_id';";
      $res = $this->mysqli->query($sql);
      if (!$res) {
          die("sql error:\n" . $this->mysqli->error);
      }
      return $res;
    }


    public function getCustomRow($table_name,$custom){
     $db_tmp=$this->mysql_conf['db'];
      $sql = "select * from $db_tmp.$table_name where $custom;";
      // echo $sql;
      $res = $this->mysqli->query($sql);
      if (!$res) {
          die("sql error:\n" . $this->mysqli->error);
      }
      return $res;
    }

    //获取表数据
    //return 需要查询的表
    //eg:---------------------------------------
    //$db =new DB();
    // $res=$db->getAll("order");
    // while ($row = $res->fetch_assoc()) {
    //     var_dump($row);
    // }
    public function getAll($table_name){

     $db_tmp=$this->mysql_conf['db'];
     $sql = "select * from $db_tmp.$table_name;";
      $res = $this->mysqli->query($sql);
      if (!$res) {
          die("sql error:\n" . $this->mysqli->error);
      }
      return $res;
    }



/**
 * 范围查询
 * @param  [type] $table_name [description]
 * @param  [type] $key        [description]
 * @param  [type] $begin      [description]
 * @param  [type] $end        [description]
 * @return [type]             [description]
 */
  public function getPage($table_name,$key,$begin,$end){

    $db_tmp=$this->mysql_conf['db'];
    $sql="select * from $db_tmp.$table_name where $key between $begin and $end;"; 

    $res = $this->mysqli->query($sql);
    if (!$res) {
        die("sql error:\n" . $this->mysqli->error);
    }
    return $res;
  }


  
   //  public function selectAll($table,$where,$fields='*',$order='',$skip=0,$limit=1000)
   //  {
   //            if(is_array($where)){
   //                  foreach ($where as $key => $val) {
   //                      if (is_numeric($val)) {
   //                          $condition = $key.'='.$val;
   //                      }else{
   //                          $condition = $key.'=\"'.$val.'\"';
   //                      }
   //                  }
   //            } else {
   //              $condition = $where;
   //            }
   //            if (!empty($order)) {
   //                $order = " order by ".$order;
   //            }
   //            $sql = "select $fields from $table where $condition $order limit $skip,$limit";
   //            $query = $this->query($sql);
   //            $list = array();
   //            while ($r= $this->getFormSource($query)) {
   //                $list[] = $r;
   //            }
   //            return $list;
   //  }


     /**
     * 定义添加数据的方法
     * return 改变的行数(int)
     *$term不传入值这插入表中的所有字段
     *主键必须插入
     * 
     *eg:-----------------------------------------------
     *$data=array("008");
     *$term=array("userid");
     *$res=$db->insert("e_user",$term,$data);
     */
     public function insert($table,$term="",$data){
      $t_str="";
      if ($term!="") {
         foreach($term as $t_key=>$t){
            $t_str.="`$t`,";
          }
        $t_str=trim($t_str,',');
        $t_str="($t_str)";
      }
     $v_str='';
     foreach($data as $key=>$v){
        $v_str.="'$v',";
     }
     $v_str=trim($v_str,',');
     //判断数据是否为空
     $db_tmp=$this->mysql_conf['db'];
     $sql="insert into $db_tmp.$table$t_str values ($v_str);";
     
     $res = $this->mysqli->query($sql);
      if (!$res) {
          die("sql error:\n" . $this->mysqli->error);
      }
      return true;
   }


   /*
    * 删除一条数据方法
    * $table, $condition 表名 条件(eg:"id='003'")
    * 无$condition数据传入则删除整张表内容
    * @return 受影响的行数
    *
    * eg:--------------------------------------------------
    * $db =new DB();
    * $data="id='003'";
    * $res=$db->delete("order",$data);
    */
    public function delete($table, $condition=""){
      $where="";
    if ($condition!="") {
            $where=" where $condition";
      }
      $db_tmp=$this->mysql_conf['db'];
      $sql = "delete from $db_tmp.$table$where;";
      // echo $sql;
     $res = $this->mysqli->query($sql);
      if (!$res) {
          die("sql error:\n" . $this->mysqli->error);
      }
      return $res;
    }


   /**
    * [修改操作description]
    * @param [type] $table [表名]
    * @param [type] $data [数据]
    * @param [type] $condition [条件]
    * @return [type]
    *
    *eg:------------------------------------------------
    *$db =new DB();
    *$data="id='001',name='zxcv'";
    *$where="id='002'";
    *$res=$db->update("order",$data,$where);
    */
   public function update($table,$data,$condition,$limit=0){   

    if (!empty($limit)) {
        $limit = " limit ".$limit;
    }else{
        $limit='';
    }

    $db_tmp=$this->mysql_conf['db'];
    //修改SQL语句
    $sql="update $db_tmp.$table set $data where $condition $limit;";
    $res = $this->mysqli->query($sql);
      if (!$res) {
          die("sql error:\n" . $this->mysqli->error);
      }
      return $res;
   }


   /**
    * 关闭数据库连接
    */   
   public function close_content(){
    $this->mysqli->close();
   }
}
?>