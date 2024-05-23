<script setup>
import { fileApi } from "@/api/file";
import { ref } from "vue";
import { backendURL } from "@/utils/config";
import { ElMessage } from "element-plus";
import httpCode from "@/utils/httpCode";

// 文件数据菜单
const fileTableData = ref([
  // {
  //   id: "",
  //   spaceId: 0,
  //   fileName: "",
  //   filePath: "",
  //   uploaderId: 0,
  //   updateTime: "",
  //   fileSize: 0,
  //   downloadCount: 0,
  //   fileType: {
  //     id: 0,
  //     typeName: "",
  //   },
  // },
]);

// 下载文件
const downloadFile = (scope) => {
  const fileDownloadPath = `${backendURL}${scope.row.filePath}${scope.row.id}`;
  console.log(fileDownloadPath);
  window.open(`${fileDownloadPath}`, "target");
  fileTableData.value[scope.$index].downloadCount += 1;
};

// 获取文件列表
const getFileList = async () => {
  const res = await fileApi.getList();
  console.log("fileList :>> ", res);
  fileTableData.value = res.data;
};
getFileList();

// 删除文件
const deleteFile = async (row) => {
  const res = await fileApi.delete(row.id);
  console.log("delete >> res :>> ", res);
  if (res.code == httpCode.SUCCESS) {
    ElMessage.success(`删除成功`);
    location.reload(); // 刷新页面
  }
};
</script>

<template>
  <div class="download-wrapper-container">
    <div>
      <el-table
        :data="fileTableData"
        stripe
        style="width: 100%"
        :scrollbar-always-on="true"
        show-overflow-tooltip
        border
      >
        <el-table-column type="index" />
        <el-table-column prop="id" label="文件编号" width="100px" />
        <el-table-column prop="spaceId" label="空间ID" width="75px" />
        <el-table-column prop="fileName" label="文件名" />
        <el-table-column prop="filePath" label="路径" width="150px" />
        <el-table-column prop="uploaderId" label="上传者" width="100px" />
        <el-table-column prop="updateTime" label="上传时间" width="160px" />
        <el-table-column prop="fileSize" label="文件大小" width="100px" />
        <el-table-column prop="downloadCount" label="下载次数" width="90px" />
        <el-table-column prop="fileType.typeName" label="类型" width="80px" />
        <el-table-column fixed="right" label="操作" width="120">
          <template #default="scope">
            <el-button
              link
              type="primary"
              size="small"
              @click.prevent="downloadFile(scope)"
              >下载</el-button
            >
            <el-button
              link
              type="primary"
              size="small"
              @click="deleteFile(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<style lang="scss" scoped></style>
