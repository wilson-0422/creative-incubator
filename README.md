# 跨部门创意立项协作平台

## 项目简介

跨部门创意立项协作平台（Creative Incubator）是一个面向企业内部的创新管理平台，支持从创意提报、评审流转、项目落地到沉淀归档的全生命周期管理。平台旨在打破部门壁垒，促进跨部门协作，推动优质创意高效落地。

## 核心功能

### 1. 创意提报
- 支持新品立项、流程优化、营销创意、技术创新等多种创意类别
- 草稿保存与直接提交双模式
- 提报信息包含标题、描述、类别、部门等

### 2. 评审流转
- 多维度评审机制，支持评分（0-100）和文字评论
- 评审结论：通过/驳回
- 多人评审，达到2人以上全部通过自动审批通过

### 3. 项目管理
- 通过评审的创意可立项为项目
- 项目全生命周期管理（进行中、已完成、暂停、已取消）
- 项目关联原始创意提报

### 4. 任务中心
- 项目下可创建多个任务
- 任务分配给具体负责人
- 任务状态流转：待办 → 进行中 → 已完成

### 5. 项目归档
- 已完成项目可归档沉淀
- 归档包含项目总结和成果描述
- 归档记录可随时查阅

### 6. 数据仪表盘
- 全局数据统计：提报数、待评审、进行中项目、已完成任务等
- 最近提报、进行中项目、待办任务快速预览

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 17 | 编程语言 |
| Spring Boot | 3.2.5 | 应用框架 |
| Spring Security | 6.x | 安全认证 |
| Spring Data JPA | 3.x | 数据访问 |
| Thymeleaf | 3.x | 模板引擎 |
| Thymeleaf Layout Dialect | 3.x | 模板布局 |
| H2 Database | 2.x | 内嵌数据库 |
| Maven | 3.9 | 构建工具 |

## 项目结构

```
repo/
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/creative/incubator/
│       │   ├── CreativeIncubatorApplication.java
│       │   ├── config/
│       │   │   ├── SecurityConfig.java
│       │   │   └── WebConfig.java
│       │   ├── controller/
│       │   │   ├── AuthController.java
│       │   │   ├── ProposalController.java
│       │   │   ├── ReviewController.java
│       │   │   ├── ProjectController.java
│       │   │   ├── TaskController.java
│       │   │   ├── ArchiveController.java
│       │   │   └── DashboardController.java
│       │   ├── model/
│       │   │   ├── User.java
│       │   │   ├── Proposal.java
│       │   │   ├── Review.java
│       │   │   ├── Project.java
│       │   │   ├── Task.java
│       │   │   └── Archive.java
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   ├── ProposalRepository.java
│       │   │   ├── ReviewRepository.java
│       │   │   ├── ProjectRepository.java
│       │   │   ├── TaskRepository.java
│       │   │   └── ArchiveRepository.java
│       │   ├── service/
│       │   │   ├── UserService.java
│       │   │   ├── ProposalService.java
│       │   │   ├── ReviewService.java
│       │   │   ├── ProjectService.java
│       │   │   ├── TaskService.java
│       │   │   └── ArchiveService.java
│       │   └── dto/
│       │       └── DashboardStats.java
│       └── resources/
│           ├── application.properties
│           ├── data.sql
│           ├── templates/
│           │   ├── layout.html
│           │   ├── index.html
│           │   ├── fragments/
│           │   │   ├── header.html
│           │   │   └── footer.html
│           │   ├── auth/
│           │   │   ├── login.html
│           │   │   └── register.html
│           │   ├── proposals/
│           │   │   ├── list.html
│           │   │   ├── detail.html
│           │   │   ├── create.html
│           │   │   └── edit.html
│           │   ├── reviews/
│           │   │   ├── list.html
│           │   │   ├── detail.html
│           │   │   └── review.html
│           │   ├── projects/
│           │   │   ├── list.html
│           │   │   ├── detail.html
│           │   │   ├── create.html
│           │   │   └── edit.html
│           │   ├── tasks/
│           │   │   ├── list.html
│           │   │   ├── create.html
│           │   │   └── detail.html
│           │   ├── archives/
│           │   │   ├── list.html
│           │   │   └── detail.html
│           │   └── dashboard/
│           │       └── overview.html
│           └── static/
│               ├── css/style.css
│               └── js/main.js
```

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.9+

### 本地运行

```bash
cd repo
mvn clean package -DskipTests
java -jar target/*.jar
```

访问 http://localhost:8080

### Docker 部署

```bash
cd creative-incubator
docker build -t creative-incubator .
docker run -p 8080:8080 creative-incubator
```

### H2 控制台

访问 http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:creativeincubator`
- 用户名: `sa`
- 密码: （空）

## 演示账号

| 用户名 | 密码 | 姓名 | 部门 | 角色 |
|--------|------|------|------|------|
| admin | admin123 | 系统管理员 | 信息技术部 | 管理员 |
| zhangwei | pass123 | 张伟 | 产品研发部 | 提报人 |
| lina | pass123 | 李娜 | 市场营销部 | 提报人 |
| wangqiang | pass123 | 王强 | 技术架构部 | 评审人 |
| zhaoli | pass123 | 赵丽 | 用户体验部 | 评审人 |
| liuyang | pass123 | 刘洋 | 数据分析部 | 提报人 |
| chenming | pass123 | 陈明 | 运营管理部 | 评审人 |

## 数据模型

### 用户角色
- **ADMIN**：系统管理员，拥有全部权限
- **REVIEWER**：评审人，可对创意提报进行评审
- **PROPOSER**：提报人，可创建和管理创意提报

### 提报状态流转
```
草稿(DRAFT) → 已提交(SUBMITTED) → 评审中(UNDER_REVIEW) → 已通过(APPROVED)
                                                    └→ 已驳回(REJECTED)
```

### 项目状态流转
```
进行中(ACTIVE) → 已完成(COMPLETED)
              → 暂停(ON_HOLD)
              → 已取消(CANCELLED)
```

### 任务状态流转
```
待办(TODO) → 进行中(IN_PROGRESS) → 已完成(DONE)
                               → 已取消(CANCELLED)
```

## 许可证

本项目仅供内部使用。
