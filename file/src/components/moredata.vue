<!-- 测试导入多行数据 到数据库 -->
<template>
  <div class="morebox">
    <P>测试导入多行数据</P>
    <div>
      <el-upload
        class="upload-demo"
        drag
        ref="upload"
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple
        :auto-upload="false"
        :file-list="fileList"
        :on-change="handleChange"
        :on-success="success"
        :http-request="uploadFile"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
    </div>
    <el-button size="medium" @click="btns">插入devlist表</el-button>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: [],
  data() {
    //这里存放数据
    return {
      fileList: [],
      name: "",
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    btns() {
      let flie = this.name;

      if (!flie) {
        this.$message({
          message: "请先上传文件",
          showClose: true,
          type: "error",
        });
        return;
      }

      this.$refs.upload.submit();
    },
    handleChange(file, fileList) {
      //   this.fileList = file.raw;
      this.fileList = fileList.slice(-1);

      this.$message({
        message: file.raw.type,
        showClose: true,
        type: "info",
      });
      this.name = file.name;
      console.log(file.name);
    },
    //上传成功！
    success(response, file, fileList) {
      console.log(response, file, fileList);
      // let reader = new FileReader();
      // reader.readAsText(file.raw);
      // reader.onload = (e) => {
      //   console.log(e.target.result);
      //   this.datadevlist = e.target.result;
      // };
    },
    //覆盖上传 不走success函数
    uploadFile(params) {
      let reader = new FileReader();
      reader.readAsText(params.file);
      reader.onload = (e) => {
        // console.log(e.target.result);
        this.datadevlist = e.target.result;
        this.arraylist();
      };
    },
    arraylist() {
      let datalist = this.datadevlist;
      let list = datalist.replace(/([.\n\r]+)/g, "#*#");
      let listsplit = list.split("#*#");
      console.log("list", listsplit);
      let listpush = [];
      for (let index = 0; index < listsplit.length; index++) {
        let listarry = listsplit[index].split(",");
        listpush.push(listarry);
      }
      console.log("ee", listpush);
      this.morearrylist(listpush);
    },
    morearrylist(data) {
      let datalist = {};
      let datalist2 = [];
      for (let index = 0; index < data.length; index++) {
        for (let indexs = 0; indexs < data[index].length; indexs++) {
          datalist = {
            deviceid: data[index][3],
            sn: data[index][0],
            addr1: data[index][1],
            addr2: data[index][2],
            testResult: "",
            testDatetime: "",
            checkDatetime: "",
            createTime: "",
            checkCount: "",
            orderId: this.orderid,
            packages: "",
            packageDatetime: "",
          };
        }
        datalist2.push(datalist);
      }
      console.log("morearry", datalist2);
      this.refile(datalist2);
    },
    /**
     * 导入须知
     * 确保文件格式为.csv
     * 内容不存在中文
     * 导入对应的属性均为字符串格式
     * 其他待优化中
     */
    refile(e) {
      let url = "http://localhost:8081/xjlist";

      this.$axios
        .post(url, e, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data.code == 200) {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "success",
            });
          } else {
            this.$message({
              message: res.data.msg,
              showClose: true,
              type: "error",
            });
          }
        })
        .catch((err) => {
          console.error(err);
          this.$message({
            message: err,
            showClose: true,
            type: "error",
          });
        });
    },
  },
  //生命周期 - 创建完成（可以访问当前this实例）
  created() {},
  //生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {},
  beforeCreate() {}, //生命周期 - 创建之前
  beforeMount() {}, //生命周期 - 挂载之前
  beforeUpdate() {}, //生命周期 - 更新之前
  updated() {}, //生命周期 - 更新之后
  beforeDestroy() {}, //生命周期 - 销毁之前
  destroyed() {}, //生命周期 - 销毁完成
  activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang='scss' scoped>
//@import url(); 引入公共css类
.morebox {
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
