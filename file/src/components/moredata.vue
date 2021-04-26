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
      let reader = new FileReader();
      reader.readAsText(file.raw);
      reader.onload = (e) => {
        this.ee = e.target.result;
        console.log(e.target.result);
        this.datadevlist = e.target.result;

      };
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
.morebox{
  text-align: center;
}
.upload-demo{
   display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 50px;
}
</style>
