/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 5.7.40 : Database - myspace
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myspace` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myspace`;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论唯一性id',
  `post_id` int(11) NOT NULL COMMENT '评论的帖子id',
  `user_id` int(11) NOT NULL COMMENT '评论的用户id',
  `comment_content` varchar(255) NOT NULL COMMENT '评论的内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论的时间',
  PRIMARY KEY (`id`),
  KEY `fk_comment_user_id` (`user_id`),
  KEY `fk_comment_post_id` (`post_id`),
  CONSTRAINT `fk_comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='保存帖子评论的表';

/*Data for the table `comment` */

insert  into `comment`(`id`,`post_id`,`user_id`,`comment_content`,`create_time`) values 
(1,19,2,'你就是在嫉妒我的露娜太强了','2023-05-27 19:35:18'),
(2,3,2,'求你了 教我','2023-05-27 19:35:18'),
(3,3,1,'求你了 教我','2023-05-27 19:35:18'),
(5,24,2,'2023年5月27号','2023-05-27 19:35:18'),
(7,29,2,'确实','2023-05-28 15:09:24'),
(8,29,2,'hh','2023-05-28 17:35:01'),
(9,29,2,'我无语啦','2023-05-28 17:53:55'),
(24,24,2,'你好啊','2023-05-29 16:10:29'),
(31,19,2,'傻逼','2023-05-29 16:14:27'),
(32,19,2,'1','2023-05-29 16:16:10'),
(33,19,2,'3','2023-05-29 16:16:14'),
(34,20,2,'你好啊','2023-05-29 16:16:31'),
(35,20,2,'111','2023-05-29 16:16:36'),
(36,20,2,'2334','2023-05-29 16:16:42'),
(37,20,2,'1','2023-05-29 16:16:54'),
(38,20,2,'2','2023-05-29 16:18:20'),
(40,29,2,'111','2023-05-29 18:44:49'),
(43,21,2,'确实','2023-06-03 13:54:35'),
(44,21,2,'123','2023-06-03 13:54:42'),
(45,21,2,'你好啊','2023-06-03 13:55:35'),
(46,21,2,'你好','2023-06-03 13:56:00'),
(47,4,2,'1','2023-06-03 13:56:09'),
(48,4,2,'2','2023-06-03 13:56:14'),
(49,4,2,'3','2023-06-03 13:56:56'),
(50,1,2,'好的喔','2023-06-03 14:01:01'),
(52,75,2,'123','2023-06-03 14:05:58'),
(57,1,2,'1','2023-06-03 14:10:26'),
(63,29,2,'确实','2023-06-03 14:17:25'),
(64,29,2,'你好啊','2023-06-03 14:17:33'),
(65,29,2,'123','2023-06-03 14:17:57'),
(68,29,2,'你好啊','2023-06-03 14:21:39'),
(69,29,2,'123','2023-06-03 14:22:04'),
(70,29,2,'1','2023-06-03 14:22:39'),
(83,75,1,'你好啊','2023-06-03 15:37:09'),
(84,23,1,'1','2023-06-03 15:38:02'),
(85,24,1,'21321','2023-06-03 15:38:34'),
(86,19,2,'好杀','2023-06-04 01:53:06');

/*Table structure for table `follow` */

DROP TABLE IF EXISTS `follow`;

CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '追随关系唯一性id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `follow_id` int(11) NOT NULL COMMENT '用户关注的id',
  `is_follow` int(11) NOT NULL DEFAULT '0' COMMENT '是否关注：0表示未关注，1表示关注',
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_follow_id` (`follow_id`),
  CONSTRAINT `fk_follow_id` FOREIGN KEY (`follow_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8 COMMENT='关注关系表';

/*Data for the table `follow` */

insert  into `follow`(`id`,`user_id`,`follow_id`,`is_follow`) values 
(1,1,2,1),
(2,1,3,1),
(3,2,1,1),
(4,3,1,0),
(5,2,3,1),
(6,3,2,0),
(7,4,3,1),
(8,4,2,1),
(9,4,1,1),
(10,5,3,1),
(11,5,4,1),
(12,2,4,0),
(16,7,2,0),
(227,2,7,1),
(228,5,1,0),
(229,11,2,1),
(230,2,11,0),
(231,5,2,1);

/*Table structure for table `post` */

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖子唯一性id',
  `user_id` int(11) NOT NULL COMMENT '哪个用户发的帖子',
  `content` varchar(255) DEFAULT NULL COMMENT '帖子详情',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帖子创建的时间',
  PRIMARY KEY (`id`),
  KEY `fk_post_user_id` (`user_id`),
  CONSTRAINT `fk_post_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COMMENT='用户帖子';

/*Data for the table `post` */

insert  into `post`(`id`,`user_id`,`content`,`create_time`) values 
(1,1,'完成JavaWeb作业','2023-05-25 04:10:15'),
(3,1,'现在在完成用户帖子功能','2023-05-25 12:30:05'),
(4,2,'注意后端api参数类型','2023-05-25 23:41:05'),
(7,3,'多人の轮','2023-05-26 00:10:05'),
(8,3,'肉球の露出','2023-05-26 00:41:05'),
(19,4,'昨天王者碰到了一个傻逼露娜打野，害我输了游戏','2023-05-27 00:10:05'),
(20,5,'你好,世界','2023-05-27 00:30:05'),
(21,2,'昨晚玩了个露娜打野，不小心坑输了别人，对吧，苏煜佳','2023-05-27 00:41:05'),
(23,1,'测试实时帖子的时效功能','2023-05-27 11:27:51'),
(24,1,'今昔是何年','2023-05-27 11:29:51'),
(29,11,'坑壁lxx','2023-05-28 14:38:39'),
(75,2,'213123','2023-06-03 14:05:53'),
(76,2,'我是傻逼','2023-06-04 01:52:23');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一性id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `photo` varchar(200) NOT NULL COMMENT '头像',
  `follower_count` int(11) DEFAULT '0' COMMENT '粉丝数量',
  `birth` varchar(255) NOT NULL COMMENT '生日（用于找回密码）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`photo`,`follower_count`,`birth`) values 
(1,'123','1','http://192.168.174.1:9999/myspace/photo/d1e90e22-97ef-45e5-92b3-baa56e90035a',3,'111111'),
(2,'王科燿','12','http://192.168.174.1:9999/myspace/photo/1309c018-fa2d-407b-9993-38b8ac1a697e',3,'030209'),
(3,'豆腐花不要花','123456','http://192.168.174.1:9999/myspace/photo/2937cc12-cd4c-4d37-8c0a-496f4d68ba5c',3,'222222'),
(4,'little','111111','http://192.168.174.1:9999/myspace/photo/f7cd4da1-0a2b-4c3a-8e0a-f9a2f9f114ff',1,'333333'),
(5,'hzc','qwer','http://192.168.174.1:9999/myspace/photo/484f8007-db22-48f4-8799-f5d7ef317557',1,'444444'),
(6,'1234','123','http://192.168.174.1:9999/myspace/photo/v2-6afa72220d29f045c15217aa6b275808_720w.png',0,'555555'),
(7,'Afterlll','123','http://192.168.174.1:9999/myspace/photo/v2-6afa72220d29f045c15217aa6b275808_720w.png',0,'666666'),
(8,'test','1','http://192.168.174.1:9999/myspace/photo/b436686b-d57e-4bf2-8917-f726bfeb27ca',0,'777777'),
(9,'lzh','12','http://192.168.174.1:9999/myspace/photo/2bf709cd-15fe-43e9-8208-2260e41a8d39',0,'888888'),
(10,'lxx','123','http://192.168.174.1:9999/myspace/photo/7d0833df-239f-4c3a-890b-2b384ef9b3b1',0,'20030317'),
(11,'sblxx','bnm123','http://192.168.174.1:9999/myspace/photo/7d0833df-239f-4c3a-890b-2b384ef9b3b1',1,'020408'),
(12,'我是大哥','111','http://192.168.174.1:9999/myspace/photo/v2-6afa72220d29f045c15217aa6b275808_720w.png',0,'222');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
