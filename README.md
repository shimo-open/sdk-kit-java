# Shimo SDK for Java

石墨文档 Java SDK,提供简洁易用的 API 接口。

## 版本说明

**v2.0** - 全新架构重构版本
- ✅ 实例化客户端,支持连接池复用
- ✅ 按功能模块组织 API
- ✅ 更好的代码可维护性
- ✅ 统一的异常处理
- ✅ 清晰简洁的 API 设计

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>com.shimo.sdksdk</groupId>
    <artifactId>sdk-sdk-java</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 2. 初始化客户端

```java
import com.shimo.sdk.ShimoClient;
import com.shimo.sdk.common.ShimoConfig;

// 方式一:快速初始化(推荐)
ShimoClient client = new ShimoClient(signature, token);

// 方式二:自定义配置
ShimoConfig config = ShimoConfig.builder()
    .signature("your-signature")
    .token("your-token")
    .connectTimeout(30)  // 连接超时 30 秒
    .readTimeout(60)     // 读超时 60 秒
    .writeTimeout(60)    // 写超时 60 秒
    .build();

ShimoClient client = new ShimoClient(config);
```

### 3. 使用 API

```java
try {
    // 文件操作
    client.files().create(createParam);
    client.files().delete(deleteParam);
    ImportFileRes result = client.files().importFile(importParam);
    
    // 表格操作
    GetTableContentRes content = client.tables().getContent(getParam);
    client.tables().updateContent(updateParam);
    
    // 文档操作
    GetPlainTextRes text = client.documents().getPlainText(param);
    GetHistoryRes history = client.documents().getHistory(historyParam);
    
    // 系统操作
    GetAppDetailRes app = client.system().getAppDetail(appParam);
    client.system().activateUserSeat(seatParam);
    
    // 预览操作
    CreatePreviewRes preview = client.preview().create(param);
    
} catch (SdkException e) {
    System.err.println("请求失败: " + e.getMessage());
} finally {
    // 使用完毕后关闭客户端
    client.close();
}
```

## API 模块说明

### FileApi - 文件操作
```java
client.files().create(param);              // 创建文件
client.files().createCopy(param);          // 创建副本
client.files().delete(param);              // 删除文件
client.files().importFile(param);          // 导入文件
client.files().getImportProgress(param);   // 获取导入进度
client.files().exportFile(param);          // 导出文件
client.files().getExportProgress(param);   // 获取导出进度
client.files().exportTableSheets(param);   // 导出表格为 Excel
```

### TableApi - 表格操作
```java
client.tables().getContent(param);         // 获取表格内容
client.tables().updateContent(param);      // 更新表格内容
client.tables().appendContent(param);      // 追加表格内容
client.tables().deleteRow(param);          // 删除表格行
client.tables().addSheet(param);           // 新增工作表
client.tables().uploadAttachment(param);   // 上传附件
```

### DocumentApi - 文档操作
```java
client.documents().getHistory(param);              // 获取历史版本
client.documents().getRevision(param);             // 获取版本列表
client.documents().getPlainText(param);            // 获取纯文本内容
client.documents().getPlainTextWordCount(param);   // 字数统计
client.documents().getMentionAt(param);            // 获取 @人 信息
client.documents().getCommentCount(param);         // 获取评论数
client.documents().readBookmarkContent(param);     // 读取书签内容
client.documents().replaceBookmarkContent(param);  // 替换书签内容
```

### SystemApi - 系统操作
```java
client.system().getAppDetail(param);        // 获取应用详情
client.system().updateCallbackUrl(param);   // 更新回调地址
client.system().getUserStatus(param);       // 获取用户状态
client.system().activateUserSeat(param);    // 激活用户席位
client.system().cancelUserSeat(param);      // 取消用户席位
client.system().batchSetUserSeat(param);    // 批量设置席位
```

### PreviewApi - 预览操作
```java
client.preview().create(param);    // 创建预览
client.preview().access(param);    // 访问预览
```

## 项目结构

```
src/main/java/com/shimo/sdk/
├── ShimoClient.java              # 主客户端入口
├── api/                          # API 模块(按功能分类)
│   ├── FileApi.java              # 文件操作
│   ├── TableApi.java             # 表格操作
│   ├── DocumentApi.java          # 文档操作
│   ├── PreviewApi.java           # 预览操作
│   └── SystemApi.java            # 系统操作
├── dto/                          # 数据传输对象
│   ├── request/                  # 请求对象
│   │   ├── BaseRequest.java      # 基础请求类
│   │   └── ...Request.java       # 各类请求对象(21个)
│   └── response/                 # 响应对象
│       ├── BaseResponse.java     # 基础响应类
│       └── ...Res.java           # 各类响应对象(18个)
├── common/                       # 公共类
│   ├── enums/                    # 枚举类
│   ├── ShimoConfig.java          # SDK 配置
│   ├── SdkException.java         # SDK 异常
│   └── Constants.java            # 常量定义
└── utils/                        # 工具类
    ├── HttpClient.java           # HTTP 客户端
    ├── JsonUtil.java             # JSON 工具
    └── StrUtil.java              # 字符串工具
```

