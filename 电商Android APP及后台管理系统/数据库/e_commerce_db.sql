/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.0.96-community-nt : Database - e_commerce_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`e_commerce_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `e_commerce_db`;

/*Table structure for table `a_user` */

DROP TABLE IF EXISTS `a_user`;

CREATE TABLE `a_user` (
  `AdminId` varchar(16) character set latin1 NOT NULL,
  `AdminName` varchar(16) default NULL,
  `AdminPassword` varchar(16) character set latin1 default NULL,
  `AdminLastLoginTime` varchar(20) character set latin1 default NULL,
  PRIMARY KEY  (`AdminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `a_user` */

insert  into `a_user`(`AdminId`,`AdminName`,`AdminPassword`,`AdminLastLoginTime`) values 
('1','admin','admin','2018-06-06');

/*Table structure for table `e_address` */

DROP TABLE IF EXISTS `e_address`;

CREATE TABLE `e_address` (
  `addressId` int(11) NOT NULL auto_increment,
  `userID` varchar(16) character set latin1 NOT NULL,
  `addressName` varchar(250) NOT NULL,
  `consignee` varchar(20) default NULL,
  `phone` varchar(16) default NULL,
  PRIMARY KEY  (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `e_address` */

insert  into `e_address`(`addressId`,`userID`,`addressName`,`consignee`,`phone`) values 
(1,'5b2cf7bec59f6','xzcvfdyh','何平','13193170785'),
(2,'5b2cf7bec59f6','九所Tim许墨','张三','1348978466'),
(3,'5b2cf7bec59f6','重庆文理学院a区','张佳窈','13193170785'),
(4,'5b2cf7bec59f6','重庆文理学院','何苹','15923854631');

/*Table structure for table `e_goods` */

DROP TABLE IF EXISTS `e_goods`;

CREATE TABLE `e_goods` (
  `goodsId` int(11) NOT NULL auto_increment,
  `text` varchar(200) default NULL,
  `imageUrl` varchar(500) character set latin1 default NULL,
  `spanSize` int(11) default NULL,
  `price` float default '0',
  `desc` varchar(500) default NULL,
  `sortId` int(11) default NULL,
  `stock` varchar(11) default NULL,
  PRIMARY KEY  (`goodsId`),
  KEY `sortId` (`sortId`),
  CONSTRAINT `sortId` FOREIGN KEY (`sortId`) REFERENCES `e_sort_lists` (`sortId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `e_goods` */

insert  into `e_goods`(`goodsId`,`text`,`imageUrl`,`spanSize`,`price`,`desc`,`sortId`,`stock`) values 
(1,'赛彩（Saicai）3D立体墙贴墙纸','https://m.360buyimg.com/mobilecms/s370x370_jfs/t16915/187/781891407/480626/942373a7/5aa7728fN4612030e.jpg!q70.jpg.dpg',2,19.9,'赛彩（Saicai）3D立体墙贴墙纸...',3,'10'),
(2,'小米（MI） 小米8','https://m.360buyimg.com/mobilecms/s370x370_jfs/t21820/235/1720758287/358809/2ba87d21/5b321685Nc0f90d5a.jpg!q70.jpg.dpg',2,2888,'小米（MI） 小米8 全面屏游戏手机 黑色 全网通( 6G 64G)',4,'10'),
(35,'我的商品','http://192.168.191.1/image/goodsImages/book_01.gif',2,12,'我的商品我的商品我的商品我的商品',2,'12');

/*Table structure for table `e_goods_banners` */

DROP TABLE IF EXISTS `e_goods_banners`;

CREATE TABLE `e_goods_banners` (
  `goodsBannersId` int(11) NOT NULL auto_increment,
  `goodsId` int(11) NOT NULL,
  `goodsBannersImgUrl` varchar(500) character set latin1 default NULL,
  PRIMARY KEY  (`goodsBannersId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `e_goods_banners` */

insert  into `e_goods_banners`(`goodsBannersId`,`goodsId`,`goodsBannersImgUrl`) values 
(1,2,'https://m.360buyimg.com/mobilecms/s750x750_jfs/t21841/220/511398767/264686/1baa44ab/5b0fca14Nc58ff97f.jpg!q80.dpg'),
(2,2,'https://m.360buyimg.com/mobilecms/s750x750_jfs/t20284/199/510812106/55652/836097aa/5b0fca18N084af63f.jpg!q80.dpg'),
(3,1,'https://m.360buyimg.com/mobilecms/s750x750_jfs/t16915/187/781891407/480626/942373a7/5aa7728fN4612030e.jpg!q80.jpg.webp'),
(4,1,'https://m.360buyimg.com/mobilecms/s750x750_jfs/t14194/360/2370177236/377705/199fde70/5a40c8eeN4b61c146.jpg!q80.dpg'),
(5,35,'http://192.168.191.1/image/goodsImages/book_01.gif'),
(6,35,'http://192.168.191.1/image/goodsImages/book_02.gif'),
(7,35,'http://192.168.191.1/image/goodsImages/book_03.gif'),
(8,35,'http://192.168.191.1/image/goodsImages/book_01.gif'),
(9,35,'http://192.168.191.1/image/goodsImages/book_02.gif'),
(10,35,'http://192.168.191.1/image/goodsImages/book_03.gif');

/*Table structure for table `e_goods_details` */

DROP TABLE IF EXISTS `e_goods_details`;

CREATE TABLE `e_goods_details` (
  `goodsDetailsID` int(11) NOT NULL auto_increment,
  `goodsId` int(11) NOT NULL,
  `goodsDetailsMenuId` int(11) default NULL,
  `goodsDetailsImgUrl` varchar(500) character set latin1 default NULL,
  PRIMARY KEY  (`goodsDetailsID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `e_goods_details` */

insert  into `e_goods_details`(`goodsDetailsID`,`goodsId`,`goodsDetailsMenuId`,`goodsDetailsImgUrl`) values 
(1,2,1,'https://img12.360buyimg.com/popWareDetail/jfs/t18619/319/1211987981/43181/d2b48c01/5abf5d05N61fc92d1.jpg.dpg'),
(2,2,2,'https://m.360buyimg.com/mobilecms/s750x750_jfs/t20284/199/510812106/55652/836097aa/5b0fca18N084af63f.jpg!q80.dpg'),
(3,2,1,'https://img30.360buyimg.com/popWareDetail/jfs/t15835/263/2647265623/132805/bd935ec8/5ab5eeb8Nff3a86ca.jpg.dpg'),
(4,2,1,'https://img30.360buyimg.com/popWareDetail/jfs/t14545/22/2427578447/60864/9f58fa46/5a9f5c5fN498c6f65.jpg.dpg'),
(5,2,1,'https://img14.360buyimg.com/popWaterMark/jfs/t21040/71/579488208/365666/d8b24f90/5b120446N56e6cabf.jpg.dpg'),
(6,2,1,'https://img20.360buyimg.com/popWaterMark/jfs/t21844/304/581760104/157123/d793ed80/5b120442N7dccb2c5.jpg.dpg'),
(7,1,1,'https://img12.360buyimg.com/imgzone/jfs/t12829/360/2023650746/323147/5c386d82/5a337a5eN1bf63397.jpg.dpg'),
(8,1,2,'https://img12.360buyimg.com/imgzone/jfs/t12448/252/2167418273/430824/36e0598d/5a337a73N8ad6de60.jpg.dpg'),
(9,1,1,'https://img10.360buyimg.com/imgzone/jfs/t13309/272/2144476830/302841/f3a11b1a/5a337a70N38cf0374.jpg.dpg'),
(10,1,1,'https://img12.360buyimg.com/imgzone/jfs/t12829/360/2023650746/323147/5c386d82/5a337a5eN1bf63397.jpg.dpg'),
(11,35,1,'http://192.168.191.1/image/goodsImages/book_04.gif'),
(12,35,1,'http://192.168.191.1/image/goodsImages/book_05.gif'),
(13,35,1,'http://192.168.191.1/image/goodsImages/book_06.gif'),
(14,35,2,'http://192.168.191.1/image/goodsImages/book_07.gif'),
(15,35,2,'http://192.168.191.1/image/goodsImages/book_07.gif'),
(16,35,2,'http://192.168.191.1/image/goodsImages/book_08.gif');

/*Table structure for table `e_goods_details_menu` */

DROP TABLE IF EXISTS `e_goods_details_menu`;

CREATE TABLE `e_goods_details_menu` (
  `goodsDetailsMenuId` int(11) NOT NULL auto_increment,
  `goodsId` int(11) NOT NULL,
  `goodsDetailsMenuName` varchar(20) default NULL,
  PRIMARY KEY  (`goodsDetailsMenuId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `e_goods_details_menu` */

insert  into `e_goods_details_menu`(`goodsDetailsMenuId`,`goodsId`,`goodsDetailsMenuName`) values 
(1,2,'商品详情'),
(2,2,'规格参数');

/*Table structure for table `e_index_banners` */

DROP TABLE IF EXISTS `e_index_banners`;

CREATE TABLE `e_index_banners` (
  `bannersId` int(11) NOT NULL auto_increment,
  `imageUrl` varchar(500) character set latin1 default NULL,
  `goodsId` int(11) default NULL,
  PRIMARY KEY  (`bannersId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `e_index_banners` */

insert  into `e_index_banners`(`bannersId`,`imageUrl`,`goodsId`) values 
(1,'https://m.360buyimg.com/mobilecms/s1278x626_jfs/t20545/200/858307689/149361/5caf3483/5b1a59a7Nb3249e6c.jpg!cr_1125x549_0_72!q70.jpg.dpg.webp',1),
(2,'https://m.360buyimg.com/mobilecms/s1125x690_jfs/t20500/309/1249933900/195300/23c1b449/5b220e52N4afc515c.jpg!cr_1125x549_0_72!q70.jpg',2),
(3,'https://m.360buyimg.com/mobilecms/s1280x626_jfs/t20188/33/787371598/121420/d1ae6f57/5b17c93aNfed9dc41.jpg!cr_1125x549_0_72!q70.jpg.dpg.webp',3),
(4,'https://m.360buyimg.com/mobilecms/s1278x624_jfs/t21802/321/1722750563/105275/d87298d3/5b334913N6b839b4a.jpg!cr_1125x549_0_72!q70.jpg.dpg.webp',4),
(5,'https://m.360buyimg.com/mobilecms/s1278x624_jfs/t20554/44/1357761817/442453/b912dcc1/5b2786a7N1e55bc2f.jpg!cr_1125x549_0_72!q70.jpg.dpg.webp',5),
(6,'https://img1.360buyimg.com/da/s1278x624_jfs/t24436/32/567322990/192690/2dcb5bf0/5b35cff4N444eb273.jpg!cr_1125x549_0_72.dpg.webp',6),
(7,'https://img1.360buyimg.com/da/s1278x624_jfs/t24436/32/567322990/192690/2dcb5bf0/5b35cff4N444eb273.jpg!cr_1125x549_0_72.dpg.webp',7);

/*Table structure for table `e_order_evaluate` */

DROP TABLE IF EXISTS `e_order_evaluate`;

CREATE TABLE `e_order_evaluate` (
  `EvaluateId` int(11) NOT NULL auto_increment,
  `Score` int(11) default '0',
  `Comment` varchar(500) default NULL,
  `OID` int(11) NOT NULL,
  PRIMARY KEY  (`EvaluateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `e_order_evaluate` */

/*Table structure for table `e_order_evaluate_img` */

DROP TABLE IF EXISTS `e_order_evaluate_img`;

CREATE TABLE `e_order_evaluate_img` (
  `EvaluateImgId` int(11) NOT NULL auto_increment,
  `EvaluateImgUrl` varchar(500) default NULL,
  `EvaluateId` int(11) default NULL,
  PRIMARY KEY  (`EvaluateImgId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `e_order_evaluate_img` */

/*Table structure for table `e_order_list` */

DROP TABLE IF EXISTS `e_order_list`;

CREATE TABLE `e_order_list` (
  `OID` int(11) NOT NULL auto_increment,
  `goodsId` int(11) NOT NULL,
  `orderTime` varchar(20) character set latin1 default NULL,
  `userID` varchar(16) character set latin1 NOT NULL,
  `count` int(11) default '0',
  `istake` varchar(4) default 'no',
  PRIMARY KEY  (`OID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `e_order_list` */

insert  into `e_order_list`(`OID`,`goodsId`,`orderTime`,`userID`,`count`,`istake`) values 
(6,1,'2018/06/26 12:13:46','5b2cf7bec59f6',1,'yes'),
(7,2,'2018/06/26 12:14:15','5b2cf7bec59f6',2,'yes'),
(8,1,'2018/06/26 12:14:55','5b2cf7bec59f6',2,'yes'),
(9,1,'2018/06/27 00:41:04','5b2cf7bec59f6',1,'no'),
(10,2,'2018/06/27 00:41:04','5b2cf7bec59f6',1,'yes'),
(11,2,'2018/06/27 12:37:31','5b2cf7bec59f6',1,'yes'),
(12,2,'2018/07/04 00:50:10','5b2cf7bec59f6',1,'yes');

/*Table structure for table `e_shop_cart` */

DROP TABLE IF EXISTS `e_shop_cart`;

CREATE TABLE `e_shop_cart` (
  `goodsId` int(11) NOT NULL,
  `goodsCount` int(11) NOT NULL default '0',
  `userID` varchar(16) character set latin1 NOT NULL,
  PRIMARY KEY  (`goodsId`,`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `e_shop_cart` */

insert  into `e_shop_cart`(`goodsId`,`goodsCount`,`userID`) values 
(1,3,'5b2cf7bec59f6'),
(2,1,'5b2af6aba2eee'),
(2,3,'5b2cf9777d66d'),
(4,3,'5b2af6aba2eee');

/*Table structure for table `e_sort_content` */

DROP TABLE IF EXISTS `e_sort_content`;

CREATE TABLE `e_sort_content` (
  `sortContentID` int(11) NOT NULL auto_increment,
  `sortContentImgUrl` varchar(500) character set latin1 default NULL,
  `sortContentName` varchar(20) default NULL,
  `titId` int(11) default NULL,
  PRIMARY KEY  (`sortContentID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `e_sort_content` */

insert  into `e_sort_content`(`sortContentID`,`sortContentImgUrl`,`sortContentName`,`titId`) values 
(1,'https://img11.360buyimg.com/focus/jfs/t21388/146/237407622/26923/221da1b3/5b054fedN2ba90518.jpg','手机',1),
(2,'https://img30.360buyimg.com/focus/jfs/t22051/318/235303191/9297/c5ea2761/5b055000N410a7553.png','空调',1),
(3,'https://img20.360buyimg.com/focus/jfs/t21115/83/225125274/13856/5473fb3f/5b0567c1N59d53b27.png','冰箱',2),
(4,'https://img14.360buyimg.com/focus/jfs/t21664/15/237213959/24996/a3c6c7d6/5b0567c7N9cc1c355.png','洗衣机',2),
(5,'https://img30.360buyimg.com/focus/jfs/t13411/188/926813276/3945/a4f47292/5a1692eeN105a64b4.png','小米',3),
(6,'https://img30.360buyimg.com/focus/jfs/t12559/262/969294499/3436/8c0ce9c9/5a17f1d2N8078d5e6.jpg','电水壶',4),
(7,'https://img13.360buyimg.com/focus/jfs/t11071/195/2462134264/9117/cd0688bf/5a17ba79N18b9f3d4.png','轻薄本',5),
(8,'https://img30.360buyimg.com/focus/jfs/t17449/23/1286271766/3527/870ce296/5ac4780cN6087feb5.jpg','夹克',6);

/*Table structure for table `e_sort_lists` */

DROP TABLE IF EXISTS `e_sort_lists`;

CREATE TABLE `e_sort_lists` (
  `sortId` int(11) NOT NULL auto_increment,
  `sortName` varchar(50) default NULL,
  PRIMARY KEY  (`sortId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `e_sort_lists` */

insert  into `e_sort_lists`(`sortId`,`sortName`) values 
(0,'热门推荐'),
(1,'手机数码'),
(2,'家用电器'),
(3,'电脑办公'),
(4,'男装'),
(5,'男鞋'),
(6,'女装'),
(7,'女鞋');

/*Table structure for table `e_sort_tit` */

DROP TABLE IF EXISTS `e_sort_tit`;

CREATE TABLE `e_sort_tit` (
  `titId` int(11) NOT NULL auto_increment,
  `section` varchar(20) default NULL,
  `sortId` int(11) default NULL,
  PRIMARY KEY  (`titId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `e_sort_tit` */

insert  into `e_sort_tit`(`titId`,`section`,`sortId`) values 
(1,'热门分类',0),
(2,'家电热搜',0),
(3,'热门品牌',1),
(4,'厨房小电',2),
(5,'热卖分类',3),
(6,'热卖选购',4);

/*Table structure for table `e_user` */

DROP TABLE IF EXISTS `e_user`;

CREATE TABLE `e_user` (
  `userID` varchar(16) character set latin1 NOT NULL,
  `username` varchar(16) default NULL,
  `password` varchar(20) character set latin1 default NULL,
  `HeadPortrait` varchar(250) character set latin1 default 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2960512808,749232260&fm=27&gp=0.jpg',
  `UserSex` varchar(8) default '男',
  `UserPhone` varchar(16) character set latin1 default NULL,
  `UserEmail` varchar(20) character set latin1 default NULL,
  `UserBirth` varchar(20) default '1990/01/01',
  PRIMARY KEY  (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `e_user` */

insert  into `e_user`(`userID`,`username`,`password`,`HeadPortrait`,`UserSex`,`UserPhone`,`UserEmail`,`UserBirth`) values 
('5b2cf7bec59f6','何苹','123456','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2960512808,749232260&fm=27&gp=0.jpg','男','15923854631','123@qq.com','1994年08月24日'),
('5b2cf9777d66d','Zhangjiayao','123456','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2960512808,749232260&fm=27&gp=0.jpg','nan','13193170785','131931@qq.com','1990/01/01'),
('5b3ba5a53c2d5','User1','123456','https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2960512808,749232260&fm=27&gp=0.jpg','男','15923854631','123456@qq.com','1990/01/01');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
