/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.29 : Database - tas_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tas_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `tas_db`;

/*Table structure for table `invitation` */

DROP TABLE IF EXISTS `invitation`;

CREATE TABLE `invitation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `inviter_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `invited_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `invited_team_id` varchar(50) DEFAULT NULL,
  `approved` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'just ignore this',
  `description` varchar(999) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `applicant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `notify_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `invitation_time` datetime DEFAULT NULL,
  `status` int DEFAULT '0' COMMENT 'whether it is readed',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `invitation` */

insert  into `invitation`(`id`,`inviter_id`,`invited_id`,`invited_team_id`,`approved`,`description`,`type`,`applicant_id`,`notify_id`,`invitation_time`,`status`) values (184,'0','0','201',NULL,'I am chandler','ApplicantApply','11140003','11150010','2022-10-22 14:55:49',1),(185,'0','0','201',NULL,'I am chandler','ApplicantApply','11140003','11150011','2022-10-22 14:55:49',0),(186,'0','0','201',NULL,'I am chandler','ApplicantApply','11140003','11150012','2022-10-22 14:55:49',0),(187,'0','0','201',NULL,'I am Ross','ApplicantApply','11140004','11150010','2022-10-22 14:56:04',1),(188,'0','0','201',NULL,'I am Ross','ApplicantApply','11140004','11150011','2022-10-22 14:56:04',0),(189,'0','0','201',NULL,'I am Ross','ApplicantApply','11140004','11150012','2022-10-22 14:56:04',0);

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Surname` varchar(25) DEFAULT NULL,
  `FirstName` varchar(25) DEFAULT NULL,
  `Email` varchar(25) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `staff` */

insert  into `staff`(`id`,`Surname`,`FirstName`,`Email`,`PASSWORD`) values (2115,'Tribbiani','Joseph','2754496408@qq.com','123'),(2116,'Rachel','Green','jakuradyhdyh@gmail.com','321');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Surname` varchar(25) DEFAULT NULL,
  `FirstName` varchar(25) DEFAULT NULL,
  `Email` varchar(25) DEFAULT NULL,
  `Password` varchar(99) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Major` varchar(99) DEFAULT NULL,
  `Minor` varchar(99) DEFAULT NULL,
  `Skills` varchar(99) DEFAULT NULL,
  `Self_Introduction` varchar(9999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `group_Status` int DEFAULT '0',
  `group_id` int DEFAULT '0',
  `status` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11151042 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `student` */

insert  into `student`(`id`,`Surname`,`FirstName`,`Email`,`Password`,`Major`,`Minor`,`Skills`,`Self_Introduction`,`group_Status`,`group_id`,`status`) values (11140001,'Green','Rachel','jakuradyhdyh@gmail.com','123','IS','Arts','Java,Php','like sleepingsss',1,528,2),(11140002,'Geller','Monica','Phil@gmail.com','123','IS','Music','Java','like skateboard,haha',1,507,2),(11140003,'Bing','Chandler','Bing@gmail.com','123','IS','Music','Php','In my spare time, I like to do anything relating to English such as listening to English songs, watching English movies or TV programs, or even attending the activities held by some English clubs or institutes. I used to go abroad for a short- term English study.',0,0,0),(11140004,'Geller','Ross','Lousia@gmail.com','123','CS','English','React','like swimming',0,0,1),(11150001,'Buffay','Phoebe','Scott@gmail.com','123','IS','Folk Music','Php,React','like sleeping ',1,200,2),(11150002,'Tribbiani','Joseph','Yu@gamil.com','123','CS','Computer','Java,Php,React','like football',1,200,1),(11150007,'Aniston','Jennifer','Jay@gmail.com','123','IS','Math','Java,Php','a normal guy',1,200,1),(11150009,'Cox','Courteney','Monica@gmail.com','123','CS','Culture Music','Java,React','I like to read English story books in my free time. Sometimes I surf the Internet and download the E- books to read. Reading E- books is fun. In addition, it also enlarges my vocabulary words because of the advanced technology and the vivid animations. ',1,200,1),(11150010,'Perry','Matthew','Kit@gmail.com','123','CS','Math','Java,Php','like dancing',1,201,2),(11150011,'Schwimmer','David','Emilia@gmail.com','123','IS','Math','Java','good at communicating',1,201,1),(11150012,'Kudrow','Lisa','SEAN@gmail.com','123','CS','English','Php,Java,React','a lazy guy',1,201,1),(11150013,'LeBlanc','Matt','Michelle@gmail.com','123','CS','English','Java,Php,React','like shopping',0,0,0),(11150014,'Madden','Richard','Richard@gmail.com','123','IS','Folk Music','Java,Php','good at evething',0,0,0),(11150015,'Turner','Sophie','Sophie@gmail.com','123','IS','Music','Php,React','like playing',0,0,0),(11150016,'Williams','Maisie','Maisie@gmail.com','123','CS','English','Php,Java,React','like travelling',0,0,0),(11150017,'Stark','Tony','Art@gmail.com','123','IS','Math','React','like eating snacks',0,0,0),(11150018,'Cooper','Stephen','Stephen@gmail.com','123','CS','Folk Music','Php,React','like watching situation comedys',0,0,0),(11151005,'Rauch','Melissa','Buffay666@gmail.com','123','IS','CS','Java,React','In my spare time, I like to read novels. I think reading could enlarge my knowledge. As for novels, I could imagine whatever I like such as a well-known scientist or a kung-fu master. A lot of grownups think playing PC games hinders the students from learning.',1,505,2),(11151006,'Lee','Sheldon','Tom@163.com','123','CS','IS','Java,React','like playing Computer Games and travelling',1,506,2),(11151007,'Cuoco','Kaley','Doth123@gmail.com','123','CS','English','Php,Java,React','like travelling',0,0,0),(11151033,'Helberg','Simon','2754496408@qq.com','123','CS','is','JAVA','I am Jack',0,0,0);

/*Table structure for table `team` */

DROP TABLE IF EXISTS `team`;

CREATE TABLE `team` (
  `id` int NOT NULL AUTO_INCREMENT,
  `StudentID1` int DEFAULT NULL,
  `StudentID2` int DEFAULT NULL,
  `StudentID3` int DEFAULT NULL,
  `StudentID4` int DEFAULT NULL,
  `team_introduction` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `StudentID1` (`StudentID1`),
  KEY `StudentID2` (`StudentID2`),
  KEY `StudentID3` (`StudentID3`),
  KEY `StudentID4` (`StudentID4`)
) ENGINE=InnoDB AUTO_INCREMENT=529 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `team` */

insert  into `team`(`id`,`StudentID1`,`StudentID2`,`StudentID3`,`StudentID4`,`team_introduction`) values (200,11150001,11150002,11150007,11150009,'Welcome to our team. Our group member Yu wang whose id is 11150002 is good at Java,Php,React. And other group members like Jay is good at Java too. We can work together to get a good grade!'),(201,11150010,11150011,11150012,0,'Welcome to our team. Our leader is Kit whose id 11150010 and she is nice person. Don\'t be worry or hesitate,just join us!!!'),(505,11151005,0,0,0,NULL),(506,11151006,0,0,0,NULL),(507,11140002,0,0,0,NULL),(528,11140001,0,0,0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
