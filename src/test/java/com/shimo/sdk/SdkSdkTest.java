package com.shimo.sdk;

import com.shimo.sdk.common.SdkException;
import com.shimo.sdk.dto.request.ImportFileRequest;
import com.shimo.sdk.dto.response.ImportFileRes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SDK 测试用例
 * 演示新的 ShimoClient API 使用方式
 */
public class SdkSdkTest {

    private ShimoClient client;
    private static final String TEST_SIGNATURE = "eyJhbGciOiJIUzI1NiIsImtpZCI6InNoaW1vZGV2IiwidHlwIjoiSldUIn0.eyJleHAiOjE3NjM3MDczOTd9.UDitDQswMk49c5hXr0Z2dXinE_jwkGTQYhtLFa-XaCQ";
    private static final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiIsImtpZCI6InNoaW1vZGV2IiwidHlwIjoiSldUIn0.eyJleHAiOjE3MzIyNTc3OTcsInVzZXJJZCI6OTMsIm1vZGUiOiJzaGltbyJ9.9vfrwvhV3S7kd--PHKj70ExGy8P5ZuAUjpfu7TbWpFc";

    @Before
    public void setUp() throws SdkException {
        // 初始化客户端,连接可以被复用
        client = new ShimoClient(TEST_SIGNATURE, TEST_TOKEN);
    }

    @After
    public void tearDown() {
        // 测试完成后关闭客户端,释放资源
        if (client != null) {
            client.close();
        }
    }

    /**
     * 测试导入文件(新 API 方式 - 不需要传 signature 和 token)
     */
    @Test
    public void testImportFile() {
        // 注意:Request 中不再需要传 signature 和 token
        ImportFileRequest request = ImportFileRequest.builder()
                .fileId("dasdas23asd")
                .type("document")
                .fileUrl("https://shimoee-deploy-tools.obs.cn-north-4.myhuaweicloud.com/stress-files/shimo_test.docx")
                .build();

        try {
            // 使用新的 API 方式:client.files().importFile()
            ImportFileRes result = client.files().importFile(request);
            System.out.println("导入成功: " + result);
        } catch (SdkException e) {
            System.err.println("导入失败: " + e.getMessage());
        }
    }

    /**
     * 演示新 API 的其他使用示例
     */
    @Test
    public void testNewApiUsageExamples() {
        try {
            // 文件操作示例
            // client.files().create(createRequest);
            // client.files().delete("fileId");
            // client.files().exportFile(exportRequest);

            // 表格操作示例
            // client.tables().getContent(getContentRequest);
            // client.tables().updateContent(updateRequest);

            // 文档操作示例
            // client.documents().getPlainText("fileId");
            // client.documents().getHistory(historyRequest);

            // 系统操作示例
            // client.system().getAppDetail(appRequest);
            // client.system().activateUserSeat(seatRequest);

            // 预览操作示例
            // client.preview().create(previewRequest);

            System.out.println("新 API 使用示例演示完成");
        } catch (Exception e) {
            System.err.println("测试失败: " + e.getMessage());
        }
    }
}
