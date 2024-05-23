# Java框架技术2-实验和作业

## 环境

### 后端

#### JDK 1.8

- jdk1.8 官网下载地址 https://www.oracle.com/cn/java/technologies/downloads/#java8-windows

#### Apache Tomcat 9

- 官网下载地址 https://archive.apache.org/dist/tomcat/tomcat-9/

#### Apache Maven 3.6.3

- 官网下载地址 https://archive.apache.org/dist/maven/maven-3/

#### MySQL 8

- 官网下载地址 https://downloads.mysql.com/archives/installer/

### 前端

#### Node.js 18

- 官网下载地址 https://nodejs.org/dist/v18.13.0/

## 必要配置

#### 后端

- 修改数据库连接，复制 `resources` 目录下的 `template.db.properties` 为 `db.properties`，自行配置。（`template-application-dev.yml`文件同理）
- 在idea中配置Tomcat（springboot-lab2可不用）

#### 前端

1. 进入前端项目文件夹内部，打开 cmd 或 PowerShell
2. 运行 `npm run install` 拉取依赖依赖（需要node环境）

## 运行

#### 后端

- 在idea中直接启动

#### 前端

1. 进入前端项目文件夹内部，打开 cmd 或 PowerShell
2. 运行 `npm run dev` 启动项目（需要node环境）