<!--  -->
<template>
  <div class="tabbox">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="上传CSV 酷宅云" name="1" class="eltab">
        <HelloWorld />
      </el-tab-pane>
      <el-tab-pane disabled label="上传表格小匠" name="2" class="eltab">
        <Xjlist
      /></el-tab-pane>
      <el-tab-pane disabled label=" xls表格" name="4" class="eltab">
        <Xls
      /></el-tab-pane>
      <el-tab-pane   label="多列数据导入" name="3" class="eltab">
        <Moredata />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import HelloWorld from "../components/HelloWorld";
import Xjlist from "../components/Xjlist";
import Xls from "../components/xls";
import Moredata from "../components/moredata";
export default {
  name: "",
  //import引入的组件需要注入到对象中才能使用
  components: { HelloWorld, Xjlist, Xls, Moredata },
  props: [],
  data() {
    //这里存放数据
    return {
      activeName: "1",
    };
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
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
.tabbox {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.eltab {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 800px;
  height: 800px;
}
</style>