### 设计特点

**1. 模块化设计**
- 5 个功能模块,职责清晰
- API 按业务功能组织,易于查找和使用

**2. DTO 层次化**
- `BaseRequest` 提取公共请求字段
- `BaseResponse` 提取公共响应字段(status, message)
- 减少重复代码,提高可维护性

**3. 统一的 HTTP 客户端**
- 单例 HttpClient,支持连接池复用
- 统一的请求构建和响应处理
- 所有方法使用 `response` 而非缩写命名

**4. 清晰的分层架构**
```
ShimoClient (入口层)
    ↓
API 模块 (业务逻辑层)
    ↓
HttpClient (HTTP 通信层)
    ↓
DTO (数据传输层)
```

## 核心设计

### 实例化客户端模式

不同于传统的静态方法调用,本 SDK 采用实例化客户端模式:

```java
// 创建客户端实例
ShimoClient client = new ShimoClient(signature, token);

// 使用各个功能模块
client.files().create(request);
client.tables().getContent(request);
client.documents().getPlainText(fileId);

// 使用完毕后关闭
client.close();
```

**优势:**
- ✅ 连接池复用,性能更好
- ✅ 支持自定义配置
- ✅ 更好的资源管理
- ✅ 线程安全,可多线程共享
- ✅ 代码结构清晰,易于维护

## 优势对比

| 特性 | 旧版本 | 新版本 |
|-----|-------|-------|
| API 组织 | 30+ 个独立类 | 5 个功能模块 |
| 连接复用 | ❌ 每次创建新连接 | ✅ 连接池复用 |
| 代码重复 | ❌ 大量重复代码 | ✅ 统一处理逻辑 |
| 可维护性 | ⚠️ 难以维护 | ✅ 易于维护扩展 |
| 性能 | ⚠️ 较差 | ✅ 优秀 |
| 资源管理 | ❌ 无法主动释放 | ✅ 支持 close() |

## 最佳实践

### 1. 单例模式(推荐)
```java
public class ShimoSDKManager {
    private static ShimoClient instance;
    
    public static synchronized ShimoClient getInstance(String signature, String token) {
        if (instance == null) {
            instance = new ShimoClient(signature, token);
        }
        return instance;
    }
}
```

### 2. Try-with-resources
```java
// 不推荐:需要手动关闭
ShimoClient client = new ShimoClient(signature, token);
try {
    client.files().create(param);
} finally {
    client.close();
}

// 推荐:如果客户端实现了 AutoCloseable
// (目前未实现,可以后续添加)
```

### 3. 异常处理
```java
try {
    ImportFileRes result = client.files().importFile(param);
    System.out.println("导入成功: " + result);
} catch (SdkException e) {
    // SDK 异常包含状态码和详细信息
    System.err.println("状态码: " + e.getStatusCode());
    System.err.println("错误信息: " + e.getMessage());
}
```

## 线程安全

`ShimoClient` 是线程安全的,可以在多线程环境中共享同一个实例:

```java
// 创建一个客户端实例
ShimoClient client = new ShimoClient(signature, token);

// 在多个线程中使用
ExecutorService executor = Executors.newFixedThreadPool(10);
for (int i = 0; i < 100; i++) {
    executor.submit(() -> {
        try {
            client.files().create(param);
        } catch (SdkException e) {
            e.printStackTrace();
        }
    });
}
```

## 代码规范

### 命名约定
- **包名**: 全小写,语义清晰 (`dto`, `response`, `request`)
- **类名**: 驼峰命名
  - Request 类: `*Request` (如 `CreateFileRequest`)
  - Response 类: `*Res` (如 `ImportFileRes`)
  - API 类: `*Api` (如 `FileApi`)
- **变量名**: 使用完整单词,避免缩写
  - ✅ `response` (而非 `resp`)
  - ✅ `requestBody` (而非 `reqBody`)

### Import 规范
禁止使用通配符 import,明确依赖关系:

```java
// ❌ 错误
import com.shimo.sdk.dto.request.*;
import com.shimo.sdk.dto.response.*;

// ✅ 正确
import com.shimo.sdk.dto.request.CreateFileRequest;
import com.shimo.sdk.dto.request.ImportFileRequest;
import com.shimo.sdk.dto.response.ImportFileRes;
```

## 更新日志

### v2.0.0 (当前版本)
- 🎉 全新架构重构
- ✨ 实例化客户端设计
- ✨ 按功能模块组织 API (5个模块)
- ✨ DTO 层次化设计,引入 BaseRequest 和 BaseResponse
- ✨ 包名优化: `model` → `dto`, `resp` → `response`
- ✨ 支持连接池复用
- ✨ 统一异常处理
- ✨ 统一变量命名规范
- ✨ 更好的代码可维护性
- ♻️ 保持向后兼容性

### v1.0.0
- 初始版本发布

## 贡献

欢迎提交 Issue 和 Pull Request!

## 许可证

[许可证信息]
