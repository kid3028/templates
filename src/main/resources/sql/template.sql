-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2018-04-02 08:32:44
-- 服务器版本： 5.7.20
-- PHP Version: 7.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `template`
--
CREATE DATABASE IF NOT EXISTS `template` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `template`;

-- --------------------------------------------------------

--
-- 表的结构 `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE IF NOT EXISTS `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `role_menu`
--

INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 16),
(4, 1, 17),
(5, 1, 18),
(6, 1, 19),
(7, 2, 18),
(8, 2, 19);

-- --------------------------------------------------------

--
-- 表的结构 `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perm` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔)',
  `perm_url` varchar(300) DEFAULT NULL COMMENT '权限',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `menu_status` tinyint(2) DEFAULT '1' COMMENT '权限状态，0;禁用，1：启用',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

--
-- 转存表中的数据 `sys_menu`
--

INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perm`, `perm_url`, `type`, `icon`, `order_num`, `menu_status`, `gmt_create`, `gmt_modified`) VALUES
(1, 0, '系统管理', NULL, NULL, '', 0, 'fa fa-cog', 0, 1, '2018-04-02 13:07:20', '2018-04-02 13:07:20'),
(2, 1, '管理员列表', '/sys/user', 'roles[admin,sys],perms[/list]', '/list', 1, 'fa fa-user', 1, 1, '2018-04-02 13:10:29', '2018-04-02 13:10:29'),
(16, 2, '新增', '/sys/user/save', 'roles[admin],perms[/save]', '/save', 2, NULL, 0, 1, '2018-04-02 14:49:16', '2018-04-02 14:49:16'),
(17, 2, '删除', '/sys/user/delete', 'roles[sys],perms[/delete]', '/delete', 2, NULL, 0, 1, '2018-04-02 14:53:01', '2018-04-02 14:53:01'),
(18, 2, '修改', '/sys/user/update', 'roles[admin,sys],perms[/update]', '/update', 2, NULL, 0, 1, '2018-04-02 14:51:23', '2018-04-02 14:51:23'),
(19, 2, '查看', '/sys/user/list', 'roles[sys],perms[/list]', '/list', 2, NULL, 0, 1, '2018-04-02 14:45:38', '2018-04-02 14:45:38');

-- --------------------------------------------------------

--
-- 表的结构 `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `role_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) NOT NULL COMMENT '角色名',
  `role_status` tinyint(1) UNSIGNED DEFAULT '0' COMMENT '角色状态，0：不可用， 1：可用',
  `role_desc` varchar(50) DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

--
-- 转存表中的数据 `sys_role`
--

INSERT INTO `sys_role` (`role_id`, `role_name`, `role_status`, `role_desc`, `gmt_create`, `gmt_modified`) VALUES
(1, 'admin', 1, '超级管理员', '2018-04-02 14:54:57', '2018-04-02 14:54:57'),
(2, 'sys', 1, '系统管理员', '2018-04-02 14:55:46', '2018-04-02 14:55:46');

-- --------------------------------------------------------

--
-- 表的结构 `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `user_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(50) NOT NULL COMMENT '账号',
  `nick_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(50) NOT NULL COMMENT '加密密码的盐',
  `user_status` tinyint(1) UNSIGNED DEFAULT '0' COMMENT '用户状态， 0：未激活， 1：已激活',
  `user_type` tinyint(1) UNSIGNED DEFAULT '0' COMMENT '用户类别, 0：普通用户， 1：管理员',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gtm_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`user_id`, `login_name`, `nick_name`, `password`, `salt`, `user_status`, `user_type`, `gmt_create`, `gtm_modified`) VALUES
(1, 'admin', 'admin', 'edb0f8ceee30a87d26d899e434bace2f', 'WPBDIIJ3t+HR5KlBPxbZGQ==', 1, 1, '2018-04-02 14:58:20', '2018-04-02 14:58:20'),
(2, 'sys', 'sys', 'f66a7f70968b8829f4754f234ba4b1db', 'L1cKkpXi8h2kol28HtoTQA==', 1, 1, '2018-04-02 14:59:15', '2018-04-02 14:59:15');

-- --------------------------------------------------------

--
-- 表的结构 `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
