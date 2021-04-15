<template>
  <div class="box">
    <!-- accept=".xlsx" -->
    <el-upload
       class="upload-demo"
      drag
      accept=".xls, .xlsx"
      :limit="limitNum"
      :auto-upload="false"
      :action="UploadUrl()"
      :before-upload="beforeUploadFile"
      :on-change="fileChange"
      :on-exceed="exceedFile"
      :on-success="handleSuccess"
      :on-error="handleError"
      :file-list="fileList"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">
        只能上传xlsx/xls文件，且不超过10M
      </div>
    </el-upload>
    <div>
      <el-button size="medium" @click="uploadFile">立即上传</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      limitNum: 1, // 上传excell时，同时允许上传的最大数
      fileList: [], // excel文件列表
    };
  },
  methods: {
    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$message({
        message:
          "`只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`",
        showClose: true,
        type: "warning",
      });
    },
    // 文件状态改变时的钩子
    fileChange(file, fileList) {
      console.log(file.raw);
      this.fileList.push(file.raw);
      console.log(this.fileList);
    },
    // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
    beforeUploadFile(file) {
      console.log("before upload");
      console.log(file);
      let extension = file.name.substring(file.name.lastIndexOf(".") + 1);
      let size = file.size / 1024 / 1024;
      if (extension !== "xlsx") {
        this.$message({
          message: "只能上传后缀是.xlsx的文件",
          showClose: true,
          type: "warning",
        });
      }
      if (size > 10) {
        this.$message({
          message: "文件大小不得超过10M",
          showClose: true,
          type: "warning",
        });
      }
    },
    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.$message({
        message: "文件上传成功",
        showClose: true,
        type: "success",
      });
    },
    // 文件上传失败时的钩子
    handleError(err, file, fileList) {
      this.$message({
        message: "文件上传失败",
        showClose: true,
        type: "error",
      });
    },
    UploadUrl: function () {
      // 因为action参数是必填项，我们使用二次确认进行文件上传时，直接填上传文件的url会因为没有参数导致api报404，所以这里将action设置为一个返回为空的方法就行，避免抛错
      return "";
    },
    uploadFile() {
      if (this.fileList.length === 0) {
        this.$message.warning("请上传文件");
      } else {
        let form = new FormData();
        // form.append("file", this.fileList[0]);
        // console.log("ds", form);
        // console.log("sdfa", this.fileList);
        /**
         * start
         */
        let reader = new FileReader();
        reader.readAsText(this.fileList[0]);
        reader.onload = (e) => {
          let data = e.target.result;
          //获取数据
          // console.log(data);
          //格式化数据
          let list = data.replace(/([.\n\r]+)/g, "#");
          let lista = list.split("#");
          //变成了两层数组
          let listdata = [];
          for (let index = 0; index < lista.length; index++) {
            let lists = lista[index].split(",");
            listdata.push(lists);
          }
          //双层数组 双层循环赋值
          console.log("dd", listdata);
        };
        /**
         * end
         */
        this.$axios({
          method: "post",
          url: "http://localhost:8081/getfiles",
          headers: {
            "Content-type": "multipart/form-data",
          },
          data: form,
        }).then(
          (res) => {},
          (err) => {}
        );
      }
    },
  },
};
</script>

<style scoped>
.box {
  display: flex;
  justify-content: center;
  align-content: center;
  flex-direction: column;
  text-align: center;
}
.upload-demo {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 50px;
}
</style>

