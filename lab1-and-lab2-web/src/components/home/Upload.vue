<script setup>
import { ref } from "vue";
import { genFileId } from "element-plus";
import { fileApi } from "@/api/file";
import { ElMessage } from "element-plus";
import httpCode from "@/utils/httpCode";

// 文件上传表单
const upload = ref();

// 新文件替换老文件
const handleExceed = (files) => {
  if (upload.value != null) {
    upload.value.clearFiles();
    const file = files[0];
    console.log(file);
    file.uid = genFileId();
    upload.value.handleStart(file);
  }
};
// 上传按钮
const submitUpload = () => {
  if (upload.value != null) {
    upload.value.submit();
  }
};
// 上传文件
const uploadFile = async (options) => {
  console.log("options :>> ", options);
  const formData = new FormData();
  formData.append("file", options.file);
  console.log(formData);
  const res = await fileApi.upload(formData);
  console.log("res :>> ", res);
  if (res.code == httpCode.SUCCESS) {
    upload.value.clearFiles(); // 清空文件列表
    ElMessage.success(`上传成功`);
    location.reload(); // 刷新页面
  } else {
    ElMessage.error(`${res.message}，code: ${res.code}, 错误信息: ${res.data}`);
  }
};
</script>

<template>
  <div class="upload-wrapper">
    <div class="upload-wrapper-container">
      <el-upload
        ref="upload"
        class="upload-form"
        :limit="10"
        :on-exceed="handleExceed"
        :auto-upload="false"
        :http-request="uploadFile"
        drag
        multiple
      >
        <template #trigger>
          选择一个文件
          <!-- <el-button type="primary">选择一个文件</el-button> -->
        </template>
        <el-button class="upload-button" type="success" @click="submitUpload">
          上传
        </el-button>
        <template #tip>
          <div class="el-upload__tip text-red">
            文件上传, 上限10个, 新文件覆盖老文件
          </div>
        </template>
      </el-upload>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.upload-wrapper {
  &-container {
    :deep(.el-upload) {
      min-width: 300px;
      width: 30vw;
      margin: 3px;
    }
    .upload-button {
      margin: 3px;
    }
  }
}
</style>
